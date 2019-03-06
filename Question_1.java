// CSCI2020u - Assignment
// By: Shawn Yama & Harrish Thasarathan

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class Question_1 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        // Create an HBox to store the row of cards
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(5, 5, 5, 5));

        Random randnum = new Random(); // Declare random number generator
        int randCard; // Declare int that will store the random number
        int[] prevCards = new int[3]; // Declare array of ints that stores the previous cards generated

        for (int i = 0; i < 3; i++) {
            randCard = 1 + randnum.nextInt(54); // Generate a random number between 1 and 54

            ImageView card = new ImageView(new Image("file:///C:/Users/Shawn/Documents/sem2/csci2020u/Assignment/Cards/" + randCard + ".png")); // Find a new random card image using random number

            if ((randCard != prevCards[0])&&(randCard != prevCards[1])&&(randCard != prevCards[2])) { // If the random card has not been generated before, append it to the HBox
                hBox.getChildren().add(card);
                prevCards[i] = randCard;
            }
            else { // Otherwise generate another random card
                i--;
            }
        }

        BorderPane pane = new BorderPane(); // Create a border pane
        pane.setTop(hBox); // Place hBox in the border pane

        Scene scene = new Scene(pane); // Create a scene and place it in the stage
        primaryStage.setTitle("Question_1"); // Set the stage title
        primaryStage.setScene(scene); // Put the scene in the stage
        primaryStage.show(); // Show the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}