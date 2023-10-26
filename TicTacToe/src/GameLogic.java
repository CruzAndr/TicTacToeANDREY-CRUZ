
/**
 * Esta es la clase sobre la logica del juego
 * @author metal
 */
public class GameLogic {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private char[][] board;

    /**
     * Constructor de la clase GameLogic
     * @param player1
     * @param player2 
     */
    public GameLogic(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = new char[3][3];
        initializeBoard();
    }

    public GameLogic(Player player1, Player player2, Player currentPlayer, 
            char[][] board) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = currentPlayer;
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
   /**
    * metodo que inicializa el tablero 
    */
    public void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }
    /**
     * metodo que genera el movimiento, lo lee y cambia de turno
     * @param row
     * @param col
     * @param symbol 
     */
    public void makeMove(int row, int col, char symbol) {
        if (isPositionAvailable(row, col)) {
            board[row][col] = symbol;
            switchTurn();
        }
    }
    /**
     * metodo que revisa la posicion y verifica que este disponible
     * @param row
     * @param col
     * @return 
     */
    public boolean isPositionAvailable(int row, int col) {
        return board[row][col] == ' ';
    }

    /**
     * metodo que contiene la logica del juego y verifica quien gana
     * @return 
     */
    public boolean checkWinner() {
        char[][] board = this.board; // Obtén el tablero del juego
        char playerSymbol = player1.getSymbol(); // Símbolo del jugador
        char iaSymbol = player2.getSymbol(); // Símbolo de la IA

        // Verificar filas y columnas para el jugador
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == playerSymbol && board[i][1] 
                    == playerSymbol && board[i][2] == playerSymbol)
                    || (board[0][i] == playerSymbol && board[1][i] 
                    == playerSymbol && board[2][i] == playerSymbol)) {
                return true; // Ganador jugador en fila o columna
            }
        }

        // Verificar diagonales para el jugador
        if ((board[0][0] == playerSymbol && board[1][1] == 
                playerSymbol && board[2][2] == playerSymbol)
                || (board[0][2] == playerSymbol && board[1][1]
                == playerSymbol && board[2][0] == playerSymbol)) {
            return true; // Ganador jugador en diagonal
        }

        // Verificar filas y columnas para la IA
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == iaSymbol && board[i][1]
                    == iaSymbol && board[i][2] == iaSymbol)
                    || (board[0][i] == iaSymbol && board[1][i] 
                    == iaSymbol && board[2][i] == iaSymbol)) {
                return true; // Ganador IA en fila o columna
            }
        }

        // Verificar diagonales para la IA
        if ((board[0][0] == iaSymbol && board[1][1] == 
                iaSymbol && board[2][2] == iaSymbol)
                || (board[0][2] == iaSymbol && board[1][1] == 
                iaSymbol && board[2][0] == iaSymbol)) {
            return true; // Ganador IA en diagonal
        }

        return false; // No hay ganador
    }
    /**
     * metodo que revisa si el tablero esta lleno o no para las posiciones
     * @return 
     */
    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false; // El tablero no está lleno
                }
            }
        }
        return true; // El tablero está lleno
    }
    /**
     * metodo que cambia de turno
     */
    public void switchTurn() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        if (checkWinner()) {
            return currentPlayer;
        }
        return null; // No hay ganador todavía
    }

    @Override
    public String toString() {
        return "GameLogic{" + "player1=" + player1 + ", player2=" +
                player2 + ", currentPlayer=" + currentPlayer + ", board=" 
                + board + '}';
    }
    
    
}
