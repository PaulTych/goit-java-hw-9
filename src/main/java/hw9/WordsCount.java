package hw9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordsCount {
    public void countWords(String inFile){
        String line;

        Map<String, Integer> wordsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inFile))) {
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordsMap.put(word.trim(), wordsMap.containsKey(word) ? wordsMap.get(word) + 1 : 1);
                    }
                }
            }

            List<Map.Entry<String, Integer>> list = new ArrayList<>(wordsMap.entrySet()) {

                @Override
                public String toString() {
                    String result = "";
                    for (int i = 0; i < this.size(); i++) {
                        result += this.get(i) + System.lineSeparator();
                    }
                    return result.replaceAll("=", " ");
                }
            };

            Collections.sort(list, (a, b) -> b.getValue() - a.getValue());
            System.out.println(list);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
