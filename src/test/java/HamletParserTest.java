import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class HamletParserTest {
    private String hamletText;
    private HamletParser hamletParser;

    @Before
    public void setUp() {
        this.hamletParser = new HamletParser();
        this.hamletText = hamletParser.getHamletData();
    }

    @Test
    public void testChangeHamletToDolio() {
        String input = "HamletHoratioHamletHoratio";
        String find = "Hamlet";
        String replace = "Dolio";
        Pattern pattern = hamletParser.createRegexPattern(find);
        Matcher matcher = hamletParser.createMatcherFromInput(pattern, input);
        String actual = hamletParser.findReplace(matcher, replace);
        String expected = "DolioHoratioDolioHoratio";
        assertEquals(expected, actual);
    }

    @Test
    public void testChangeHoratioToFroilan() {
        String input = "HamletHoratioHamletHoratio";
        String find = "Horatio";
        String replace = "Froilan";
        Pattern pattern = hamletParser.createRegexPattern(find);
        Matcher matcher = hamletParser.createMatcherFromInput(pattern, input);
        String actual = hamletParser.findReplace(matcher, replace);
        String expected = "HamletFroilanHamletFroilan";
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateHoratioPattern() {
        String expected = "Horatio";
        Pattern pattern = hamletParser.createRegexPattern(expected);
        String actual = pattern.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateHamletPattern() {
        String expected = "Hamlet";
        Pattern pattern = hamletParser.createRegexPattern(expected);
        String actual = pattern.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateMatcherFromInput(){
        String input = "HamletHoratioHamletHoratio";
        Pattern pattern = hamletParser.createRegexPattern("Hamlet");
        Matcher matcher = hamletParser.createMatcherFromInput(pattern, input);
        boolean actual = matcher instanceof Matcher;
        assertTrue(actual);
    }

}