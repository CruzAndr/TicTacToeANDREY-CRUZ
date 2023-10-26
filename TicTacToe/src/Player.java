
/**
 * Esta es la clase sobre el jugador
 * @author metal
 */
public class Player {
    private int playerScore;
    private String name;
    private char symbol; // Símbolo del jugador (por ejemplo, 'X' o 'O')
 
    /**
     * Constructor de la clase player
     * @param name
     * @param symbol 
     */
    public Player(String name, char symbol) {
        this.name = name;
        this.playerScore = 0;
        this.symbol = symbol;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Player(int playerScore, String name, char symbol) {
        this.playerScore = playerScore;
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Player{" + "playerScore=" + playerScore + ", name=" + name + ", symbol=" + symbol + '}';
    }

    public Player() {
    }
    
}
