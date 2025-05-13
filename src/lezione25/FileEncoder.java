package lezione25;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Scanner;

public class FileEncoder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome logico (max 10 caratteri): ");
        String name = scanner.nextLine().trim();

        System.out.print("Messaggio da codificare: ");
        String message = scanner.nextLine();

        String filePath = "encoded.dat";

        try {
            encode(filePath, name, message);
            System.out.println("File codificato con successo in: " + filePath);
        } catch (IOException e) {
            System.err.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    public static void encode(String path, String logicalName, String payload) throws IOException {
        try (FileOutputStream out = new FileOutputStream(path)) {
            // Magic number
            out.write("HDR".getBytes(StandardCharsets.US_ASCII));

            // Version
            out.write(1);

            // Logical name (10 bytes, padded with '\0')
            byte[] nameBytes = logicalName.getBytes(StandardCharsets.UTF_8);
            byte[] padded = new byte[10];
            System.arraycopy(nameBytes, 0, padded, 0, Math.min(nameBytes.length, 10));
            out.write(padded);

            // Timestamp (4 bytes, big endian)
            int timestamp = (int) Instant.now().getEpochSecond();
            out.write((timestamp >> 24) & 0xFF);
            out.write((timestamp >> 16) & 0xFF);
            out.write((timestamp >> 8) & 0xFF);
            out.write(timestamp & 0xFF);

            // Payload
            byte[] payloadBytes = payload.getBytes(StandardCharsets.UTF_8);
            int length = payloadBytes.length;

            // Payload length (2 bytes, big endian)
            out.write((length >> 8) & 0xFF);
            out.write(length & 0xFF);

            // Payload data
            out.write(payloadBytes);
        }
    }
}
