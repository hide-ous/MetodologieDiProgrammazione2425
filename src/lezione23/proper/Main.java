package lezione23.proper;

public class Main {
    public static void main(String[] args) {
        CounterModel model = new CounterModel();

        new CounterView(model);             // GUI view
        model.addObserver(new ConsoleLogger("Logger1")); // logs to console
        model.addObserver(new WarningPopup());           // warning popup
    }
}
