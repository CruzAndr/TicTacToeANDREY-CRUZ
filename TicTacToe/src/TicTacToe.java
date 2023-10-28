import java.util.Random;
import java.util.Scanner;

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
    private int totalMoves; // Nuevo atributo para contar los movimientos

    public TicTacToe(GameMode gameMode) {
        this.gameMode = gameMode;
        this.board = new Board();
        this.gameStatus = EN_CURSO;
        this.totalMoves = 0; // Inicializar el contador de movimientos

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

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido profe al TcTacToe mas insano");

        Player currentPlayer = player1;

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
                                + " arreglo del gatito.");
                        System.out.println("Por gracioso lo salto");
                        scanner.nextLine(); // Limpiar el búfer de entrada
                        continue; // Volver a pedir los valores
                    }
                } while (!isValidMove(row, col));

                char symbol = currentPlayer.getSymbol();
                gameLogic.makeMove(row, col, symbol);
                board.placeSymbol(row, col, symbol);
                showMoves();
            } else if (currentPlayer == player2 && ia != null) {
                int[] move = getRandomMove();
                int row = move[0];
                int col = move[1];
                char symbol = currentPlayer.getSymbol();
                board.placeSymbol(row, col, symbol);
                showMoves();
            }

            if (gameLogic.checkWinner()) {
                board.displayBoard();
                winner = currentPlayer;
                gameStatus = FIN;
            }

            if (gameStatus != EN_CURSO) {
                break;
            }

            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }

            totalMoves++;

            if (totalMoves == 9) {
                System.out.println("Empate");
                gameStatus = FIN;
            }
        }

        if (gameMode.getGameMode().equals("Un jugador")) {
            if (winner == player2) {
                System.out.println("La IA te gano. MANCO");
            } else if (winner == player1) {
                System.out.println("El jugador 1 gano, es mejor que la IA.");
            } else {
                System.out.println("Empate");
            }
        } else if (gameMode.getGameMode().equals("Dos jugadores")) {
            if (winner == player1) {
                System.out.println("El jugador 1 nacio para esto.");
            } else if (winner == player2) {
                System.out.println("El jugador 2 gano, increible.");
            } else {
                System.out.println("Empate.");
            }
        }
    }

    private int[] getRandomMove() {
        int row, col;
        Random random = new Random();

        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));

        return new int[]{row, col};
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Movimiento no válido. "
                + "Ingrese valores entre 0 y 2");
            return false;
        } else if (!board.isPositionAvailable(row, col)) {
            System.out.println("Esa casilla ya esta llena, ingrese otra");
            return false;
        }
        return true;
    }

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
                System.out.println("Error: Ingresa un numero, no una palabra. "
                        + "POR FAVOR");
                scanner.nextLine();
                continue;
            }

            GameMode gameMode;
            if (gameModeChoice == 1) {
                gameMode = new GameMode("Dos jugadores", 0, "X");
            } else if (gameModeChoice == 2) {
                gameMode = new GameMode("Un jugador", 1, "X");
            } else {
                System.out.println("Opción no valida. Por favor, "
                        + "selecciona 1 o 2.");
                continue;
            }

            TicTacToe ticTacToe = new TicTacToe(gameMode);
            ticTacToe.startGame();

            System.out.print("Ya que has ganado, "
                    + "Deseas jugar de nuevo? (S/N): ");
            char playAgain = scanner.next().charAt(0);

            if (playAgain != 'S' && playAgain != 's') {
                System.out.print("Hasta la vista.");
                break;
            }
        } while (true);
    }
}
