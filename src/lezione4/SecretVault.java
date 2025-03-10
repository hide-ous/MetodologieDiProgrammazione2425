package lezione4;

import java.util.ArrayList;
import java.util.List;

public class SecretVault {
    private List<String> participants;

    public SecretVault() {
        this.participants = new ArrayList<>();
        participants.add("Arin the Brave");
        participants.add("Lyria the Swift");
        participants.add("Baldric the Wise");
    }
    public String toString(){
        return participants.toString();
    }

    public VaultHelper getHelper() {
        return new VaultHelper();
    }

    public class VaultHelper {
        public List<String> getParticipants() {
            return participants;  // Direct access to the private list!
        }
    }
    public static void main(String[] args){
        SecretVault vault = new SecretVault();
        System.out.println(vault.getHelper().getParticipants());
    }
}
