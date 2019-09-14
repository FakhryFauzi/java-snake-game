import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.math.*;

//add field to the java program
public class Field extends JPanel {

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

    // Method to generate the apple in a random location
    void createApple() {
        appleY = new Random().nextInt(width / dot_size) * dot_size;
        appleX = new Random().nextInt(width / dot_size) * dot_size;
    }

    void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 50 - i * dot_size;
            y[i] = 50;
        }
        createApple();
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
    }

    public void loadImages() {
        ImageIcon appleImage = new ImageIcon("apple.png");
        apple = appleImage.getImage();
        ImageIcon dotImage = new ImageIcon("dot.png");
        dot = dotImage.getImage();
    }
}