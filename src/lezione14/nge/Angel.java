package lezione14.nge;

public class Angel {
    private String name;
    private String state;
    private int health; // Angel health

    public Angel(String name) {
        this.name = name;
        this.state = "Active";
        this.health = 100; // Initial Angel health
    }

    public void attackEva(Eva eva) {
        System.out.println("Angel " + name + ": Attacking Eva.");
        this.state = "Combat";
        eva.takeDamage(15); // Angel deals damage to Eva
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
        System.out.println("Angel " + name + ": Took " + damage + " damage. Health: " + health);
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public int getHealth() {
        return this.health;
    }
}