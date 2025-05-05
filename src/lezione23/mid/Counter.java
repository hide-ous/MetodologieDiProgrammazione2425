package lezione23.mid;

import java.util.ArrayList;
import java.util.List;

interface CounterObserver {
    void counterUpdated(int newValue);
}

class Counter {
    private int value = 0;
    private final List<CounterObserver> observers = new ArrayList<>();

    public void increment() {
        value++;
        notifyObservers();
    }

    public void addObserver(CounterObserver obs) {
        observers.add(obs);
    }

    private void notifyObservers() {
        for (CounterObserver obs : observers) {
            obs.counterUpdated(value);
        }
    }
}
