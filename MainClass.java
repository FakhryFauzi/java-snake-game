import javax.swing.*;

public class MainClass extends JFrame{
    public static void main(String[] args) {
        MainClass window = new MainClass();
    }
    
    //Create a window object using the javax.swing plugins in java
    //This will create the game window
    
    public MainClass(){
        add(new Field());
        setTitle("Java Snake Game");
        setLocation(300,500); //set the location within the screen
        setSize(345, 360); //set the size of the window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}