package org.example.javafxlr16;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AnalogClock extends Pane {
    private Circle clockFace;
    private Line hourHand;
    private Line minuteHand;
    private Text digitalClock;

    public AnalogClock() {

        clockFace = new Circle();
        clockFace.setFill(Color.WHITE);
        clockFace.setStroke(Color.BLACK);


        hourHand = new Line();
        hourHand.setStrokeWidth(4);


        minuteHand = new Line();
        minuteHand.setStrokeWidth(2);


        digitalClock = new Text();
        digitalClock.setStyle("-fx-font-size: 20px;");

        getChildren().addAll(clockFace, hourHand, minuteHand, digitalClock);


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> update()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void update() {
        LocalTime now = LocalTime.now();

        // Update digital clock display
        digitalClock.setText(now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // Update clock hands
        double centerX = getWidth() / 2.0;
        double centerY = getHeight() / 2.0;
        double radius = Math.min(centerX, centerY) * 0.9;

        double secondAngle = now.getSecond() * 6;
        double minuteAngle = (now.getMinute() + now.getSecond() / 60.0) * 6;
        double hourAngle = (now.getHour() % 12 + now.getMinute() / 60.0) * 30;

        hourHand.setStartX(centerX);
        hourHand.setStartY(centerY);
        hourHand.setEndX(centerX + radius * 0.5 * Math.cos(Math.toRadians(hourAngle - 90)));
        hourHand.setEndY(centerY + radius * 0.5 * Math.sin(Math.toRadians(hourAngle - 90)));

        minuteHand.setStartX(centerX);
        minuteHand.setStartY(centerY);
        minuteHand.setEndX(centerX + radius * 0.7 * Math.cos(Math.toRadians(minuteAngle - 90)));
        minuteHand.setEndY(centerY + radius * 0.7 * Math.sin(Math.toRadians(minuteAngle - 90)));

        // Update clock
        clockFace.setRadius(radius);
        clockFace.setCenterX(centerX);
        clockFace.setCenterY(centerY);


        digitalClock.setX(centerX - digitalClock.getLayoutBounds().getWidth() / 2);
        digitalClock.setY(centerY + digitalClock.getLayoutBounds().getHeight() / 4);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        update();
    }
}
Лістинг коду: 
package org.example.javafxlr16;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        AnalogClock analogClock = new AnalogClock();


        Scene scene = new Scene(analogClock, 320, 320);


        stage.setTitle("Analog Clock");


        
