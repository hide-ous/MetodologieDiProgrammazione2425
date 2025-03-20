package lezione7;

public class Spammer {

    private static String youWon = "You have been selected as a lucky winner! Claim your prize now!!1!";
    private String gullibleName;

    Spammer(String gullibleName) {
        this.gullibleName = gullibleName;
    }
    public void printPersonalizedMessage(){
        System.out.println("Congratulations, "+gullibleName+"!\n"+youWon);
    }
    public static void printGenericMessage(){
        System.out.println(youWon);
    }
    public static void main(String[] args) {

        Spammer.printGenericMessage();
        System.out.println(Spammer.youWon);
//        Spammer.printPersonalizedMessage(); // does not work!

        Spammer genericSpammer = new Spammer("DoesNotMatter");
        genericSpammer.printGenericMessage(); // does work, but useless to call it on an instance

        Spammer specialSpammer = new Spammer("Forest Gump");
        specialSpammer.printPersonalizedMessage();

    }
}
