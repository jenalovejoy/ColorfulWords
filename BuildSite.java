import java.io.*;
import java.util.*;

public class BuildSite {

    ArrayList<String> allWords;
    String root;
    String html;
    String newHTMLSection;

    public BuildSite() throws IOException {
        BuildDictionary dictionaryBuilder = new BuildDictionary();
        allWords = dictionaryBuilder.getWords();
        root = "/Users/jena/Desktop/BC/Projects/ColorfulWords/";
        newHTMLSection = buildSiteText();
        buildSiteFile();
        System.out.println("Site completed!");
    }

    public String buildSiteText() throws FileNotFoundException{
        String formatA ="        <div class=\"colorItem\" style=\"background-color:";
        String formatB = "</div>";

        // Builds the formatted section
        for (String color : allWords){
            newHTMLSection += (formatA + color + "\">" + color + formatB) + "\n";
        }

        Scanner readSite = new Scanner(new File(root + "ColorfulSite.html"));
        String key = "<div id=\"colorCollection\">";

        String line = "";
        // Grabs the first half
        while (readSite.hasNextLine() && !line.contains(key)){
            line = readSite.nextLine();
            html += line + "\n";
        }
        // inserts the new section
        html += newHTMLSection;
        // adds the last half
        while (readSite.hasNextLine()){
            line = readSite.nextLine();
            html += line + "\n";
        }
        return html;
    }

    public void buildSiteFile() throws IOException{
        FileWriter fileWriter = new FileWriter(root + "FullColorfulSite.html");
        PrintWriter htmlWriter = new PrintWriter(fileWriter);

        htmlWriter.print(html);
        htmlWriter.close();
    }

    public static void main(String[] args) throws IOException{
        BuildSite siteBuilder = new BuildSite();
    }
}