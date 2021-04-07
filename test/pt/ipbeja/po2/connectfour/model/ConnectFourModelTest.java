package pt.ipbeja.po2.connectfour.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Laura Melissa Bernardo Correia (17179)
 * @version 02/05/2019
 */

class ConnectFourModelTest {
    TestView VIEW;
    ConnectFourModel connectFourModel;

    @BeforeEach
    void setup() {
        this.VIEW = new TestView();
        this.connectFourModel = new ConnectFourModel(VIEW);
    }

    /**
     * Try to play on a column that is empty. The piece stays in the last line
     */
    @Test
    void teste01(){
        connectFourModel.placeSelected(2,5);
        assertEquals(5, connectFourModel.getLineToPlay());
    }

    /**
     * Try to play on a column that already has another pieces before. The piece stays above the others
     */
    @Test
    void teste02(){
        for (int i = 0; i < 3; i++) {
            connectFourModel.placeSelected(i, 1);
        }

        assertEquals(3, connectFourModel.getLineToPlay() );
    }

    /**
     * Try to play on a column that is complete, the board stays equal
     */
    @Test
    void teste03(){
        for (int i = 5; i > -1; i--) {
            connectFourModel.placeSelected(i, 1);
        }

        String a = t3();
        connectFourModel.placeSelected(0,1);
        String b = t3();
        assertEquals(a,b);
    }

    /**
     * A play that nobody wins
     */
    @Test
    void teste04(){
        connectFourModel.placeSelected(1, 3);
        assertFalse(connectFourModel.isWinPosition(connectFourModel.getLineToPlay(), 3));
    }

    /**
     * A play that the player wins with a horizontal line
     */
    @Test
    void teste05(){
        for (int i = 0; i < 4; i++) {
            for (int j = 5; j > 3 ; j--) {
                if(i == 3 && j == 4){
                    break;
                }
                connectFourModel.placeSelected(j, i);
            }
    }

    assertTrue(connectFourModel.isWinPosition(connectFourModel.getLineToPlay(),3));
    }

    /**
     * A play that the player wins with a vertical column
     */
    @Test
    void teste06(){
        for (int i = 5; i > 1; i--) {
            for (int j = 0; j < 2; j++) {
                if(i == 2 && j == 1){
                    break;
                }
                connectFourModel.placeSelected(i, j);
            }
        }
        assertTrue(connectFourModel.isWinPosition(connectFourModel.getLineToPlay(),0));
    }

    /**
     * A play that the player wins with a diagonal
     */
    @Test
    void teste07(){
        t7();
        assertTrue(connectFourModel.isWinPosition(connectFourModel.getLineToPlay(), 3));
    }

    /**
     * A play that finals the game with a draw
     */
    @Test
    void teste08(){
        t8();
        connectFourModel.placeSelected(0,6);
        assertTrue(connectFourModel.draw());
    }

    /**
     * Saves the player who playes (1 or 2) in a String
     */
    String t3() {
        String a = " ";
        for (int i = 0; i < 6; i++) {
            if (connectFourModel.boadState.get(i) == Cell.PLAYER1) {
                a += 1;
            } else {
                a += 2;
            }
        }
        return a;
    }

    /**
     * Put the pieces on the board on a certain way
     */
    void t7() {
        int col = 0;
        for (int i = 5; i > 3; i--) {
            connectFourModel.placeSelected(i, col);
        }
        col = 2;
        for (int i = 5; i > 0; i--) {
            connectFourModel.placeSelected(i, col);
        }
        col = 1;
        for (int i = 5; i > 1; i--) {
            connectFourModel.placeSelected(i, col);
        }
        col = 3;
        for (int i = 5; i > 0; i--) {
            connectFourModel.placeSelected(i, col);
        }
    }

    void t8() {
        for (int i = 0; i < 3; i++) {
            for (int j = 5; j > -1; j--) {
                connectFourModel.placeSelected(j, i);
            }
        }
        for (int i = 4; i < 7; i++) {
            for (int j = 5; j > -1; j--) {
                if(i == 0 && j == 6){
                    break;
                }
                connectFourModel.placeSelected(j, i);
            }
        }
        for (int i = 3; i < 4; i++) {
            for (int j = 5; j > -1; j--) {
                connectFourModel.placeSelected(j, i);
            }
        }
    }
}