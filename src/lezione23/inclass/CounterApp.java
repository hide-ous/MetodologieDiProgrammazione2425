package lezione23.inclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CounterApp");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        int counter = 0;

        JLabel counterLabel = new JLabel(String.valueOf(counter));
        counterLabel.setFont(new Font("Arial", Font.BOLD, 120));
        frame.add(counterLabel);

        JButton incrementer = new JButton("MORE!");
        incrementer.setFont(new Font("Arial", Font.BOLD, 40));
        incrementer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String labelContent = counterLabel.getText();
                int currentCounter = Integer.parseInt(labelContent);
                currentCounter++;
                counterLabel.setText(String.valueOf(currentCounter));
            }
        });
        frame.add(incrementer);

        frame.pack();
        frame.setVisible(true);
    }
}
