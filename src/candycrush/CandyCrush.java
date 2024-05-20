package candycrush;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class CandyCrush extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    public static boolean inMenu = true;
    sound bgSound = null;
    Image image;
    Graphics2D g;

    Menu m = new Menu();

    public static void main(String[] args) {
        CandyCrush frame = new CandyCrush();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CandyCrush Game");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("candyLogo.jpg"));
        frame.setVisible(true);
    }

    public CandyCrush() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    if (!inMenu)
                    Board.AddPiece(e.getX(), e.getY());
                    Menu.ButtonsManager(e.getX(), e.getY());
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.VK_ESCAPE == e.getKeyCode()) {
                    reset();
                }
                repaint();
            }
        });
        init();
        start();
    }

    Thread relaxer;

    public void init() {
        requestFocus();
        Menu.Init(); // Initialize the menu to load the image
    }

    public void destroy() {
    }

    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (!inMenu) {
            g.setColor(Color.cyan);
            g.fillRect(0, 0, Window.xsize, Window.ysize);

            int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
            int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
            g.setColor(Color.white);
            g.fillPolygon(x, y, 4);
            g.setColor(Color.red);
            g.drawPolyline(x, y, 5);

            if (animateFirstTime) {
                gOld.drawImage(image, 0, 0, null);
                return;
            }

            Board.Draw(g);
        } else {
            m.Draw(g, this);
        }

        gOld.drawImage(image, 0, 0, null);
    }

    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = .1;
            int milliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
            }
        }
    }

    public void reset() {
        Board.Reset();
        Player.Reset();
    }

    public void animate() {
    //

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            bgSound = new sound("MenuMusic.wav");
            reset();
        }
        if (bgSound.donePlaying)
            bgSound = new sound("MenuMusic.wav");
    }

    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }

    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
}
