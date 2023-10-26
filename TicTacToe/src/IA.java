import java.util.Random;


/**
 * Clase de la IA
 * @author metal
 */
public class IA {
    private String iaLevel;
    private char iaSymbol;
     /**
      * Constructor de la clase
      * @param iaLevel
      * @param iaSymbol 
      */
      public IA(String iaLevel, char iaSymbol) {
        this.iaLevel = iaLevel;
        this.iaSymbol = iaSymbol;
    }

    
    public void setSymbol(char symbol) {
        this.iaSymbol = symbol;
    }
  /**
   * Este metedo define los movimientos que se hacen de acorde al nivel
   * de la ia en el tablero
   * @param board 
   */
    public void makeMove(Board board) {
        if (iaLevel.equals("Fácil")) {
            makeRandomMove(board);
        } else if (iaLevel.equals("Medio")) {
            // Implementa una estrategia de nivel medio aquí
        } else if (iaLevel.equals("Difícil")) {
            // Implementa una estrategia de nivel difícil aquí
        }
    }
                  /**
                   * metodo que utiliza el board y genera un movimiento 
                   * aleatorio
                   * @param board 
                   */
    private void makeRandomMove(Board board) {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!board.isPositionAvailable(row, col));

        board.placeSymbol(row, col, iaSymbol);
    }

    public String getIaLevel() {
        return iaLevel;
    }

    public void setIaLevel(String iaLevel) {
        this.iaLevel = iaLevel;
    }

    public char getIaSymbol() {
        return iaSymbol;
    }

    public void setIaSymbol(char iaSymbol) {
        this.iaSymbol = iaSymbol;
    }

   

    public IA() {
    }

    @Override
    public String toString() {
        return "IA{" + "iaLevel=" + iaLevel + ", iaSymbol=" + iaSymbol + '}';
    }
    
}
