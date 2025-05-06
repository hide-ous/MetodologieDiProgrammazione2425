package lezione23.inclass;

public class CounterObserver implements Observer {
    private final Counter counter;

    public CounterObserver(Counter o) {
        this.counter = o;
    }
    @Override
    public void update() {
        System.out.println(counter.getState());
    }
}
