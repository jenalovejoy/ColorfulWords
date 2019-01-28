// Jena Lovejoy
// BuildDictionary takes in an English dictionary and saves all words using only the letters A-F, 
// then pads those words with values 0-9 to generate an ArrayList of hexidecimal values

import java.util.regex.*;
import java.util.*;
import java.io.*;

public class BuildDictionary {

    private File input;
    private ArrayList<String> allWordsRaw;      // starting words without formatting
    private ArrayList<String> allWordsPadded;   // formatted dictionary, with "#" and numbers 
    Random random;

    public BuildDictionary() throws FileNotFoundException {
        input = new File("/usr/share/dict/words");
        allWordsRaw = new ArrayList<>();
        allWordsPadded = new ArrayList<>();
        random = new Random(System.currentTimeMillis());

        String acceptableWord = "^[a-f]{2,6}$";         // ignores words longer than possibly shown as a color
        Pattern p = Pattern.compile(acceptableWord);
        
        Scanner readFile = new Scanner(input);

        while (readFile.hasNext()){
            String word = readFile.next();
            Matcher m = p.matcher(word);
            if (m.find()){
                allWordsRaw.add(word.toUpperCase());
            }
        }

        // Formats the original words
        finishWords();

        readFile.close();
    }

    // Brings a word to 6 characters with randomly generated numbers for padding
    // I chose to pad the words in the center to slant the end result towards bluer tones
    public String padWord(String word){
        
        int length = 6 - word.length();
        String paddedWord = "";

        // If there's an uneven number of values needed to pad, add the extra to the beginning
        for (int i = 0; i < (length / 2 + length % 2); i++){
            int num = random.nextInt(10);
            paddedWord += num;
        }
        paddedWord += word;
        for (int i = 0; i < (length / 2); i++){
            int num = random.nextInt(10);
            paddedWord += num;
        }
        return paddedWord;
    }

    // Adding additional formatting and creating more options
    public void finishWords(){
        for (String startWord : allWordsRaw){
            for (int i = 0; i < 4; i++){ // creates 4 different hexidecimal versions of a given word
                String newWord = "#" + padWord(startWord);
                allWordsPadded.add(newWord);
                newWord = "";
            }
        }
    }

    // To access the word list
    public ArrayList<String> getWords(){
        return allWordsPadded;
    }

    // To display the word list
    public void showDetails(int max){
        for (String s : allWordsPadded){
            System.out.println(s);
        }
    }
}