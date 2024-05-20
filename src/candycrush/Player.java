package candycrush;

import java.awt.Color;
public class Player {
    private static int numPlayers=2;
    private static Player players[] = new Player[numPlayers];
    private static Player currentPlayer;
    private Color color;
    private int score = 0;
    //Class methods.
    public static void Reset() {
//If we have not created any instances yet, create the instances of the 2 players.
//Have the first player be the current player.
        if (players[0] == null) {
            players[0] = new Player(Color.red);
            players[1] = new Player(Color.yellow);
        }
        currentPlayer = players[0];
    }
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    public static void switchCurrentPlayer() {
        if (currentPlayer == players[0])
            currentPlayer = players[1];
        else
            currentPlayer = players[0];
    }
    public static Player getPlayer1() {
        return players[0];
    }
    public static Player getPlayer2() {
        return players[1];
    }

    public Player(Color _color) {
        color = _color;
    }
    //accessor methods.
    public Color getColor() {
        return (color);
    }
    public int getScore() {
        return (score);
    }
    //mutator methods.
    public void addScore() {
        score++;
    }
}