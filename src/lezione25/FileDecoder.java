package lezione25;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileDecoder {

    public static void main(String[] args) {
        String filePath = "encoded.dat";

        try {
            decode(filePath);
        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file: " + e.getMessage());
        }
    }

    public static void decode(String path) throws IOException {
        try (FileInputStream in = new FileInputStream(path)) {
            String magic = parseMagic(in);
            int version = parseVersion(in);
            String name = parseLogicalName(in);

            // === Parte da completare ===
            int timestamp = parseTimestamp(in);           // TODO
            int payloadLength = parsePayloadLength(in);   // TODO
            String payload = parsePayload(in, payloadLength); // TODO

            // Output
            System.out.println("=== HEADER DECODIFICATO ===");
            System.out.println("Magic     : " + magic);
            System.out.println("Versione  : " + version);
            System.out.println("Nome      : " + name);
            System.out.println("Timestamp : " + timestamp);
            System.out.println("Payload   : " + payload);
        }
    }

    static String parseMagic(FileInputStream in) throws IOException {
        byte[] magic = new byte[3];
        int read = in.read(magic);
        if (read != 3) throw new IOException("Impossibile leggere il magic number");
        return new String(magic, StandardCharsets.US_ASCII);
    }

    static int parseVersion(FileInputStream in) throws IOException {
        int version = in.read();
        if (version == -1) throw new IOException("Versione mancante");
        return version;
    }

    static String parseLogicalName(FileInputStream in) throws IOException {
        byte[] name = new byte[10];
        int read = in.read(name);
        if (read != 10) throw new IOException("Nome logico incompleto");
        return new String(name, StandardCharsets.UTF_8).replaceAll("\0", "");
    }

    static int parseTimestamp(FileInputStream in) throws IOException {
        // TODO: Leggere 4 byte big-endian e restituire int
        return -1;
    }

    static int parsePayloadLength(FileInputStream in) throws IOException {
        // TODO: Leggere 2 byte big-endian e restituire int
        return -1;
    }

    static String parsePayload(FileInputStream in, int length) throws IOException {
        // TODO: Leggere 'length' byte e convertirli in stringa
        return "TODO";
    }
}
