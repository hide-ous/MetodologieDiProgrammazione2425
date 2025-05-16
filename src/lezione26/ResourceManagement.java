package lezione26;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ResourceManagement {
    public static void main(String[] args) {


        FileReader fr = null;
        try {
            fr = new FileReader("/somewhere.pb");
            BufferedReader br = new BufferedReader(fr);
            // do stuff with the file
            // ....

            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("I/O exception");
        } finally {
//            if (fr != null) {
//                try {
//                    fr.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    if (fr != null) {
//                        fr.close()
//                    }
//                }
//            }

            BufferedReader br = new BufferedReader(fr);

            // #steven wilson
            try(BufferedReader abr = new BufferedReader(new FileReader("/nothere.pb"))){
                // do stuff
            } catch (IOException e) {

                System.out.println(e.getMessage());
            }
        }


    }
}
