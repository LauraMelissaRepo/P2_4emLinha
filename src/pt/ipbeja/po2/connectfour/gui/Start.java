package pt.ipbeja.po2.connectfour.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Laura Melissa Bernardo Correia (17179)
 * @version 19/04/2019
 */

public class Start extends Application {
    @Override
    public void start(Stage primaryStage){
        Board board = new Board();
        Scene scene = new Scene(board);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
