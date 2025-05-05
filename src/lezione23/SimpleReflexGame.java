package lezione23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class SimpleReflexGame extends JFrame implements Observer {

    private final ReflexModel model = new ReflexModel();
    private final int WIDTH = 800, HEIGHT = 200;

    public SimpleReflexGame() {
        super("Reflex Trainer");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        model.addObserver(this);
        model.start();

        // Listen for spacebar
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    model.checkHit();
                }
            }
        });

        // Animation loop
        new Timer(16, e -> model.tick()).start(); //60fps
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        int ballX = (int) model.ballX;

        // Draw the line
        g2.setColor(Color.BLACK);
        g2.drawLine(50, 100, WIDTH - 50, 100);

        // Target zone in center
        int targetX = WIDTH / 2 - 20;
        g2.setColor(Color.RED);
        g2.fillRect(targetX, 90, 40, 20);

        // Ball
        g2.setColor(Color.BLUE);
        g2.fillOval(ballX - 10, 90, 20, 20);

        // Status
        if (model.status != null) {
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            g2.setColor(Color.BLACK);
            g2.drawString(model.status, WIDTH / 2 - 40, 60);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleReflexGame::new);
    }
}

class ReflexModel extends Observable {
    double ballX = 50;
    double velocity = 5;
    boolean running = true;
    String status = null;
    private final int WIDTH = 800;

    void start() {
        ballX = 50;
        velocity = 1.5;
        running = true;
        status = null;
        notifyChange();
    }

    void tick() {
        if (!running) return;

        double center = WIDTH / 2.0;

        // Acceleration near center
        double accel = 1;
        if (ballX < center) velocity += accel;
        else velocity -= accel;

        velocity = Math.max(1.0, Math.min(velocity, 20.0));
        ballX += velocity;

        if (ballX > WIDTH - 50) {
            running = false;
            status = "Too late!";
        }

        notifyChange();
    }

    void checkHit() {
        if (!running) return;

        double center = WIDTH / 2.0;
        double tolerance = 20.0;

        if (Math.abs(ballX - center) < tolerance) {
            status = "HIT!";
        } else {
            status = "MISS!";
        }
        running = false;
        notifyChange();
    }

    private void notifyChange() {
        setChanged();
        notifyObservers();
    }
}
