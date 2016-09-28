/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author wilzo
 */
public class Ball implements Runnable {

    private final Color[] colours = new Color[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW, Color.WHITE};
    private final Random r = new Random();
    private Balls b;
    private int size;//the diameter of the ball
    private int x, y;//position of the ball
    private int dx, dy;//move per pixel horizontally and vertically
    private int speed;
    private Color color;

    public Ball(int x, int y, Balls b) {
        this.size = r.nextInt(50) + 10;
        this.x = x;
        this.y = y;
        this.dx = this.dy = this.speed = 2;
        this.b = b;
        this.color = randomColor();
    }

    private Color randomColor() {
        int i = r.nextInt(colours.length);
        return colours[i];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public void move() {
        
        if (x + dx < 0) {
            dx = speed;
            ++this.speed;
        }
        if (x + dx > b.getWidth() - size) {
            dx = -speed;
            ++this.speed;
        }
        if (y + dy < 0) {
            dy = speed;
            ++this.speed;
        }
        if (y + dy > b.getHeight() - size) {
            dy = -speed;
            ++this.speed;
        }
        
        

        x = x + dx;
        y = y + dy;
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    @Override
    public void run() {
        while (speed < 30) {
            move();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        b.getBalls().remove(this);
    }
}
