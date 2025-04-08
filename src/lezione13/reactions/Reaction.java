package lezione13.reactions;

class Reaction {
    private String userId;
    private ReactionType type;

    public Reaction(String userId, ReactionType type) {
        this.userId = userId;
        this.type = type;
    }

    public void printReaction() {
        System.out.println(userId + " reacted with " + type.getEmoji() + " (" + type.getDescription() + ")");
    }
}
