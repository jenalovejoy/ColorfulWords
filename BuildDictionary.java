import java.util.regex.*;
import java.util.*;
import java.io.*;

public class BuildDictionary {

    private File input;
    private ArrayList<String> allWords;

    public BuildDictionary() throws FileNotFoundException {
        input = new File("/usr/share/dict/words");
        allWords = new ArrayList<>();

        String acceptableWord = "^[a-f]{1,6}$";
        Pattern p = Pattern.compile(acceptableWord);
        
        Scanner readFile = new Scanner(input);

        while (readFile.hasNext()){
            String word = readFile.next();
            Matcher m = p.matcher(word);
            if (m.find()){
                if (word.length() != 6){
                    word = padWord(word);
                }
                allWords.add("#" + padWord(word.toUpperCase()));
            }
        }
        readFile.close();
    }

    public void showDetails(int max){
        for (int i = 0; i < max; i++){
            System.out.println("#" + allWords.get(i));
        }
    }

    public String padWord(String word){
        int length = 6 - word.length();
        String paddedWord = "";

        for (int i = 0; i < (length / 2 + length % 2); i++){
            paddedWord += "0";
        }
        paddedWord += word;
        for (int i = 0; i < (length / 2); i++){
            paddedWord += "0";
        }

        return paddedWord;
    }

    public ArrayList<String> getWords(){
        return allWords;
    }
}