// CSCI2020u - Assignment
// By: Shawn Yama & Harrish Thasarathan

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Question2 extends Application {
  @Override
  // Override the start method in the Application class
  public void start(Stage primaryStage) {
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setHgap(5);
    pane.setVgap(5);
    //create necessary textfields
    TextField InvAmount = new TextField();
    TextField Years = new TextField();
    TextField AnnIntRate = new TextField();
    TextField FutVal = new TextField();

    //Enter values to the right
    InvAmount.setAlignment(Pos.CENTER_RIGHT);
    Years.setAlignment(Pos.CENTER_RIGHT);
    AnnIntRate.setAlignment(Pos.CENTER_RIGHT);
    FutVal.setAlignment(Pos.CENTER_RIGHT);
    FutVal.setDisable(true);
    FutVal.setStyle("-fx-text-fill: black ");

    //make textfields longer  
    InvAmount.setPrefColumnCount(12);
    Years.setPrefColumnCount(12);
    AnnIntRate.setPrefColumnCount(12);
    FutVal.setPrefColumnCount(12);

    Label Inv = new Label("Investment Amount: ");
    Label Year = new Label("Years: "); 
    Label AnnInt = new Label("Annual Interest Rate: ");
    Label Fut = new Label("Future Value");

    //Add labels and Textfields in appropriate grid coordinates
    pane.add(Inv,0,1);
    pane.add(InvAmount, 1,1);
    pane.add(Year,0,2);
    pane.add(Years,1,2);
    pane.add(AnnInt, 0,3);
    pane.add(AnnIntRate,1,3);
    pane.add(Fut,0,4);
    pane.add(FutVal,1,4);

    HBox hBox = new HBox(5);
    hBox.setPadding(new Insets(10, 10, 10, 10));
    Button btCalculate = new Button("Calculate");
    hBox.setAlignment(Pos.CENTER_RIGHT);
    hBox.getChildren().addAll(btCalculate);
    
    //place gridpane with textfields and labels and hbox with calculate button in one pane
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);
    BorderPane.setAlignment(hBox, Pos.BOTTOM_CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 310, 200);
    primaryStage.setTitle("Question2"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btCalculate.setOnAction(e -> {
      //format for 2 decimal places 
      DecimalFormat df = new DecimalFormat("#.00");
      //calculate result from given formula with modification for annual interest vs monthly interest 
      double result = Double.parseDouble(InvAmount.getText()) * (Math.pow(1 + (Double.parseDouble(AnnIntRate.getText())/12)/100,Double.parseDouble(Years.getText())*12));
      FutVal.setText(df.format(result)+ "");
    });
  }

  public static void main(String[] args) {
    launch(args);
  }
} 
