package io.github.nilscoding.regexdyn;

import io.github.nilscoding.regexdyn.examples.IdentityReplaceFunction;
import io.github.nilscoding.regexdyn.examples.MultiplyIntMatchReplaceFunction;
import io.github.nilscoding.regexdyn.examples.UppercaseReplaceFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Test class for <code>RegexReplace</code>.
 * @author NilsCoding
 */
class TestRegexReplace {

    @Test
    void handleNullAsSource() {
        String inputStr = null;
        String regexStr = "[a-z]";
        Function<RegexMatchData, String> replaceFunction = new IdentityReplaceFunction();
        String resultStr = RegexReplace.replaceAll(inputStr, regexStr, replaceFunction);
        Assertions.assertNull(resultStr);
    }

    @Test
    void handleNullAsRegex() {
        String inputStr = "This is a test string.";
        String regexStr = null;
        Function<RegexMatchData, String> replaceFunction = new IdentityReplaceFunction();
        String resultStr = RegexReplace.replaceAll(inputStr, regexStr, replaceFunction);
        Assertions.assertNull(resultStr);
    }

    @Test
    void handleNullAsReplaceFunction() {
        String inputStr = "This is a test string.";
        String regexStr = "[a-z]";
        Function<RegexMatchData, String> replaceFunction = null;
        String resultStr = RegexReplace.replaceAll(inputStr, regexStr, replaceFunction);
        Assertions.assertNull(resultStr);
    }

    @Test
    void replaceIntWithTripleValue() {
        String inputStr = "There are 4 persons in the room, each having 20 dollars in their pocket.";
        String regexStr = "(\\d+)";
        Function<RegexMatchData, String> replaceFunction = new MultiplyIntMatchReplaceFunction(3);
        String resultStr = RegexReplace.replaceAll(inputStr, regexStr, replaceFunction);
        String expectedResultStr = "There are 12 persons in the room, each having 60 dollars in their pocket.";
        Assertions.assertEquals(expectedResultStr, resultStr);
    }

    @Test
    void eachWordInUpperCase() {
        String inputStr = "Everybody at the concert got excited when she entered the stage." +
                " Her performance of all 13 songs was awesome.";
        String regexStr = "([a-zA-Z]+)";
        Function<RegexMatchData, String> replaceFunction = new UppercaseReplaceFunction();
        String resultStr = RegexReplace.replaceAll(inputStr, regexStr, replaceFunction);
        String expectedResultStr = "EVERYBODY AT THE CONCERT GOT EXCITED WHEN SHE ENTERED THE STAGE." +
                " HER PERFORMANCE OF ALL 13 SONGS WAS AWESOME.";
        Assertions.assertEquals(expectedResultStr, resultStr);
    }

    @Test
    void eachWordInUpperCaseOnlyFirstLine() {
        String inputStr = "This is the first line.\r\nThis is the second line.\r\nAnd this is the third line.";
        String regexStr = "^(.+)";
        Function<RegexMatchData, String> replaceFunction = new UppercaseReplaceFunction();
        String resultStr = RegexReplace.replaceAll(inputStr, regexStr, replaceFunction);
        String expectedResultStr = "THIS IS THE FIRST LINE.\r\nThis is the second line.\r\nAnd this is the third line.";
        Assertions.assertEquals(expectedResultStr, resultStr);
    }

    @Test
    void eachWordInUpperCaseMultiLineFlag() {
        String inputStr = "This is the first line.\r\nThis is the second line.\r\nAnd this is the third line.";
        String regexStr = "^(.+)";
        Function<RegexMatchData, String> replaceFunction = new UppercaseReplaceFunction();
        String resultStr = RegexReplace.replaceAll(inputStr, regexStr, Pattern.DOTALL, replaceFunction);
        String expectedResultStr = "THIS IS THE FIRST LINE.\r\nTHIS IS THE SECOND LINE.\r\nAND THIS IS THE THIRD LINE.";
        Assertions.assertEquals(expectedResultStr, resultStr);
    }

}
