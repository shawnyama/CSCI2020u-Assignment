// CSCI2020u - Assignment
// By: Shawn Yama & Harrish Thasarathan

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Question3 extends Application {  
  private int height = 400;
  private int width = 400;
  //Define circle points
  private Circle[] circle = {new Circle(40, 40, 5),
    new Circle(140, 40, 5), new Circle(60, 140, 5)};
  //Define center circle
  private Circle CircCenter = new Circle(height/2,width/2,80);
  private Line line1 = new Line();
  private Line line2 = new Line();
  private Line line3 = new Line();  
  private Text[] text = {new Text(), new Text(), new Text()};
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {  
    Pane pane = new Pane();    
    CircCenter.setFill(Color.TRANSPARENT);
    CircCenter.setStroke(Color.BLACK);
    for(int i = 0; i <3; i++){
      circle[i].setFill(Color.RED);
      setRanLocation(CircCenter, circle[i]);
    }
    drawLines();
    pane.getChildren().addAll(CircCenter, circle[0], circle[1], circle[2],
      line1, line2, line3, text[0], text[1], text[2]);
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 400);
    primaryStage.setTitle("Question3"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage 

    circle[0].setOnMouseDragged(e -> { 
      // Keep points along large circle
      Point2D bigcirc = new Point2D (CircCenter.getCenterX(),CircCenter.getCenterY());
      Point2D mouse = new Point2D(e.getX(), e.getY());
      Point2D centerToMouse = mouse.subtract(bigcirc);
      Point2D centerToNewPoint = centerToMouse.normalize().multiply(CircCenter.getRadius());
      Point2D newPoint = centerToNewPoint.add(bigcirc);
      //move points
      circle[0].setCenterX(newPoint.getX());
      circle[0].setCenterY(newPoint.getY());
      //recalculate lines and redisplay angles
      drawLines();
      
    });

    circle[1].setOnMouseDragged(e -> { 
      //Attach points to large circle
      Point2D bigcirc = new Point2D (CircCenter.getCenterX(),CircCenter.getCenterY());
      Point2D mouse = new Point2D(e.getX(), e.getY());
      Point2D centerToMouse = mouse.subtract(bigcirc);
      Point2D centerToNewPoint = centerToMouse.normalize().multiply(CircCenter.getRadius());
      Point2D newPoint = centerToNewPoint.add(bigcirc);
      //move points
      circle[1].setCenterX(newPoint.getX());
      circle[1].setCenterY(newPoint.getY());
      //recalculate lines and redisplay angles
      drawLines();
      
    });
    
    circle[2].setOnMouseDragged(e -> { 
      // Attach points to large circle
      Point2D bigcirc = new Point2D (CircCenter.getCenterX(),CircCenter.getCenterY());
      Point2D mouse = new Point2D(e.getX(), e.getY());
      Point2D centerToMouse = mouse.subtract(bigcirc);
      Point2D centerToNewPoint = centerToMouse.normalize().multiply(CircCenter.getRadius());
      Point2D newPoint = centerToNewPoint.add(bigcirc);
      //move points
      circle[2].setCenterX(newPoint.getX());
      circle[2].setCenterY(newPoint.getY());
      //recalculate lines and redisplay angles
      drawLines();
      
    });
  }

  private void drawLines() {
    line1.setStartX(circle[0].getCenterX());
    line1.setStartY(circle[0].getCenterY());
    line1.setEndX(circle[1].getCenterX());
    line1.setEndY(circle[1].getCenterY());
    line2.setStartX(circle[0].getCenterX());
    line2.setStartY(circle[0].getCenterY());
    line2.setEndX(circle[2].getCenterX());
    line2.setEndY(circle[2].getCenterY());
    line3.setStartX(circle[1].getCenterX());
    line3.setStartY(circle[1].getCenterY());
    line3.setEndX(circle[2].getCenterX());
    line3.setEndY(circle[2].getCenterY());

    //Line distance calculation using Point2D vectors
    double a = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
            distance(circle[1].getCenterX(), circle[1].getCenterY());
    double b = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
            distance(circle[0].getCenterX(), circle[0].getCenterY());
    double c = new Point2D(circle[1].getCenterX(), circle[1].getCenterY()).
            distance(circle[0].getCenterX(), circle[0].getCenterY());     
    double[] angle = new double[3];

    //Calculate angles 
    angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
    angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
    angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

    //reset text location to follow moving points
    for (int i = 0; i < 3; i++) {
      text[i].setX(circle[i].getCenterX());
      text[i].setY(circle[i].getCenterY() - radius);
      text[i].setText(String.format("%.2f", Math.toDegrees(angle[i])));
    }
  }

  private void setRanLocation(Circle big,Circle point){
      double angle = Math.random()*360; 
      double xcor = big.getCenterX() + big.getRadius()* Math.cos(Math.toRadians(angle));
      double ycor = big.getCenterY() + big.getRadius() * Math.sin(Math.toRadians(angle));
      point.setCenterX(xcor); 
      point.setCenterY(ycor);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
