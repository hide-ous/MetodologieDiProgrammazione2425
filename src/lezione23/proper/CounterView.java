package lezione23.proper;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CounterView extends JFrame implements Observer {

    private final JLabel label = new JLabel("0", SwingConstants.CENTER);
    private final CounterModel model;

    public CounterView(CounterModel model) {
        super("Multi-observer counter");
        this.model = model;

        model.addObserver(this);

        JButton plus = new JButton("+");
        JButton minus = new JButton("–");

        plus.addActionListener(e -> model.increment());
        minus.addActionListener(e -> model.decrement());

        label.setFont(new Font("Arial", Font.BOLD, 40));

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(minus);
        buttons.add(plus);
        add(buttons, BorderLayout.SOUTH);

        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        label.setText(String.valueOf(model.getValue()));
    }
}
