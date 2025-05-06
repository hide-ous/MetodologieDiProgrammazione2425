package lezione23.inclass;


public interface Observable {
    void notifyObservers();
    void attach(Observer observer);
    void detach(Observer observer);
}
