import java.util.*;

import model.BankInfo;

public class minibank12 {
    
    public static void wordFrequency() {
        String paragraph = "Java is great and Java is fun. Coding in Java is easy and fun.";
        String[] words = paragraph.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
        
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> {
            if (!b.getValue().equals(a.getValue())) {
                return b.getValue().compareTo(a.getValue());
            }
            return a.getKey().compareTo(b.getKey());
        });
        
        System.out.println("=== Word Frequency ===");
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static void uniqueVisitors() {
        String[] logins = {"alice", "bob", "alice", "charlie", "bob", "david", "alice"};
        
        HashSet<String> uniqueSet = new HashSet<>(Arrays.asList(logins));
        LinkedHashSet<String> orderedSet = new LinkedHashSet<>(Arrays.asList(logins));
        
        System.out.println("\n=== Unique Visitors ===");
        System.out.println("Total logins: " + logins.length);
        System.out.println("Unique users: " + uniqueSet.size());
        System.out.println("First-seen order: " + orderedSet);
        
        System.out.print("First three distinct names: ");
        int count = 0;
        for (String name : orderedSet) {
            System.out.print(name);
            count++;
            if (count < 3 && count < orderedSet.size()) {
                System.out.print(", ");
            }
            if (count == 3) {
                break;
            }
        }
        System.out.println();
    }
    
    public static void leaderboard() {
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("Rahul", 950);
        scores.put("Priya", 1200);
        scores.put("Amit", 1200);
        scores.put("Neha", 800);
        scores.put("Karan", 1100);
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());
        list.sort((a, b) -> {
            if (!b.getValue().equals(a.getValue())) {
                return b.getValue().compareTo(a.getValue());
            }
            return a.getKey().compareTo(b.getKey());
        });
        
        System.out.println("\n=== Leaderboard (Top 3) ===");
        int topN = 3;
        for (int i = 0; i < list.size() && i < topN; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + " - " + entry.getValue());
        }
    }
    
    public static void main(String[] args) {
        BankInfo bankInfo = new BankInfo("MiniBank", "Main Branch");
        System.out.println(bankInfo);
        
        wordFrequency();
        uniqueVisitors();
        leaderboard();
    }
}
