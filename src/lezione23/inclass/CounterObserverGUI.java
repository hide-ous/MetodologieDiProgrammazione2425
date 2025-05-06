package lezione23.inclass;

import javax.swing.*;
import java.awt.*;

public class CounterObserverGUI extends JFrame implements Observer{
    private final Counter counter;
    private final JLabel counterLabel;

    public CounterObserverGUI(Counter counter) {
        this.counter = counter;
        setTitle("Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        counterLabel = new JLabel(String.valueOf(counter.getState()));
        counterLabel.setFont(new Font("Arial", Font.BOLD, 120));
        add(counterLabel);

        JButton incrementer = new JButton("MORE!");
        incrementer.setFont(new Font("Arial", Font.BOLD, 40));
        incrementer.addActionListener(e -> counter.modify());
        add(incrementer);

        pack();
        setVisible(true);
    }

    @Override
    public void update() {
        counterLabel.setText(String.valueOf(counter.getState()));
    }
}
