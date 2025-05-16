package lezione26.gtfs;


import com.google.transit.realtime.GtfsRealtime;
import org.jxmapviewer.viewer.GeoPosition;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GTFSFetcher {
    private static final String GTFS_RT_URL = "https://romamobilita.it/sites/default/files/rome_rtgtfs_vehicle_positions_feed.pb";

    public static List<GeoPosition> fetchBusPositions() {
        try (InputStream inputStream = new URL(GTFS_RT_URL).openStream()) {
            return parseFromStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<GeoPosition> parseFromStream(InputStream inputStream) throws Exception {
        List<GeoPosition> positions = new ArrayList<>();
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(inputStream);

        for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
            if (entity.hasVehicle()) {
                GtfsRealtime.VehiclePosition vehicle = entity.getVehicle();
                double lat = vehicle.getPosition().getLatitude();
                double lon = vehicle.getPosition().getLongitude();
                positions.add(new GeoPosition(lat, lon));
            }
        }
        return positions;
    }
}
