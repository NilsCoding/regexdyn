package io.github.nilscoding.regexdyn.examples;

import io.github.nilscoding.regexdyn.RegexMatchData;

import java.util.Locale;
import java.util.function.Function;

/**
 * Replaces the match string with uppercase string.
 * @author NilsCoding
 */
public class UppercaseReplaceFunction implements Function<RegexMatchData, String> {

    @Override
    public String apply(RegexMatchData regexMatchData) {
        if (regexMatchData == null) {
            return null;
        }
        String matchStr = regexMatchData.getData();
        if (matchStr == null) {
            return null;
        }
        return matchStr.toUpperCase(Locale.GERMAN);
    }

}
