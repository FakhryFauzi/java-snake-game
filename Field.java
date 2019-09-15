import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//add field to the java program
public class Field extends JPanel implements ActionListener {

    int width = 320;
    int dot_size = 16;
    int field_size = 320 / 16 * 320 / 16;
    Image dot;
    Image apple;

    int appleX;
    int appleY;
    int[] x = new int[field_size];
    int[] y = new int[field_size];
    int dots;

    boolean left = false;
    boolean right = true;
    boolean up = false;
    boolean down = false;

    Timer timer;

    // Method to generate the apple in a random location
    void createApple() {
        appleY = new Random().nextInt(width / dot_size) * dot_size;
        appleX = new Random().nextInt(width / dot_size) * dot_size;
    }

    void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * dot_size;
            y[i] = 48;
        }
        createApple();
        timer = new Timer(150, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    void move() {

        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (left) {
            x[0] = x[0] - dot_size;
        }
        if (right) {
            x[0] = x[0] + dot_size;
        }
        if (up) {
            y[0] = y[0] - dot_size;
        }
        if (down) {
            y[0] = y[0] + dot_size;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(apple, appleX, appleY, this);
        for (int i = 0; i < dots; i++) {
            g.drawImage(dot, x[i], y[i], this);
        }
    }

    // constructor
    public Field() {
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener((KeyListener) new FieldKeyListener());
        setFocusable(true);
    }

    public void loadImages() {
        ImageIcon appleImage = new ImageIcon("apple.png");
        apple = appleImage.getImage();
        ImageIcon dotImage = new ImageIcon("dot.png");
        dot = dotImage.getImage();
    }

    class FieldKeyListener extends KeyAdapter{

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                up = true;
                left = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                left = false;
                down = true;
            }
        }
    }
}