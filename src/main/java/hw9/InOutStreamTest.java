package hw9;

import java.io.*;

public class InOutStreamTest {
    public static void main(String[] args) {
        String path = new File("").getAbsolutePath() + "\\";

        System.out.println("---------------------Task 1-----------------------------");
        PhoneReader readPhoneNumbers = new PhoneReader();
        readPhoneNumbers.readPhoneNumbers(path + "file.txt");

        System.out.println("---------------------Task 2-----------------------------");
        RewriteData rewriteData = new RewriteData();
        rewriteData.rewriteUserData(path + "file2.txt", path + "file3.txt");

        System.out.println("---------------------Task 3-----------------------------");
        WordsCount wordsCount = new WordsCount();
        wordsCount.countWords(path + "file4.txt");
    }
}
