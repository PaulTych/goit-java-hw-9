package hw9;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public class InOutStreamTest {
    public void readPhoneNumbers(String inFile){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile))) {
            while ((line = reader.readLine()) != null) {
                if (line.matches("([(]\\d{3}[)] \\d{3}-\\d{4})|(\\d{3}-\\d{3}-\\d{4})")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void rewriteUserData(String inFile, String outJSON) {
        String line ;
        String[] inFields;
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile))) {
            while ((line = reader.readLine()) != null) {
                if (line.matches("[a-zA-Z]+\\s\\d{1,3}")) {
                    inFields = line.split(" ", 2);
                    User user = new User(inFields[0], Integer.parseInt(inFields[1]));
                    userList.add(user);
                    System.out.println(user);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (Writer writer = new FileWriter(outJSON)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userList, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void countWords(String inFile) {
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

    public static void main(String[] args) {
        InOutStreamTest testClass = new InOutStreamTest();
        System.out.println("---------------------Task 1-----------------------------");
        testClass.readPhoneNumbers("c:\\temp\\file.txt");
        System.out.println("---------------------Task 2-----------------------------");
        testClass.rewriteUserData("c:\\temp\\file2.txt", "c:\\temp\\file3.txt");
        System.out.println("---------------------Task 3-----------------------------");
        testClass.countWords("c:\\temp\\file4.txt");
    }
}
