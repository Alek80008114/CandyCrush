package candycrush;

import java.awt.*;

public class Highlight {
    public enum Direction {Right,Down};
    private int row;
    private int column;
    private Direction direction;
    private int numBoxes;

    public Highlight()
    {
    }

    public Highlight(int _row,int _column,int _numBoxes,Direction _direction)
    {
        row = _row;
        column = _column;
        numBoxes = _numBoxes;
        direction = _direction;
    }


    public void draw(Graphics2D g,int xdelta,int ydelta)
    {
        g.setColor(Color.gray);
        if (direction == Direction.Right)
        {
            for (int i=0;i<numBoxes;i++)
                g.fillRect(Window.getX((column+i)*xdelta), Window.getY(row*ydelta), xdelta , ydelta);
        }
        else if (direction == Direction.Down)
        {
            for (int i=0;i<numBoxes;i++)
                g.fillRect(Window.getX(column*xdelta), Window.getY((row+i)*ydelta) , xdelta, ydelta);
        }

    }


}

