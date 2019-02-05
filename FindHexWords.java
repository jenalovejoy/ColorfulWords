import java.util.regex.*;
import java.util.*;
import java.io.*;

public class FindHexWords {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        File fullDictionary = new File("Dictionary.txt");
        File acceptedHexWords  = new File("AllHexWords.txt");

        buildAcceptableWords(fullDictionary, acceptedHexWords);
    }

    // Scans full dictionary to pull out hexidecimal-accepted words more than one letter
    public static void buildAcceptableWords(File fullDictionary, File acceptedHexWords) throws IOException, FileNotFoundException {
        Pattern hexWordsPattern = Pattern.compile("^([a-f]|[A-F]){2,6}$");
        Scanner readFile = new Scanner(fullDictionary);

        FileWriter writeToAllWords = new FileWriter(acceptedHexWords);
        PrintWriter printToAllWords = new PrintWriter(writeToAllWords);

        while (readFile.hasNextLine()){
            String word = readFile.nextLine();
            Matcher hexWordsMatcher = hexWordsPattern.matcher(word);

            if (hexWordsMatcher.find()){
                printToAllWords.println(word.toUpperCase());
            }
        }
        printToAllWords.close();
        readFile.close();
    }
}
