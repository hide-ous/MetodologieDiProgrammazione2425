package lezione23.inclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFirstFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        FlowLayout layout = new FlowLayout();
//        BoxLayout layout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(layout);

        JLabel label = new JLabel("This is my label");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        frame.add(label);

        JButton button = new JButton("button");
        button.addActionListener(e -> System.out.println("clicked!"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button.setText(button.getText() + "n");
            }
        });

        frame.add(button);

//        frame.pack();
        frame.setVisible(true);
    }
}
