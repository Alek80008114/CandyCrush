package candycrush;

import java.awt.*;

public class Menu {
    private static Image candyLogo;
    private static Image candyBack;
    int xpos1;
    int ypos1;
    int xpos2;
    int ypos2;
    static int button1X = 204;
    static int button1Y = 310;
    static int button1Xscale = 250;
    static int button1Yscale = 75;
    static int button2X = 204;
    static int button2Y = 390;
    static int button2Xscale = 250;
    static int button2Yscale = 75;
    static int button3X = 204;
    static int button3Y = 470;
    static int button3Xscale = 250;
    static int button3Yscale = 75;

    private final static int NUM_ROWS = 8;
    private final static int NUM_COLUMNS = 8;

    Menu() {
        xpos1 = 300;
        ypos1 = 400;
        xpos2 = 300;
        ypos2 = 300;
    }

    public static void Init() {
            candyLogo = Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/candycrush/Images/candyLogo.png"));
        candyBack = Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/candycrush/Images/candyBackground.jpg"));
    }

    public static void ButtonsManager(int _xpos,int _ypos){


        if (_xpos > button1X && _xpos < button1X+button1Xscale && _ypos > button1Y && _ypos < button1Y +button1Yscale) {
            System.out.println(_xpos + _ypos);
            CandyCrush.inMenu = false;
        }

    }

    public void Draw(Graphics2D g, CandyCrush thisObj) {
        drawImage(g, thisObj, candyBack, Window.getX(xpos2), Window.getYNormal(ypos2), 0.0, 1, 1);
        // if image fails
       // g.setColor(Color.BLACK);
       // g.fillRect(0, 0, Window.xsize, Window.ysize);
        // if image fails END
        drawImage(g, thisObj, candyLogo, Window.getX(xpos1), Window.getYNormal(ypos1), 0.0, 0.34, 0.34);
        g.setColor(Color.ORANGE);
        g.fillRect(button1X,button1Y,button1Xscale,button1Yscale);
        g.fillRect(button2X,button2Y,button2Xscale,button2Yscale);
        g.fillRect(button3X,button3Y,button3Xscale,button3Yscale);
        g.setColor(Color.WHITE);
        g.setFont (new Font ("ARIAL BOLD",Font.PLAIN, 45));
        g.drawString("PLAY", button1X+60,button1Y+50);
        g.drawString("TUTORIAL", button2X+10,button2Y+50);
        g.drawString("CREDITS", button3X+25,button3Y+50);

    }

    public void drawImage(Graphics2D g, CandyCrush thisObj, Image image, int xpos, int ypos, double rot, double xscale, double yscale) {
        int width = image.getWidth(thisObj);
        int height = image.getHeight(thisObj);
        if (width == -1 || height == -1) return; // Image not loaded yet



        g.translate(xpos, ypos);
        g.rotate(rot * Math.PI / 180.0);
        g.scale(xscale, yscale);
        g.drawImage(image, -width / 2, -height / 2, width, height, thisObj);
        g.scale(1.0 / xscale, 1.0 / yscale);
        g.rotate(-rot * Math.PI / 180.0);
        g.translate(-xpos, -ypos);
    }
}
