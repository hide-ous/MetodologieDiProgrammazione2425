package lezione26.gtfs;

import com.google.transit.realtime.GtfsRealtime;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class SampleGTFSFeedGenerator {

    public static void generateAndSaveSampleFeed(String filePath) {
        try {
            // Costruzione del feed
            GtfsRealtime.FeedMessage.Builder feedBuilder = GtfsRealtime.FeedMessage.newBuilder();

            // Header
            feedBuilder.getHeaderBuilder()
                    .setGtfsRealtimeVersion("2.0")
                    .setIncrementality(GtfsRealtime.FeedHeader.Incrementality.FULL_DATASET)
                    .setTimestamp(System.currentTimeMillis() / 1000);

            // Aggiungi un'entità con un veicolo
            GtfsRealtime.FeedEntity.Builder entityBuilder = GtfsRealtime.FeedEntity.newBuilder();
            entityBuilder.setId(UUID.randomUUID().toString());

            GtfsRealtime.VehiclePosition.Builder vehicleBuilder = GtfsRealtime.VehiclePosition.newBuilder();
            vehicleBuilder.setVehicle(
                    GtfsRealtime.VehicleDescriptor.newBuilder().setId("BUS42").build()
            );
            vehicleBuilder.setPosition(
                    GtfsRealtime.Position.newBuilder()
                            .setLatitude(41.9028f)    // Roma
                            .setLongitude(12.4964f)
                            .build()
            );
            vehicleBuilder.setTimestamp(System.currentTimeMillis() / 1000);

            entityBuilder.setVehicle(vehicleBuilder.build());
            feedBuilder.addEntity(entityBuilder.build());

            // Scrittura su file .pb
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                feedBuilder.build().writeTo(out);
                System.out.println("Sample GTFS feed saved to " + filePath);
            }

        } catch (IOException e) {
            System.err.println("Failed to generate GTFS sample feed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateAndSaveSampleFeed("./sample_feed.pb");
    }
}
