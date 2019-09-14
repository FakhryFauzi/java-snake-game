import javax.swing.*;
import java.awt.*;

//add field to the java program
public class Field extends JPanel{

    int width  = 320;
    int dot_size = 16;
    int field_size = (320/16) * (320/16);
    Image dot;
    Image apple;

    public Field(){
        setBackground(Color.black);
    }
}