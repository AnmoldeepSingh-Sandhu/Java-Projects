// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URL;

// public class AlphaVantageDataRetriever {
//     public static void main(String[] args) {
//         try {
//             String apiKey = " LY5VTQXKUU8DCT4A"; // Replace with your Alpha Vantage API key
//             String symbol = "AAPL"; // Replace with the stock symbol you want to retrieve data for
//             String functionName = "TIME_SERIES_DAILY"; // Specify the function you want to use

//             String urlString = "https://www.alphavantage.co/query?function=" + functionName +
//                     "&symbol=" + symbol + "&apikey=" + apiKey;

//             URL url = new URL(urlString);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//             connection.setRequestMethod("GET");

//             int responseCode = connection.getResponseCode();

//             if (responseCode == HttpURLConnection.HTTP_OK) {
//                 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                 String line;
//                 StringBuilder response = new StringBuilder();

//                 while ((line = reader.readLine()) != null) {
//                     response.append(line);
//                 }

//                 reader.close();

//                 // Process the JSON response data
//                 String jsonData = response.toString();
//                 System.out.println(jsonData);
//             } else {
//                 System.out.println("HTTP Request Failed with status code: " + responseCode);
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }



import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AlphaVantageDataRetriever extends Application {
    private static final String ALPHA_VANTAGE_API_KEY = "LY5VTQXKUU8DCT4A";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stock Market Analyzer");

        // Input Controls
        TextField symbolInput = new TextField();
        symbolInput.setPromptText("Enter Stock Symbol (e.g., AAPL)");

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
        
        // Layout
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(symbolInput, timeframeComboBox, analyzeButton, lineChart);

        // Analyze Button Action
        analyzeButton.setOnAction(e -> {
            String symbol = symbolInput.getText();
            String timeframe = timeframeComboBox.getValue();

            if (!symbol.isEmpty()) {
                List<Double> prices = fetchData(symbol, timeframe);

                // Update the chart with fetched data
                series.getData().clear();
                for (int i = 0; i < prices.size(); i++) {
                    series.getData().add(new XYChart.Data<>(i, prices.get(i)));
                }
            } else {
                showAlert("Invalid Input", "Please enter a stock symbol.");
            }
        });

        
        // Add tooltips to the data points in the line chart
        for (XYChart.Data<Number, Number> data : series.getData()) {
            Tooltip tooltip = new Tooltip(String.format("Price: $%.2f", data.getYValue()));
            Tooltip.install(data.getNode(), tooltip);
        }

        lineChart.setMouseTransparent(false);
        series.getNode().setMouseTransparent(false);


        Scene scene = new Scene(layout, 800, 600);
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
            System.out.println(responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                // Skip the first line (header) if needed
                reader.readLine();
                

                while ((line = reader.readLine()) != null) {

                    if (line.contains("4. close")) {

                        int startIndex = line.indexOf(":") + 3;
                        int endIndex = line.indexOf(",")-1;

                        if (startIndex != -1 && endIndex != -1) {
                            String closePriceStr = line.substring(startIndex, endIndex).trim();
                            double closePrice = Double.parseDouble(closePriceStr);
                            prices.add(closePrice);
                        }

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
