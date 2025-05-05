package lezione23.proper;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class WarningPopup implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof CounterModel model) {
            if (model.getValue() < 0) {
                JOptionPane.showMessageDialog(null,
                        "Warning: counter is negative!",
                        "Negative Alert",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

