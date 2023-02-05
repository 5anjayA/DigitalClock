package com.example.digitalclock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Controller {
    @FXML
    private Label label;


    public void initialize() {
        RunningTime();
    }


    public void RunningTime() { // could also use animationTimer class to create object and override its handle method to update clock and display it in each animation frame.
        final Thread thread = new Thread(() -> { //used a thread here to allow program to display clock and update the time in the background -> allows concurrent threads to execute other classes if needed so user interface remains responsive.
            SimpleDateFormat timeFormat = new SimpleDateFormat("dd MMMM, yyyy"); //here using the import -> time data (date) is grabbed from simpleformat database and stored in a new object in which is displayed in the clock view.
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); //(time) data is grabbed and stored in new object.

            while (true) {
                try {
                    Thread.sleep(1000); // this pauses the current thread for 1sec and updating the time -> to refresh clock for every second. The program will try to interrupt the thread from doing this so put try and catch here to avoid crashing of program//
                } catch (Exception e) {
                    e.getStackTrace(); ///call stack is captured and used to retrieve info of where exception is formed//
                }
                final String time = timeFormat.format(new Date().getTime());
                final String Date = dateFormat.format(new Date().getTime());

                Platform.runLater(() -> // thread used here can't update the User Interface as it's not a JavaFX App thread. Use this to set task to update label in JavaFX app thread.
                        label.setText(time + "\n" + Date));
            }
        });
        thread.start();
    }
}


