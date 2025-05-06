package lezione23.inclass;

import java.util.ArrayList;

public class Counter implements Observable {

    int counter = 0;
    ArrayList<Observer> observers = new ArrayList<Observer>();

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }


    public void modify() {
        counter++; //change state
        // ...
        notifyObservers();
    }
    public int getState(){
        return counter;
    }
}
