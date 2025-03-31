package lezione13.eliza;

import java.util.ArrayList;
import java.util.List;

class ConversationMemory {
    private List<String> recentInputs = new ArrayList<>();
    private List<String> recentResponses = new ArrayList<>();
    private static final int MEMORY_SIZE = 5;

    public void addInput(String input) {
        recentInputs.add(0, input);
        if (recentInputs.size() > MEMORY_SIZE) {
            recentInputs.remove(recentInputs.size() - 1);
        }
    }

    public void addResponse(String response) {
        recentResponses.add(0, response);
        if (recentResponses.size() > MEMORY_SIZE) {
            recentResponses.remove(recentResponses.size() - 1);
        }
    }

    public boolean hasRecentResponse(String response) {
        return recentResponses.contains(response);
    }
}
