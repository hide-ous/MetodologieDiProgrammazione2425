package lezione14.nge;

public class Main {
    public static void main(String[] args) {
        NERV nerv = new NERV();
        Pilot shinji = new Pilot("Shinji", 1);
        Eva unit01 = new Eva("Unit-01", "Positron Rifle");
        Angel sachiel = new Angel("Sachiel");
        Battle battle = new Battle("Battle 1", unit01, sachiel, shinji);

        shinji.synchronizeWithEva(unit01);
        shinji.controlEva(unit01);

        nerv.coordinateBattles(battle);
        battle.recordDamage();
        nerv.analyzeThreats(sachiel);

        shinji.reportStatus();
    }
}