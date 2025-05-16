package lezione25;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileDecoderSolved {

    public static void main(String[] args) {
        String filePath = "encoded.dat";  // Specificare il percorso del file da decodificare

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
            int timestamp = parseTimestamp(in);
            int payloadLength = parsePayloadLength(in);
            String payload = parsePayload(in, payloadLength);

            // Output
            System.out.println("=== HEADER DECODIFICATO ===");
            System.out.println("Magic     : " + magic);
            System.out.println("Versione  : " + version);
            System.out.println("Nome      : " + name);
            System.out.println("Timestamp : " + timestamp);
            System.out.println("Payload   : " + payload);
        }
    }

    // Parsing Magic Number (3 byte ASCII)
    static String parseMagic(FileInputStream in) throws IOException {
        byte[] magic = new byte[3];
        int read = in.read(magic);
        if (read != 3) throw new IOException("Impossibile leggere il magic number");
        return new String(magic, StandardCharsets.US_ASCII);
    }

    // Parsing Version (1 byte)
    static int parseVersion(FileInputStream in) throws IOException {
        int version = in.read();
        if (version == -1) throw new IOException("Versione mancante");
        return version;
    }

    // Parsing Logical Name (10 byte UTF-8)
    static String parseLogicalName(FileInputStream in) throws IOException {
        byte[] name = new byte[10];
        int read = in.read(name);
        if (read != 10) throw new IOException("Nome logico incompleto");
        return new String(name, StandardCharsets.UTF_8).replaceAll("\0", "");  // Rimuove i caratteri nulli
    }

    // Parsing Timestamp (4 byte, big-endian)
    static int parseTimestamp(FileInputStream in) throws IOException {
        int b1 = in.read();
        int b2 = in.read();
        int b3 = in.read();
        int b4 = in.read();
        if (b1 == -1 || b2 == -1 || b3 == -1 || b4 == -1) throw new IOException("Timestamp mancante");
        return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
    }

    // Parsing Payload Length (2 byte, big-endian)
    static int parsePayloadLength(FileInputStream in) throws IOException {
        int b1 = in.read();
        int b2 = in.read();
        if (b1 == -1 || b2 == -1) throw new IOException("Lunghezza payload mancante");
        return (b1 << 8) | b2;
    }

    // Parsing Payload (byte[] di dimensione 'length')
    static String parsePayload(FileInputStream in, int length) throws IOException {
        byte[] payload = new byte[length];
        int read = in.read(payload);
        if (read != length) throw new IOException("Errore nella lettura del payload");
        return new String(payload, StandardCharsets.UTF_8);  // Converte in stringa UTF-8
    }
}
