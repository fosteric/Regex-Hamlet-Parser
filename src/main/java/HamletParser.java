import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thook on 10/7/15.
 */
public class HamletParser {

    private String hamletData;

    public HamletParser(){
        this.hamletData = loadFile();
    }

    private String loadFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("hamlet.txt").getFile());
        StringBuilder result = new StringBuilder("");

        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return result.toString();
    }

    public String loadFileAndReplace(String find, String replace){
        Pattern pattern = createRegexPattern(find);
        Matcher matcher = createMatcherFromInput(pattern, hamletData);
        return findReplace(matcher, replace);
    }

    public String getHamletData(){
        return hamletData;
    }

    public Pattern createRegexPattern(String find) {
        return Pattern.compile(find, Pattern.CASE_INSENSITIVE);
    }

    public Matcher createMatcherFromInput(Pattern pattern, String input) {
        return pattern.matcher(input);
    }

    public String findReplace(Matcher matcher, String replace) {
        return matcher.replaceAll(replace);
    }

    public boolean contains(String find, String input){
        Pattern pattern = createRegexPattern(find);
        Matcher matcher = createMatcherFromInput(pattern, input);
        return matcher.find();
    }
}
