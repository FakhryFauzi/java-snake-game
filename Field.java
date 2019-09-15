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

    boolean inGame = true;

    int score = 0;

    // Method to generate the apple in a random location
    void createApple() {
        appleY = new Random().nextInt(width / dot_size) * dot_size;
        appleX = new Random().nextInt(width / dot_size) * dot_size;

        //TODO - make apple appear not in the same location as the snake
         
        // System.out.println(appleY);
        // System.out.println(appleX);
    }

    void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * dot_size;
            y[i] = 48;
        }
        createApple();
        timer = new Timer(150, this); //increase the value if you want the snake to go faster
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkCollisions();
            checkApple();
            move();
        }

        repaint();

    }

    void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++; // increase the length of the snake if it hits the apple
            score++;
            createApple();
        }
    }

    void checkCollisions() {

        for (int i = dots; i > 0; i--) {
            if (i < 4 && x[0] == x[i] && y[0] == y[i]) { // 4 is used since it requires more than 4 dots of the snake to
                                                         // hit itself
                inGame = false;
            }
        }

        // check if the snake hits the borders
        if (y[0] > width) {
            inGame = false;
        }
        if (x[0] > width) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
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
            y[0] = y[0] - dot_size; // for some reason this needs to be negative
        }
        if (down) {
            y[0] = y[0] + dot_size;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        }else{
            //want to show game over message if its not showing the snake
            String str = "Game Over";
            Font f = new Font("Helvetica",Font.BOLD,14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str, 125, width/2);
            g.drawString("score: "+score,125,width/2 + 20);
        }
    }

    // constructor
    public Field() {
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener((KeyListener) new FieldKeyListener());
        setFocusable(true);
        setPreferredSize(new Dimension(345,345));
    }

    public void loadImages() {
        ImageIcon appleImage = new ImageIcon("apple.png");
        apple = appleImage.getImage();
        ImageIcon dotImage = new ImageIcon("dot.png");
        dot = dotImage.getImage();
    }

    class FieldKeyListener extends KeyAdapter {

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