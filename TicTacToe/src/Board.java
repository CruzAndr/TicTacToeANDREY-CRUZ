import java.util.Random;

/**
 * Esta es la clase sobre el tablero
 * @author metal
 */
public class Board {
    private char[][] boardGame;
   
    /**
     * constructor de la clase
     */
    public Board() {
        boardGame = new char[3][3];
        initializeBoard();
    }

    /**
     * metodo que inica el tablero 
     */
    public void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardGame[row][col] = ' ';
            }
        }
    }
    /**
     * metodo que muestra el tablero en la terminal
     */
    public void displayBoard() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(boardGame[row][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }
    /**
     * metodo que revisa disponibilidad de espacios
     * @param row
     * @param col
     * @return 
     */
    public boolean isPositionAvailable(int row, int col) {
        return boardGame[row][col] == ' ';
    }
    /**
     * metodo que toma la columna y fila y reescribe su ubicacion con
     * el simbolo
     * @param row
     * @param col
     * @param symbol 
     */
    public void placeSymbol(int row, int col, char symbol) {
        boardGame[row][col] = symbol;
    }
    /**
     * metodo que verifica quien gana
     * @param symbol
     * @return 
     */
    public boolean checkWinner(char symbol) {
        // Verificar filas
        for (int row = 0; row < 3; row++) {
            if (boardGame[row][0] == symbol && boardGame[row][1]
                    == symbol && boardGame[row][2] == symbol) {
                return true;
            }
        }

        // Verificar columnas
        for (int col = 0; col < 3; col++) {
            if (boardGame[0][col] == symbol &&
                    boardGame[1][col] == symbol && boardGame[2][col]
                    == symbol) {
                return true;
            }
        }

        // Verificar diagonales
        return (boardGame[0][0] == symbol &&
                boardGame[1][1] == symbol && boardGame[2][2] == 
                symbol) ||
               (boardGame[0][2] == symbol && boardGame[1][1] == 
                symbol && boardGame[2][0] == symbol);
    }
    /**
     * metodo que revisa si el tablero esta lleno
     * @return 
     */
    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (boardGame[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public char getSymbol(int row, int col) {
        return boardGame[row][col];
    }

    public void setSymbol(int row, int col, char symbol) {
        boardGame[row][col] = symbol;
    }

    public Board(char[][] boardGame) {
        this.boardGame = boardGame;
    }

    public char[][] getBoardGame() {
        return boardGame;
    }

    public void setBoardGame(char[][] boardGame) {
        this.boardGame = boardGame;
    }

    @Override
    public String toString() {
        return "Board{" + "boardGame=" + boardGame + '}';
    }
    
    
    
}

