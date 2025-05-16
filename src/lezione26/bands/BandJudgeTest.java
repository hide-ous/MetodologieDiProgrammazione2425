package lezione26.bands;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BandJudgeTest {

    private final BandJudge judge = new BandJudge();

    @Test
    public void testKnownBands() {
        assertTrue(judge.isBandKnown("car bomb"));
        assertTrue(judge.isBandKnown("Louis Cole"));
        assertFalse(judge.isBandKnown("metallica"));
    }

    @Test
    public void testGetBestBand() {
        assertEquals("louis cole", judge.getBestBand());
    }

    @Test
    public void testBandScores() {
        assertEquals(100, judge.getBandScore("Louis Cole"));
        assertEquals(85, judge.getBandScore("between the buried and me"));
        assertEquals(80, judge.getBandScore("frost*"));
        assertEquals(70, judge.getBandScore("car bomb"));
        assertEquals(0, judge.getBandScore("unknown"));
    }

    @Test
    public void testBattleBetweenBands() {
        assertEquals("louis cole wins!", judge.judgeBattle("louis cole", "car bomb"));
        assertEquals("between the buried and me wins!", judge.judgeBattle("between the buried and me", "car bomb"));
        assertEquals("It's a tie between frost* and frost*", judge.judgeBattle("frost*", "frost*"));
        assertEquals("Unknown band(s)", judge.judgeBattle("dream theater", "car bomb"));
    }
}

