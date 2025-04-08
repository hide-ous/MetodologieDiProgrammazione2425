package lezione14.nge;

public class Battle {
    private String name;
    private Eva eva;
    private Angel angel;
    private Pilot pilot;

    public Battle(String name, Eva eva, Angel angel, Pilot pilot) {
        this.name = name;
        this.eva = eva;
        this.angel = angel;
        this.pilot = pilot;
    }

    public void handleInteractions() {
        System.out.println("Battle " + name + ": Handling interactions between Eva and Angel.");
        eva.attackAngel(angel);
        angel.attackEva(eva);

        // Simulate pilot stress and sync changes
        int syncAdjustment = (int) (Math.random() * 30 - 15); // Random adjustment
        pilot.adjustSync(syncAdjustment);

        if (Math.random() < 0.5) {
            pilot.changeEmotionalState("Unstable");
        } else {
            pilot.changeEmotionalState("Stable");
        }
    }

    public void recordDamage() {
        System.out.println("Battle " + name + ": Recording damage and losses.");
        System.out.println("Eva: " + eva.getUnitNumber() + ", Health: " + eva.getHealth() + ", State: " + eva.getState());
        System.out.println("Angel: " + angel.getName() + ", Health: " + angel.getHealth() + ", State: " + angel.getState());
        pilot.reportStatus();
    }
}
