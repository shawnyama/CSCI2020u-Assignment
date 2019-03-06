// CSCI2020u - Assignment
// By: Shawn Yama & Harrish Thasarathan

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.control.TextField;

public class Question_4 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        // Create HBox that includes the text field and the view button
        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(0, 10, 10, 10));
        TextField tfDir = new TextField();
        tfDir.setMinWidth(600);
        Button btView = new Button("View"); // Create view button
        hBox.getChildren().add(new Label("Directory: "));
        hBox.getChildren().add(tfDir);
        hBox.getChildren().add(btView);
        hBox.setAlignment(Pos.CENTER);

        // Create VBox that includes the histogram
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(10, 10, 0, 10));
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Alphabet");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Occurences");
        BarChart histogram = new BarChart(xAxis, yAxis);
        XYChart.Series alphaBars = new XYChart.Series();
        histogram.setAnimated(false);
        vBox.getChildren().add(histogram);

        initializeHisto(histogram, alphaBars); // Initialize empty histogram

        // Create BorderPane that includes hBox on the bottom and vBox in center
        BorderPane pane = new BorderPane();
        pane.setBottom(hBox);
        pane.setCenter(vBox);

        Scene scene = new Scene(pane,  800, 450); // Create a scene and place it in the stage

        primaryStage.setTitle("Question_4"); // Set the stage title
        primaryStage.setScene(scene); // Put the scene in the stage

        primaryStage.show(); // Show the stage

        btView.setOnAction(e -> { // Enter directory by clicking the View button
            makeHistogram(tfDir.getText(), histogram, alphaBars); // Create the new histogram
        });

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) { // Enter directory by clicking ENTER key
                makeHistogram(tfDir.getText(), histogram, alphaBars); // Create the new histogram
            }
        });
    }

    // This method creates a new histogram with the users directory entry in the text field and histogram attributes
    public void makeHistogram(String directory, BarChart histogram, XYChart.Series alphaBars) {

        // Clear the previous values of the histogram
        alphaBars.getData().clear();
        histogram.getData().clear();

        int alphaCount; // Declare int that counts occurence of a letter in the text file
        Scanner scan; // Declare a Scanner to scan the text file
        File file = new File(directory); // Declare a file with its directory

        for (int i = 0; i < 26; i++) { // Loop through the number of letters in the alphabet
            alphaCount = 0; // Initialize number of letters counted to zero
            try {
                scan = new Scanner(file); // Open the file
            }
            catch(FileNotFoundException err) { // If the file is not found, initialize empty histogram, print error message and return
                initializeHisto(histogram, alphaBars);
                System.out.println("ERROR - Directory or file not found.");
                return;
            }
            while (scan.hasNext()) {
                String s = scan.next();
                for (int j = 0; j < s.length(); j++) { // Loop through current String
                    if((s.charAt(j)==(char)(65+i))||(s.charAt(j)==(char)(65+i+32))) { // If the letter is found
                        alphaCount++; // Increase the occurence of the letter
                    }
                }
            }
            scan.close(); // Close the file
            alphaBars.getData().add(new XYChart.Data(Character.toString((char)(65+i)), alphaCount)); // Create bar for current letter
        }
        histogram.getData().add(alphaBars); // Place bars in histogram
    }

    // This method initializes labels of the x and y axis
    public void initializeHisto(BarChart histogram, XYChart.Series alphaBars) {
        for (int i = 0; i < 26; i++) { // Loop through the number of letters in the alphabet
            alphaBars.getData().add(new XYChart.Data(Character.toString((char)(65+i)), 0)); // Create empty bar for current letter
        }
        histogram.getData().add(alphaBars); // Place bars in histogram
    }

    public static void main(String[] args) {
        launch(args);
    }
}