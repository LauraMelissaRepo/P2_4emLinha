package pt.ipbeja.po2.connectfour.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pt.ipbeja.po2.connectfour.View;
import pt.ipbeja.po2.connectfour.model.Cell;
import pt.ipbeja.po2.connectfour.model.ConnectFourModel;

import java.util.Optional;

/**
 * @author Laura Melissa Bernardo Correia (17179)
 * @version 19/04/2019
 */

public class Board extends VBox implements View{

    private final ConnectFourModel GAME_MODEL;
    private GridPane gridPane;
    MenuButton menuButton;
    MenuItem newgame;

    /**
     * Constructor
     */
    public Board() {
        MenuItem newGame = new MenuItem("NEW GAME");
        this.menuButton = new MenuButton("GAME", null, newGame);
        this.GAME_MODEL = new ConnectFourModel(this);
        this.gridPane = new GridPane();
        this.createBoard();
        this.getChildren().addAll(menuButton, gridPane);
    }

    /**
     * Creates de game board
     */
    private void createBoard(){

        CellButtonButton handler = new CellButtonButton();
        for (int i = 0; i < this.GAME_MODEL.SIZELINE; i++) {
            for (int j = 0; j < this.GAME_MODEL.SIZECOL; j++) {
                CellButton btn = new CellButton();
                btn.setOnAction(handler);
                this.gridPane.getChildren().add(btn);
                GridPane.setColumnIndex(btn, j);
                GridPane.setRowIndex(btn, i);
            }
        }
    }

    /**
     * Shows the alert with the player who wins
     */
    @Override
    public void isWinPosition(int turncounter) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "O jogador " + this.name() + " ganhou!\n" + "Pontuação: " + this.GAME_MODEL.points());
        alert.show();
        this.gridPane.setDisable(true);
    }

    /**
     * Shows the alert saying there is a draw
     */
    @Override
    public void draw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Empate!");
        alert.show();
    }

    /**
     * Ask the name of the person who won
     * @return the name of the person who won
     */
    public String name() {
        TextInputDialog name = new TextInputDialog("Nome");
        name.setContentText("Digite o seu nome:");
        Optional<String> result = name.showAndWait();
        String m = name.getResult();
        result.ifPresent(i -> System.out.println("Name: " + name));

        return m;
    }

    /**
     * Click handler for the CellButtons
     */
    class CellButtonButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            CellButton button = (CellButton) event.getSource();

            // Métodos do GridPane para obter a linha e coluna
            int line = gridPane.getRowIndex(button);
            int col = gridPane.getColumnIndex(button);

                GAME_MODEL.placeSelected(line, col);
                int lineToPlay = GAME_MODEL.getLineToPlay();
                Cell cell = GAME_MODEL.getCell(lineToPlay, col);
                CellButton buttonToChange = (CellButton) getButtonToChange(col, lineToPlay);
                if(cell == Cell.PLAYER1) {
                    buttonToChange.player1();
                }
                else {
                    buttonToChange.player2();
                }
        }
    }

    /**
     *
     * @return node
     */
    private Node getButtonToChange(int col, int row) {
        for (Node node : this.gridPane.getChildren()) {
            if (this.gridPane.getColumnIndex(node) == col && gridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
