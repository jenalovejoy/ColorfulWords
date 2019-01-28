import java.util.regex.*;
import java.util.*;
import java.io.*;

public class BuildDictionary {

    private File input;
    private ArrayList<String> allWordsRaw;
    private ArrayList<String> allWordsPadded;
    Random random;

    public BuildDictionary() throws FileNotFoundException {
        input = new File("/usr/share/dict/words");
        allWordsRaw = new ArrayList<>();
        allWordsPadded = new ArrayList<>();
        random = new Random(System.currentTimeMillis());

        String acceptableWord = "^[a-f]{2,6}$";
        Pattern p = Pattern.compile(acceptableWord);
        
        Scanner readFile = new Scanner(input);

        while (readFile.hasNext()){
            String word = readFile.next();
            Matcher m = p.matcher(word);
            if (m.find()){
                allWordsRaw.add(word.toUpperCase());
            }
        }

        finishWords();

        readFile.close();
    }

    public void showDetails(int max){
        for (String s : allWordsPadded){
            System.out.println(s);
        }
    }

    public String padWord(String word){
        
        int length = 6 - word.length();
        String paddedWord = "";

        for (int i = 0; i < (length / 2 + length % 2); i++){
            int num = random.nextInt(10);
            System.out.print(num + "  ");
            paddedWord += num;
        }
        paddedWord += word;
        for (int i = 0; i < (length / 2); i++){
            int num = random.nextInt(10);
            System.out.print(num + "  ");
            paddedWord += num;
        }
        System.out.println(paddedWord);
        return paddedWord;
    }

    public void finishWords(){
        for (String startWord : allWordsRaw){
            for (int i = 0; i < 4; i++){
                System.out.println(System.currentTimeMillis());
                String newWord = "#" + padWord(startWord);
                System.out.println(newWord);
                allWordsPadded.add(newWord);
                newWord = "";
            }
        }
    }

    public ArrayList<String> getWords(){
        return allWordsPadded;
    }
}