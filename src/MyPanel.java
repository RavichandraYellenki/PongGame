import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;



public class MyPanel extends Panel implements Runnable {

    int width = 1000;
    int height = (int)(width*0.555);
    Dimension screen = new Dimension(width,height);

    int PaddleWidth = 25;
    int PaddleHeight = 100;
    int ballDiameter = 20;



    Image image;
    Graphics graphics;

     MyPaddle p1,p2;

     MyBall ball;

     Score score = new Score(width, height);

    Thread GameThread;
    MyPanel() {
        setPreferredSize(screen);
        GameThread = new Thread(this);

        addKeyListener(new listener());

        setFocusable(true);
        GameThread.start();

        newPaddle();
        newBall();
    }

    private void newBall() {
        Random random = new Random();
        ball = new MyBall(width/2, random.nextInt(height-ballDiameter),ballDiameter,ballDiameter);
    }

    private void newPaddle() {
        p1 = new MyPaddle(0,(height- PaddleHeight)/2,PaddleWidth,PaddleHeight,1);
        p2 = new MyPaddle(width-PaddleWidth,(height-PaddleHeight)/2,PaddleWidth,PaddleHeight,2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    private void draw(Graphics g) {
      p1.draw(g);
      p2.draw(g);
      ball.draw(g);
      score.draw(g);

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ticks = 60;
        double nanoSec = 1000000000/ticks;
        double delta = 0;
        while (true)
        {
            long nowTime = System.nanoTime();
            delta += (nowTime-lastTime)/nanoSec;

            if(delta >= 1)
            {
                repaint();
                move();
                CheckCollision();
                delta--;

            }
            lastTime = nowTime;
        }
    }

    private void CheckCollision() {
         if(ball.y <= 0)
         {
             ball.setYDirection(-ball.yVelocity);
         }
         if(ball.y >= height-ballDiameter)
         {
             ball.setYDirection(-ball.yVelocity);
         }
         if(ball.intersects(p1))
         {
             ball.xVelocity = -ball.xVelocity;
             ball.xVelocity++;
             if(ball.yVelocity > 0)
             {
                 ball.yVelocity++;
             }
             else
             {
                 ball.yVelocity--;
             }
             ball.setYDirection(ball.yVelocity);
             ball.setXDirection(ball.xVelocity);
         }
        if(ball.intersects(p2))
        {
            ball.xVelocity = -ball.xVelocity;
            ball.xVelocity--;
            if(ball.yVelocity > 0)
            {
                ball.yVelocity++;
            }
            else
            {
                ball.yVelocity--;
            }
            ball.setYDirection(ball.yVelocity);
            ball.setXDirection(ball.xVelocity);
        }
        if (p1.y<=0)
        {
         p1.y =0;
        }
        if (p1.y >= height-PaddleHeight)
        {
           p1.y =height-PaddleHeight;
        }
        if (p2.y<=0)
        {
            p2.y =0;
        }
        if (p2.y >= height-PaddleHeight)
        {
            p2.y =height-PaddleHeight;
        }
        if(ball.x >= width-ballDiameter)
        {
            newBall();
            newPaddle();
            score.player1++;
        }
        if(ball.x <=0)
        {
            newBall();
            newPaddle();
            score.player2++;
        }

    }

    private void move() {
        p1.move();
        p2.move();
        ball.move();
    }

    public class listener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {

            p1.keyPressed(e);
            p2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            p1.keyReleased(e);
            p2.keyReleased(e);
        }
    }
}
