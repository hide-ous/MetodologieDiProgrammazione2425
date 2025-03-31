package lezione13;

enum ReactionType {
    LIKE("👍", "Like"),
    LOVE("❤️", "Love"),
    WOW("😮", "Wow"),
    SAD("😢", "Sad"),
    ANGRY("😡", "Angry");

    private final String emoji;
    private final String description;

    ReactionType(String emoji, String description) {
        this.emoji = emoji;
        this.description = description;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getDescription() {
        return description;
    }
}
