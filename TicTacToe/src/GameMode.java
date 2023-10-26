
/**
 * Clase sobre el modo de juego
 * @author metal
 */
public class GameMode {
    private String gameMode;
    private int iaLevel; // Cambia el tipo de dato de String a int
    private String playerSymbol;
    /**
     * constructor de la clase 
     * @param gameMode
     * @param iaLevel
     * @param playerSymbol 
     */
    public GameMode(String gameMode, int iaLevel, String playerSymbol) {
        this.gameMode = gameMode;
        this.iaLevel = iaLevel; // Actualiza el tipo de dato
        this.playerSymbol = playerSymbol;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getIaLevel() {
        return iaLevel;
    }

    public void setIaLevel(int iaLevel) {
        this.iaLevel = iaLevel;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public GameMode() {
    }

    @Override
    public String toString() {
        return "GameMode{" + "gameMode=" + gameMode 
                + ", iaLevel=" + iaLevel + ", playerSymbol="
                + playerSymbol + '}';
    }

  
}

