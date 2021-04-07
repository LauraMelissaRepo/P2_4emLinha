package pt.ipbeja.po2.connectfour.model;

import pt.ipbeja.po2.connectfour.View;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Laura Melissa Bernardo Correia (17179)
 * @version 19/04/2019
 */

public class ConnectFourModel {

    public final int SIZELINE = 6;
    public final int SIZECOL = 7;
    private final View VIEW;
    //array bi-dimensional
    private final Cell[][] boardData;
    public int turnCounter;
    private int lineToPlay;
    List <Cell> boadState;


    /**
     * Constructor
     * @param view The Board View (observer)
     */
    public ConnectFourModel(View view) {
        this.VIEW = view;
        this.boardData = new Cell[this.SIZELINE][this.SIZECOL];
        this.fillBoard();
        boadState = new ArrayList<>();
    }

    /**
     * Creates the game with all the position Place.EMPTY
     * */
    private void fillBoard() {

        System.out.format("A criar um tabuleiro\n", this.SIZELINE, this.SIZECOL);

        for (int line = 0; line < this.SIZELINE; line++) {
            for (int col = 0; col < this.SIZECOL; col++) {
                this.boardData[line][col] = Cell.EMPTY;
            }
        }
    }

    /**
     * Check if the board cell is free (no player)
     * @param line Line coordinate
     * @param col  Column coordinate
     * @return true if the cell is free
     */
    public boolean isFree(int line, int col) {
        return this.boardData[line][col] == Cell.EMPTY;
    }

    /**
     * Plays on the specified board coordinates
     * @param line line coordinate
     * @param col  column coordinate
     */
    public void placeSelected(int line, int col) {
        if (isFree(line, col)) {
            this.updateBoardState(col);
            this.turnCounter++;
            this.checkBoardState(this.lineToPlay, col);
            System.out.println("Coordenadas: " + lineToPlay + "," + col + "!!!");
        }
    }

    /**
     * Checks if it is possible to win
     * @param line line coordinate
     * @param col col coordinate
     * @return the player who wins
     */
    private void checkBoardState(int line, int col) {
        if (this.isWinPosition(line, col)) {
            VIEW.isWinPosition(this.turnCounter);
        }
        if(this.draw() && !isWinPosition(line, col)){
            VIEW.draw();
        }

    }

    /**
     * Checks if it is possible to win
     * @param line line coordinate
     * @param col col coordinate
     * @return true if a winning condition was found
     */
    public boolean isWinPosition(int line, int col) {
        return  (this.winHorizontal(line) ||
                this.winVertical(col) ||
                this.winDiagonal1() ||
                this.winDiagonal2() ||
                this.winantiDiagonal1() ||
                this.winantiDiagonal2());
    }

    /**
     * Update the status of the board. Push the piece to the last line available in the column selected.
     * Alternates between the Player 1 and 2 depending if the turncounter is even or odd
     * @param col col coordinate
     */
    private void updateBoardState(int col) {
        for (int i = 0; i < SIZELINE; i++) {
            if(boardData[i][col] == Cell.EMPTY){
                this.lineToPlay = i;
            }
        }
        this.boardData[this.lineToPlay][col] = (this.turnCounter % 2 == 0) ? Cell.PLAYER1 : Cell.PLAYER2;
        this.boadState.add(this.getCell(this.lineToPlay,col));
}

    public Cell getCell(int line, int col) {
        return this.boardData[line][col];
    }

    public int getLineToPlay() {
        return this.lineToPlay;
    }


    /**
     * Check vertical (col) for winning condition
     * @param col
     * @return true if a winning vertical condition was found
     */
    private boolean winVertical(int col) {
        int soma = 1;
        for (int line = 0; line < this.SIZELINE - 1; line++) {
            if (this.boardData[line][col] != Cell.EMPTY && this.boardData[line + 1][col] == this.boardData[line][col]) {
                soma++;
                if (soma == 4) {
                    return true;
                }
            } else {
                soma = 1;
            }
        }
        return false;
    }

    /**
     * Check horizontal (line) for winning condition
     * @param line
     * @return true if a winning horizontal condition was found
     */
    private boolean winHorizontal(int line) {
        int soma = 1;
        for (int col = 0; col < this.SIZECOL - 1; col++) {
            if (this.boardData[line][col] != Cell.EMPTY && this.boardData[line][col + 1] == this.boardData[line][col]) {
                soma++;
                if (soma == 4) {
                    return true;
                }
            } else {
                soma = 1;
            }
        }
        return false;
    }

