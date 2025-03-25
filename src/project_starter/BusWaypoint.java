package project_starter;

import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;

public class BusWaypoint implements Waypoint {
    private final GeoPosition position;

    public BusWaypoint(double lat, double lon) {
        this.position = new GeoPosition(lat, lon);
    }

    @Override
    public GeoPosition getPosition() {
        return position;
    }
}
