package lezione26.gtfs;

import org.jxmapviewer.viewer.GeoPosition;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GTFSFetcherTest {

    @org.junit.Test
    @Test
    public void testParseFromSampleFeed() throws Exception {
        try (InputStream input = getClass().getResourceAsStream("sample_feed.pb")) {
            assertNotNull(input, "sample_feed.pb not found");

            List<GeoPosition> positions = GTFSFetcher.parseFromStream(input);
            assertFalse(positions.isEmpty(), "Expected at least one position");

            GeoPosition pos = positions.get(0);
            assertTrue(pos.getLatitude() > 0);
            assertTrue(pos.getLongitude() > 0);
        }
    }
}
