/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author wilzo
 */
public class Balls extends JPanel implements MouseListener {

    private ArrayList<Ball> balls;

    public Balls() {
        super();
        balls = new ArrayList<>();
        addMouseListener(this);
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Iterator<Ball> it = balls.iterator();
        while (it.hasNext()) {
            it.next().paint(g2d);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Balls");
        Balls b = new Balls();
        frame.add(b);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true) {
            b.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        Ball b = new Ball(x, y, this);
        balls.add(b);
        Thread t = new Thread(b);
        t.start();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //do nothing
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //do nothing
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //do nothing
    }

}
