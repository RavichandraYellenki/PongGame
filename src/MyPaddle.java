import java.awt.*;
import java.awt.event.KeyEvent;

public class MyPaddle extends Rectangle {

    int id;
    int yVelocity;
    int speed = 10;

    MyPaddle(int x, int y, int paddleWidth, int paddleHeight, int id){
      super( x, y, paddleWidth, paddleHeight);
      this.id = id;
    }
    public void keyPressed(KeyEvent e){
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                }
                break;

            case 2 :
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
                break;
        }
    }

    private void setYDirection(int Direction) {
        yVelocity = Direction;
    }

    public void keyReleased(KeyEvent e){
        switch (id) {
            case 1 :
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;
            case 2 :
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }


    public void move(){
        y = y + yVelocity;
    }

    public void draw(Graphics g) {
        if(id==1){
            g.setColor(Color.blue);
        }
        else{
            g.setColor(Color.green);
        }
        g.fillRect(x,y,width,height);
    }
}
