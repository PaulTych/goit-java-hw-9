package hw9;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RewriteData {
    public static void checkFile(File file){
        if (!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
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
        File file = new File(outJSON);
        checkFile(file);
        try (BufferedWriter writer = new BufferedWriter( new FileWriter(outJSON))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userList, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
