import java.util.*;

public class VotingSystem {
    private Map<String, Integer> voteCounts = new HashMap<>();
    private Map<String, Integer> orderedVotes = new LinkedHashMap<>();
    private Map<String, Integer> sortedVotes = new TreeMap<>();

    public void castVote(String candidate) {
        // Update all three maps
        voteCounts.merge(candidate, 1, Integer::sum);
        orderedVotes.merge(candidate, 1, Integer::sum);
        sortedVotes.merge(candidate, 1, Integer::sum);
    }

    public Map<String, Integer> getVoteCounts() {
        return new HashMap<>(voteCounts);
    }

    public Map<String, Integer> getVotesInOrder() {
        return new LinkedHashMap<>(orderedVotes);
    }

    public Map<String, Integer> getVotesSorted() {
        return new TreeMap<>(sortedVotes);
    }

    public Map<String, Integer> getVotesSortedByCount() {
        Map<String, Integer> sortedByCount = new LinkedHashMap<>();
        voteCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sortedByCount.put(entry.getKey(), entry.getValue()));
        return sortedByCount;
    }

    public static void main(String[] args) {
        VotingSystem system = new VotingSystem();

        system.castVote("Alice");
        system.castVote("Bob");
        system.castVote("Alice");
        system.castVote("Charlie");
        system.castVote("Bob");
        system.castVote("Alice");

        System.out.println("Vote counts: " + system.getVoteCounts());
        System.out.println("Votes in order: " + system.getVotesInOrder());
        System.out.println("Votes sorted by name: " + system.getVotesSorted());
        System.out.println("Votes sorted by count: " + system.getVotesSortedByCount());
    }
}