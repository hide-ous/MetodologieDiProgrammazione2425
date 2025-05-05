package lezione23.mid;

import javax.swing.*;
import java.awt.*;

public class CounterApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Counter model = new Counter();

            JFrame frame = new JFrame("Contatore");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel("0");
            label.setFont(new Font("Arial", Font.BOLD, 40));
            label.setHorizontalAlignment(SwingConstants.CENTER);

            JButton button = new JButton("Incrementa");

            model.addObserver(newValue -> label.setText(String.valueOf(newValue)));

            button.addActionListener(e -> model.increment());

            frame.add(label, BorderLayout.CENTER);
            frame.add(button, BorderLayout.SOUTH);

            frame.setSize(300, 150);
            frame.setVisible(true);
        });
    }
}