    /**
     * Check diagonal for winning condition
     * @return true if a winning diagonal condition was found
     */
    private boolean winDiagonal1(){
        int col = -1;
        int soma = 1;
        for (int i = 5; i > 0; i--) {
            col++;
                if (this.boardData[i][col] != Cell.EMPTY && this.boardData[i - 1][col + 1] == this.boardData[i][col]) {
                    soma++;
                    if (soma == 4) {
                        return true;
                    }
                } else {
                    soma = 1;
                }
    }

        col = -1;
        for (int i = 4; i > 0; i--) {
            col++;
                if (this.boardData[i][col] != Cell.EMPTY && this.boardData[i - 1][col + 1] == this.boardData[i][col]) {
                    soma++;
                    if (soma == 4) {
                        return true;
                    }
                } else {
                    soma = 1;
                }
        }

        col = -1;
        for (int i = 3; i > 0; i--) {
            col++;
                if (col < this.SIZECOL) {
                    if (this.boardData[i][col] != Cell.EMPTY && this.boardData[i - 1][col + 1] == this.boardData[i][col]) {
                        soma++;
                        if (soma == 4) {
                            return true;
                        }
                    } else {
                        soma = 1;

                    }
            }
        }
        return false;
}

    /**
     * Check diagonal for winning condition
     * @return true if a winning diagonal condition was found
     */
    private boolean winDiagonal2(){
        int col = 0;
        int soma = 1;
        for (int i = 5; i > 0; i--) {
            col++;
                if (this.boardData[i][col] != Cell.EMPTY && this.boardData[i - 1][col + 1] == this.boardData[i][col]) {
                    soma++;
                    if (soma == 4) {
                        return true;
                    }
                } else {
                    soma = 1;
                }
        }

        col = 1;
        for (int i = 5; i > 1; i--) {
            col++;
                if (this.boardData[i][col] != Cell.EMPTY && this.boardData[i - 1][col + 1] == this.boardData[i][col]) {
                    soma++;
                    if (soma == 4) {
                        return true;
                    }
                } else {
                    soma = 1;
                }
        }

        col = 2;
        for (int i = 5; i > 2; i--) {
            col++;
                if (this.boardData[i][col] != Cell.EMPTY && this.boardData[i - 1][col + 1] == this.boardData[i][col]) {
                    soma++;
                    if (soma == 4) {
                        return true;
                    }
                } else {
                    soma = 1;
                }
        }
        return false;
    }

    /**
     * Check anti-diagonal for winning condition
     * @return true if a winning anti-diagonal condition was found
     */
    private boolean winantiDiagonal1() {
        int line = 6;
        int soma = 1;
        for (int i = 6; i > 1; i--) {
            line--;
                if (this.boardData[line][i] != Cell.EMPTY && this.boardData[line - 1][i - 1] == this.boardData[line][i]) {
                    soma++;
                    if (soma == 4) {
                        return true;
                    }
                } else {
                    soma = 1;
                }
        }

        line = 5;
        for (int i = 6; i > 2; i--) {
            line--;
                if (this.boardData[line][i] != Cell.EMPTY && this.boardData[line - 1][i - 1] == this.boardData[line][i]) {
                    soma++;
                    if (soma == 4) {
                        return true;
                    }
                } else {
                    soma = 1;
                }
        }

        line = 4;
        for (int i = 6; i > 3; i--) {
            line--;
                if (this.boardData[line][i] != Cell.EMPTY && this.boardData[line - 1][i - 1] == this.boardData[line][i]) {
                    soma++;
                    if (soma == 4) {
                        return true;
                    }
                } else {
                    soma = 1;
                }
        }
        return false;
    }

    /**
     * Check anti-diagonal for winning condition
     * @return true if a winning anti-diagonal condition was found
     */
    private boolean winantiDiagonal2() {
        int line = 6;
        int soma = 1;
        for (int i = 5; i > 0; i--) {
            line--;
            if (this.boardData[line][i] != Cell.EMPTY && this.boardData[line - 1][i - 1] == this.boardData[line][i]) {
                soma++;
                if (soma == 4) {
                    return true;
                }
            } else {
                soma = 1;
            }
        }

        line = 6;
        for (int i = 4; i > 0; i--) {
            line--;
            if (this.boardData[line][i] != Cell.EMPTY && this.boardData[line - 1][i - 1] == this.boardData[line][i]) {
                soma++;
                if (soma == 4) {
                    return true;
                }
            } else {
                soma = 1;
            }
        }

        line = 6;
        for (int i = 3; i > 0; i--) {
            line--;
            if (this.boardData[line][i] != Cell.EMPTY && this.boardData[line - 1][i - 1] == this.boardData[line][i]) {
                soma++;
                if (soma == 4) {
                    return true;
                }
            } else {
                soma = 1;
            }
        }

        return false;
    }

    /**
     * Check the complete board for draw condition
     * @return true if a draw condition was found
     */
    public boolean draw(){
          return turnCounter == this.SIZELINE * this.SIZECOL;
      }

    /**
     * Check the complete board for draw condition
     * @return true if a draw condition was found
     */
    public int points(){
        int play = ((this.turnCounter - 1) / 2) + 1;
        int points = 42 - play;

        return points;

    }

    /**
     * Starts the board with all the positions empty
     */
    private void newGame(int line, int col) {
       for (int i = 0; i < this.SIZELINE; i++) {
           for (int j = 0; j < this.SIZECOL; j++) {
               if(isWinPosition(line, col) || draw()) {
                   this.boardData[i][j] = Cell.EMPTY;
               }
           }
       }
    }
}

