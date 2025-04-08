package lezione14.nge;

public class Pilot {
    private String name;
    private int id;
    private int syncRatio; // Sync ratio for Eva
    private String emotionalState;

    public Pilot(String name, int id) {
        this.name = name;
        this.id = id;
        this.syncRatio = 100; // Initial sync ratio
        this.emotionalState = "Stable";
    }

    public void synchronizeWithEva(Eva eva) {
        System.out.println("Pilot " + name + ": Synchronizing with Eva.");
        this.emotionalState = "Synchronized";
    }

    public void controlEva(Eva eva) {
        System.out.println("Pilot " + name + ": Controlling Eva.");
    }

    public void changeEmotionalState(String newState) {
        this.emotionalState = newState;
        System.out.println("Pilot " + name + ": Emotional state changed to " + newState + ".");
    }

    public void reportStatus() {
        System.out.println("Pilot " + name + ": Emotional state - " + emotionalState + ", Sync Ratio: " + syncRatio + ".");
    }

    public void adjustSync(int adjustment) {
        this.syncRatio += adjustment;
        if (this.syncRatio < 0) this.syncRatio = 0;
        if (this.syncRatio > 100) this.syncRatio = 100;
        System.out.println("Pilot " + name + ": Sync ratio adjusted to " + syncRatio);
    }

    public int getSyncRatio() {
        return this.syncRatio;
    }
}