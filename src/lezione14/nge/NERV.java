package lezione14.nge;

public class NERV {
    public void coordinateBattles(Battle battle) {
        System.out.println("NERV: Coordinating Battle.");
        battle.handleInteractions();
    }

    public void analyzeThreats(Angel angel) {
        System.out.println("NERV: Analyzing Angel threats.");
    }
}