// Board.java

package candycrush;
import java.awt.*;

public class Board {
    private final static int NUM_ROWS = 8;
    private final static int NUM_COLUMNS = 8;
    private final static int NUM_CONNECT = 4;

    private static Highlight highlight = null;

    private static Piece board[][] = new Piece[NUM_ROWS][NUM_COLUMNS];

    private static boolean gameOver;
    public static void Reset() {


        gameOver = false;
//clear the board.
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol] = null;

        highlight = null;
// highlight = new Highlight(3,2,NUM_CONNECT,Highlight.Direction.Down);

    }

    public static void AddPiece(int xpixel,int ypixel) {
        if (gameOver)
            return;

        int ydelta = Window.getHeight2() / NUM_ROWS;
        int xdelta = Window.getWidth2() / NUM_COLUMNS;
        int xpixelOffset = xpixel - Window.getX(0);
        int ypixelOffset = ypixel - Window.getY(0);

        if (xpixelOffset < 0 || xpixelOffset > Window.getWidth2() ||
                ypixelOffset < 0 || ypixelOffset > Window.getHeight2())
            return;

// int row = ypixelOffset/ydelta;
        int col = xpixelOffset/xdelta;

        if (board[0][col] != null)
            return;
        int row = NUM_ROWS-1;
        while (board[row][col] != null) {
            row--;
        }

        board[row][col] = new Piece(Player.getCurrentPlayer().getColor());

        if (CheckWin()) {
            gameOver = true;
            System.out.println("Winner");
            Player.getCurrentPlayer().addScore();
        }
        else
            Player.switchCurrentPlayer();
    }


    public static boolean CheckWin()
    {
        int numConsecutive = 0;
        Color colorMatch = null;
        int startRowWin = 0;
        int startColumnWin = 0;

//Check for horizontal win.
        for (int row=0;row<NUM_ROWS;row++)
        {
            for (int col=0;col<NUM_COLUMNS;col++)
            {

                if (board[row][col] == null)
                {
                    numConsecutive = 0;
                    colorMatch = null;
                }
                else if (board[row][col].getColor() == colorMatch)
                {
                    numConsecutive++;
                    if (numConsecutive == NUM_CONNECT)
                    {
                        highlight = new Highlight(startRowWin,startColumnWin,NUM_CONNECT,Highlight.Direction.Right);
                        return true;
                    }
                }
                else if (board[row][col].getColor() != colorMatch)
                {
                    startRowWin = row;
                    startColumnWin = col;
                    numConsecutive = 1;
                    colorMatch = board[row][col].getColor();
                }

            }
            colorMatch = null;
            numConsecutive = 0;
        }

//Check for vertical win.
        colorMatch = null;
        numConsecutive = 0;
        for (int col=0;col<NUM_COLUMNS;col++)
        {
            for (int row=0;row<NUM_ROWS;row++)
            {

                if (board[row][col] == null)
                {
                    numConsecutive = 0;
                    colorMatch = null;
                }
                else if (board[row][col].getColor() == colorMatch)
                {
                    numConsecutive++;
                    if (numConsecutive == NUM_CONNECT)
                    {
                        highlight = new Highlight(startRowWin,startColumnWin,NUM_CONNECT,Highlight.Direction.Down);

                        return true;

                    }
                }
                else if (board[row][col].getColor() != colorMatch)
                {
                    startRowWin = row;
                    startColumnWin = col;
                    numConsecutive = 1;

                    colorMatch = board[row][col].getColor();

                }



            }
            colorMatch = null;
            numConsecutive = 0;

        }

        return false;
    }




    public static void Draw(Graphics2D g) {
//draw grid
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;

        if (highlight != null)
            highlight.draw(g, xdelta, ydelta);

        g.setColor(Color.black);
        for (int zi = 1;zi<NUM_ROWS;zi++)
        {
            g.drawLine(Window.getX(0),Window.getY(zi*ydelta),
                    Window.getX(Window.getWidth2()),Window.getY(zi *ydelta));
        }

        for (int zi = 1;zi<NUM_COLUMNS;zi++)
        {
            g.drawLine(Window.getX(zi*xdelta),Window.getY(0),
                    Window.getX(zi*xdelta),Window.getY(Window.getHeight2 ()));
        }
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
            {
                if (board[zrow][zcol] != null)
                    board[zrow][zcol]. draw(g, zrow, zcol,xdelta, ydelta);
            }
        }

        g.setColor(Color.black);
        g.setFont(new Font("Arial",Font.PLAIN, 20));
        g.drawString("Score = " + Player.getPlayer1().getScore(), 40, 60);
        g.drawString("Score = " + Player.getPlayer2().getScore(), 520, 60);
        if (gameOver) {
            if (Player.getCurrentPlayer() == Player.getPlayer1())
                g.drawString("Player 1 Wins", 240, 60);
            else
                g.drawString("Player 2 Wins", 240, 60);
        }

    }
}