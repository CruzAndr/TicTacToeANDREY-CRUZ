import java.util.Random;
import java.util.Scanner;

/**
 * Esta es la clase principl que ejecuta el juego
 * @author metal
 */
public class TicTacToe {

    private static final int EN_CURSO = 0;
    private static final int FIN = 1;

    private int gameStatus;
    private Player player1;
    private Player player2;
    private IA ia;
    private Board board;
    private GameLogic gameLogic;
    private Player winner;
    private final GameMode gameMode;
    
   /**
    * Este es el constructor de la clase TicTacToe
    * @param gameMode 
    */
    public TicTacToe(GameMode gameMode) {
        this.gameMode = gameMode;
        this.board = new Board();
        this.gameStatus = EN_CURSO;

        if (gameMode.getGameMode().equals("Un jugador")) {
            this.player1 = new Player("Jugador 1", gameMode.getPlayerSymbol()
                    .charAt(0));
            this.ia = new IA(Integer.toString(gameMode.getIaLevel()), (player1.
                    getSymbol() == 'X' ? 'O' : 'X'));
            this.player2 = new Player("IA", (player1.getSymbol() == 'X') ? 
                    'O' : 'X');
        } else if (gameMode.getGameMode().equals("Dos jugadores")) {
            this.player1 = new Player("Jugador 1", gameMode.getPlayerSymbol().
                    charAt(0));
            this.player2 = new Player("Jugador 2", (player1.getSymbol() == 
                    'X' ? 'O' : 'X'));
            this.ia = null;
        }

        this.gameLogic = new GameLogic(player1, player2);
    }
        /**
         * metodo que inicia el juego y posee toda su relacon con el usuario
         */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido profe al Tic-Tac-Toe mas insano"
                + " del mundo");

        Player currentPlayer = player1; // Comenzamos con el jugador 1
        int turnCount = 0;

        while (gameStatus == EN_CURSO) {
            board.displayBoard();
            System.out.println("Turno de " + currentPlayer.getName() +
                    " (" + currentPlayer.getSymbol() + ")");
            if (currentPlayer == player1 || (currentPlayer == 
                    player2 && ia == null)) {
                int row = 0, col = 0;
                do {
                    try {
                        System.out.print("Ingrese la fila (0-2): ");
                        row = scanner.nextInt();
                        System.out.print("Ingrese la columna (0-2): ");
                        col = scanner.nextInt();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Error: Ingresa numeros validos del"
                                + "arreglo del gatito.");
                          System.out.println("Por gracioso lo salto");
                        scanner.nextLine(); // Limpiar el búfer de entrada
                        continue; // Volver a pedir los valores
                    }
                } while (!isValidMove(row, col));

                char symbol = currentPlayer.getSymbol(); // Obtén el símbolo del jugador actual
                gameLogic.makeMove(row, col, symbol); // Llama a makeMove con el símbolo
                board.placeSymbol(row, col, symbol); // Actualiza el tablero
                showMoves();
            } else if (currentPlayer == player2 && ia != null) {
                int[] move = getRandomMove();
                int row = move[0];
                int col = move[1];
                char symbol = currentPlayer.getSymbol(); // Obtén el símbolo del jugador actual
                board.placeSymbol(row, col, symbol); // Actualiza el tablero
                showMoves();
            }

            if (gameLogic.checkWinner()) {
                board.displayBoard();
                winner = currentPlayer;
                gameStatus = FIN;
            }

            // Cambiar el turno en función del recuento de turnos
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }

            turnCount++; // Aumentar el recuento de turnos

            // Asegurarse de que el juego no continúe indefinidamente en caso de un error
            if (turnCount > 9) {
                System.out.println("Empate");
                gameStatus = FIN;
            }
        }

       /**
        * Este codigo demuestra el mensaje del ganador
        */
        if (gameMode.getGameMode().equals("Un jugador")) {
            if (winner == player2) {
                System.out.println("La IA te gano, manco.");
            } else if (winner == player1) {
                System.out.println("El jugador 1 gano, es más bueno que la IA");
            } else {
                System.out.println("Empate");
            }
        } else if (gameMode.getGameMode().equals("Dos jugadores")) {
            if (winner == player1) {
                System.out.println("El jugador 1 nació para esto.");
            } else if (winner == player2) {
                System.out.println("El jugador 2 gano.");
            } else {
                System.out.println("Empate.");
            }
        }
    }
                
    private int[] getRandomMove() {
        int row, col;
        Random random = new Random();

        do {
            row = random.nextInt(3); // Genera un número aleatorio entre 0 y 2
            col = random.nextInt(3); // Genera un número aleatorio entre 0 y 2
        } while (!isValidMove(row, col)); // Verifica si el movimiento es válido

        return new int[]{row, col};
    }
/**
                 * metodo que lee las posiciones del arreglo y las devuelve
                 * @return 
                 */
    private boolean isValidMove(int row, int col) {
    if (row < 0 || row > 2 || col < 0 || col > 2) {
        System.out.println("Movimiento no válido. "
                + "Ingrese valores entre 0 y 2");
        return false;
    } else if (!board.isPositionAvailable(row, col)) {
        System.out.println("Esa casilla ya está llena, ingrese otra");
        return false;
    }
    return true;
}
                    /**
                     * Metodo que muestra los movimientos en tablero
                     */
    public void showMoves() {
        System.out.println("Movimientos del gatito:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board.getSymbol(i, j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Seleccione el modo de juego (1 para Jugador vs. "
                    + "Jugador, 2 para Jugador vs. IA): ");
            int gameModeChoice;
            try {
                gameModeChoice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingresa un numero no una palabra,"
                        + "POR FAVOR");
                scanner.nextLine(); // Limpiar el búfer de entrada
                continue; // Volver a pedir la selección del modo de juego
            }

            GameMode gameMode;
            if (gameModeChoice == 1) {
                gameMode = new GameMode("Dos jugadores", 0, "X");
            } else if (gameModeChoice == 2) {
                gameMode = new GameMode("Un jugador", 1, "X");
            } else {
                System.out.println("Opción no válida. Por favor,"
                        + " selecciona 1 o 2. MANCO");
                continue; // Volver a pedir la selección del modo de juego
            }

            TicTacToe ticTacToe = new TicTacToe(gameMode);
            ticTacToe.startGame();

            System.out.print("Ya que ganaste, Deseas jugar de nuevo? (S/N): ");
            char playAgain = scanner.next().charAt(0);

            if (playAgain != 'S' && playAgain != 's') {
                System.out.print("Hasta la vista beiby");
                break;
            }
        } while (true);
    }
}
