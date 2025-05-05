package lezione23.proper;

import java.util.Observable;

public class CounterModel extends Observable {
    private int value = 0;

    public int getValue() {
        return value;
    }

    public void increment() {
        value++;
        notifyChange();
    }

    public void decrement() {
        value--;
        notifyChange();
    }

    private void notifyChange() {
        setChanged();
        notifyObservers();
    }
}

