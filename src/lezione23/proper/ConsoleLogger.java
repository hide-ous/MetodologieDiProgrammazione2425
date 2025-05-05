package lezione23.proper;

import java.util.Observable;
import java.util.Observer;

public class ConsoleLogger implements Observer {
    private final String name;

    public ConsoleLogger(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof CounterModel model) {
            System.out.println(name + ": Counter is now " + model.getValue());
        }
    }
}

