package lezione26.bands;


import java.util.*;

public class BandJudge {
    private static final Set<String> BAND_POOL = Set.of(
            "car bomb",
            "between the buried and me",
            "frost*",
            "louis cole"
    );

    private final String bestBand = "louis cole";

    public boolean isBandKnown(String bandName) {
        return BAND_POOL.contains(bandName.toLowerCase());
    }

    public String getBestBand() {
        return bestBand;
    }

    public int getBandScore(String bandName) {
        bandName = bandName.toLowerCase();
        switch (bandName) {
            case "louis cole":
                return 100;
            case "between the buried and me":
                return 85;
            case "frost*":
                return 80;
            case "car bomb":
                return 70;
            default:
                return 0;
        }
    }

    public String judgeBattle(String band1, String band2) {
        if (!isBandKnown(band1) || !isBandKnown(band2)) {
            return "Unknown band(s)";
        }

        int score1 = getBandScore(band1);
        int score2 = getBandScore(band2);

        if (score1 == score2) {
            return "It's a tie between " + band1 + " and " + band2;
        }
        return (score1 > score2 ? band1 : band2) + " wins!";
    }
}
