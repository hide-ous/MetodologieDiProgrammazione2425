package lezione14.nge;

public class Eva {
    private String unitNumber;
    private String weapon;
    private String state;
    private int health; // Eva health

    public Eva(String unitNumber, String weapon) {
        this.unitNumber = unitNumber;
        this.weapon = weapon;
        this.state = "Operational";
        this.health = 100; // Initial Eva health
    }

    public void attackAngel(Angel angel) {
        System.out.println("Eva " + unitNumber + ": Attacking with " + weapon + ".");
        this.state = "Combat";
        angel.takeDamage(20); // Eva deals damage to Angel
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
        System.out.println("Eva " + unitNumber + ": Took " + damage + " damage. Health: " + health);
    }

    public String getUnitNumber() {
        return this.unitNumber;
    }

    public String getState() {
        return this.state;
    }

    public int getHealth() {
        return this.health;
    }
}