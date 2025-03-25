package project_starter;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.OSMTileFactoryInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class RealTimeBusTracker {
    private static JXMapViewer mapViewer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rome Bus Tracker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            TileFactoryInfo info = new OSMTileFactoryInfo();
            DefaultTileFactory tileFactory = new DefaultTileFactory(info);

            mapViewer = new JXMapViewer();
            mapViewer.setTileFactory(tileFactory);
            mapViewer.setAddressLocation(new GeoPosition(41.9028, 12.4964)); // Rome center
            mapViewer.setZoom(5);

            frame.add(mapViewer);
            frame.setVisible(true);

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    updateBusPositions();
                }
            }, 0, 15000); // Refresh every 15 sec
        });
    }
    private static void updateBusPositions() {
        List<GeoPosition> busPositions = GTFSFetcher.fetchBusPositions();
        displayBusPositions(busPositions);
    }

    private static void displayBusPositions(List<GeoPosition> positions) {
        Set<BusWaypoint> waypoints = new HashSet<>();
        for (GeoPosition pos : positions) {
            waypoints.add(new BusWaypoint(pos.getLatitude(), pos.getLongitude()));
        }

        WaypointPainter<BusWaypoint> painter = new WaypointPainter<>();
        painter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(painter);
    }
}
