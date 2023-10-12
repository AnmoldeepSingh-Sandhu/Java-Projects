import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StockMarketAnalyzer extends Application {
    private static final String ALPHA_VANTAGE_API_KEY = "LY5VTQXKUU8DCT4A";
    private double initialX;
    private double initialY;
    private List<String> dates = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stock Market Analyzer");

        // Input Controls
        TextField symbolInput = new TextField();
        symbolInput.setPromptText("Enter Stock Symbol (e.g., AAPL)");
        symbolInput.setPrefWidth(250); // Set the preferred width
        symbolInput.setPrefHeight(35); // Set the preferred height

        // Create an HBox to hold the symbolInput
        HBox symbolInputContainer = new HBox(symbolInput);
        symbolInputContainer.setPadding(new Insets(40, 10, 10, 10));; // Set the spacing around symbolInput
        symbolInputContainer.setAlignment(Pos.CENTER); // Center the symbolInput horizontally

        ComboBox<String> timeframeComboBox = new ComboBox<>();
        timeframeComboBox.setItems(FXCollections.observableArrayList("Daily", "Weekly", "Monthly"));
        timeframeComboBox.setValue("Daily");

        Button analyzeButton = new Button("Analyze");

        // Line Chart for Visualization
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Stock Price Chart");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Stock Price");
        lineChart.getData().add(series);

        lineChart.setPrefWidth(600); // Set the preferred width
        lineChart.setPrefHeight(600); // Set the preferred height


        // Set the line color to black
        String lineStyle = "-fx-stroke: black;";
        series.getNode().setStyle(lineStyle);
        
        
        // Layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(symbolInputContainer, timeframeComboBox, analyzeButton, lineChart);

        // Analyze Button Action
        analyzeButton.setOnAction(e -> {
            String symbol = symbolInput.getText();
            String timeframe = timeframeComboBox.getValue();

            if (!symbol.isEmpty()) {
                List<Double> prices = fetchData(symbol, timeframe);

                // Update the chart with fetched data
                series.getData().clear();
                for (int i = 0; i < prices.size(); i++) {

                    XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(i, prices.get(i));
                    series.getData().add(dataPoint);

                    // Set the point color to black
                    String pointStyle = "-fx-background-color: black, white; -fx-background-insets: 0, 1;";
                    dataPoint.getNode().setStyle(pointStyle);

                }

            } else {
                showAlert("Invalid Input", "Please enter a stock symbol.");
            }

        });

        
        
        lineChart.setOnMouseMoved(event -> {
            
            // Add tooltips to the data points in the line chart
            DecimalFormat decimalFormat = new DecimalFormat("#.##");

            // Get the index of the data point under the mouse cursor
            int dataIndex = (int) xAxis.getValueForDisplay(event.getX()).doubleValue();

            int index = dates.size() - dataIndex;
            
            if (index >= 0 && index < dates.size()) {

                String date = dates.get(index); // Retrieve the date

                for (XYChart.Data<Number, Number> data : series.getData()) {

                    // Use the retrieved date along with the price in the tooltip
                    Tooltip tooltip = new Tooltip("Date: " + date + "\nPrice: $" + decimalFormat.format(data.getYValue()));
                    Tooltip.install(data.getNode(), tooltip);

                }
            }

        });


        // Enable zooming on the line chart
        lineChart.setOnScroll((ScrollEvent event) -> {

            double deltaY = event.getDeltaY();
            double lowerBound = xAxis.getLowerBound();
            double upperBound = xAxis.getUpperBound();

            double maxRange = 25; // Define your maximum zoom range
            
            // Prevent invalid bounds and zooming out beyond the initial range
            if ((upperBound - lowerBound) > maxRange) {

                if (deltaY < 0) {
                    // Zoom in
                    xAxis.setAutoRanging(false);
                    xAxis.setLowerBound(lowerBound + 1);
                    xAxis.setUpperBound(upperBound - 1);
                } 
                
            }

            if(deltaY > 0 && lowerBound > 0){
                // Zoom out
                xAxis.setAutoRanging(false);
                xAxis.setLowerBound(lowerBound - 1);
                xAxis.setUpperBound(upperBound + 1);
            }
            
        });


        

        lineChart.setOnMousePressed(event -> {
            initialX = xAxis.getValueForDisplay(event.getX()).doubleValue();
            initialY = yAxis.getValueForDisplay(event.getY()).doubleValue();
        });

        lineChart.setOnMouseDragged(event -> {
            double deltaX = xAxis.getValueForDisplay(event.getX()).doubleValue() - initialX;
            double deltaY = yAxis.getValueForDisplay(event.getY()).doubleValue() - initialY;


            double newXLower = xAxis.getLowerBound() - deltaX;
            double newXUpper = xAxis.getUpperBound() - deltaX;
            double newYLower = yAxis.getLowerBound() - deltaY;
            double newYUpper = yAxis.getUpperBound() - deltaY;

            // Check if new bounds are not negative, then update them
            if (newXLower >= 0) {
                xAxis.setAutoRanging(false);
                yAxis.setAutoRanging(false);

                xAxis.setLowerBound(newXLower);
                xAxis.setUpperBound(newXUpper);

                yAxis.setLowerBound(newYLower);
                yAxis.setUpperBound(newYUpper);
            }

            // Update initial values for the next drag operation
            initialX = xAxis.getValueForDisplay(event.getX()).doubleValue();
            initialY = yAxis.getValueForDisplay(event.getY()).doubleValue();
        });

        Scene scene = new Scene(layout, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    private List<Double> fetchData(String symbol, String timeframe) {
        List<Double> prices = new ArrayList<>();

        try {
            String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_" + timeframe +
                    "&symbol=" + symbol + "&apikey=" + ALPHA_VANTAGE_API_KEY;

            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                // Skip the first line (header) if needed
                reader.readLine();
                
                int count = 0;
                while ((line = reader.readLine()) != null) {

                    // if(count < 50){System.out.println(line); count++;}

                    if (line.contains("4. close")) {

                        int startIndex = line.indexOf(":") + 3;
                        int endIndex = line.indexOf(",")-1;

                        if (startIndex != -1 && endIndex != -1) {
                            String closePriceStr = line.substring(startIndex, endIndex).trim();
                            double closePrice = Double.parseDouble(closePriceStr);
                            prices.add(closePrice);
                        }

                    }else if(line.contains(": {") ){

                        int startIndex = 9;
                        int endIndex = line.indexOf(":")-1;

                        if (startIndex != -1 && endIndex != -1 && count >= 2) {
                            String date = line.substring(startIndex, endIndex).trim();
                            dates.add(date);
                        }

                        count++;
                    }

                }

                reader.close();
            } else {
                showAlert("HTTP Error", "HTTP Request Failed with status code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while fetching data.");
        }
        return prices;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
