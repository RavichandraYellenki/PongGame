import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame()
    {
       setTitle("PONG GAME");
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       getContentPane().setBackground(Color.black);

       //creating a panel object and adding it to frame
       MyPanel panel = new MyPanel();
       add(panel);


       setResizable(false);
       pack();                              //restricts frame to the size of panel
       setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        //creating a frame and calling constructor for functionalities
        MyFrame g = new MyFrame();
    }
}
