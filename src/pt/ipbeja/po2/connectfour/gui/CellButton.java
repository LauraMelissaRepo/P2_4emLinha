package pt.ipbeja.po2.connectfour.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Laura Melissa Bernardo Correia (17179)
 * @version 19/04/2019
 */

public class CellButton extends Button {

    private static final Image PLAY_EMPTY = new Image("/resources/EMPTY.png");
    private static final Image PLAY_PLAYER1 = new Image("/resources/PLAYER1.png");
    private static final Image PLAY_PLAYER2 = new Image("/resources/PLAYER2.png");


    private final ImageView imageView;


    public CellButton() {
        this.imageView = new ImageView(PLAY_EMPTY);
        this.setGraphic(imageView);
    }

    public void player1(){
        this.imageView.setImage(PLAY_PLAYER1);
    }

    public void player2(){
        this.imageView.setImage(PLAY_PLAYER2);
    }
}
