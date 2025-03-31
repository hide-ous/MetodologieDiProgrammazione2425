package lezione13;

public class SocialMediaApp {
    public static void main(String[] args) {
        Reaction reaction1 = new Reaction("user123", ReactionType.LIKE);
        Reaction reaction2 = new Reaction("user456", ReactionType.WOW);
        Reaction reaction3 = new Reaction("user789", ReactionType.LOVE);

        reaction1.printReaction();
        reaction2.printReaction();
        reaction3.printReaction();
    }
}
