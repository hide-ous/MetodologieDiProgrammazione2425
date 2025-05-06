package lezione23.inclass;

public class CounterCLIApp {
    public static void main(String[] args) {
        Counter counter = new Counter();
        CounterObserver observer = new CounterObserver(counter);
        CounterObserverGUI observerGUI = new CounterObserverGUI(counter);
        counter.attach(observer);
        counter.attach(observerGUI);

//        counter.modify();
//        counter.modify();
//        counter.modify();
    }

}
