import javax.swing.*;

public class Main extends JFrame{
    public static void main(String[] args) {
        Main window = new Main();
    }
    
    //Create a window object using the javax.swing plugins in java
    //This will create the game window
    
    public Main(){
        add(new Field());
        setTitle("Java Snake Game");
        setLocation(300,500); //set the location within the screen
        setSize(320, 345); //set the size of the window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}