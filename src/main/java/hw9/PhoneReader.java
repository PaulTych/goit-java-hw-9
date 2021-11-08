package hw9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PhoneReader {
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
}
