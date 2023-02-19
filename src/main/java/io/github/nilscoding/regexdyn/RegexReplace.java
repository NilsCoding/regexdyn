package io.github.nilscoding.regexdyn;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Apply dynamic replacements based on regular expression matching.
 * @author NilsCoding
 * @version 1.0.0
 */
public final class RegexReplace {

    private RegexReplace() {
    }

    /**
     * Replaces all matches of <code>regexStr</code> in <code>str</code> using the given replacement resolve function.
     * If the replacement function returns <code>null</code>, the match will not be replaced but remain the same
     * as in the given input string. To remove a match, return an empty string.
     * @param str               string (required)
     * @param regexStr          regular expression string (required)
     * @param dynReplaceDataFnc dynamic replacement resolve function (required)
     * @return string with replacements applied or null on error
     */
    public static String replaceAll(final String str,
                                    final String regexStr,
                                    final Function<RegexMatchData, String> dynReplaceDataFnc) {
        return replaceAll(str, regexStr, 0, dynReplaceDataFnc);
    }

    /**
     * Replaces all matches of <code>regexStr</code> in <code>str</code> using the given replacement resolve function.
     * If the replacement function returns <code>null</code>, the match will not be replaced but remain the same
     * as in the given input string. To remove a match, return an empty string.<br>
     * For <code>regexFlags</code> int values see javadoc of java.util.regex.Pattern
     * @param str               string (required)
     * @param regexStr          regular expression string (required)
     * @param regexFlags        regular expression flags (required)
     * @param dynReplaceDataFnc dynamic replacement resolve function (required)
     * @return string with replacements applied or null on error
     * @see java.util.regex.Pattern
     */
    public static String replaceAll(final String str,
                                    final String regexStr,
                                    final int regexFlags,
                                    final Function<RegexMatchData, String> dynReplaceDataFnc) {
        if ((regexStr == null) || (regexStr.length() == 0)) {
            return null;
        }
        if ((str == null) || (str.isEmpty())) {
            return null;
        }
        if (dynReplaceDataFnc == null) {
            return null;
        }
        StringBuffer buffer;
        try {
            Pattern pattern = Pattern.compile(regexStr, regexFlags);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find() == false) {
                return null;
            } else {
                buffer = new StringBuffer();
                int index = 0;
                do {
                    RegexMatchData matchData = new RegexMatchData(matcher, index);
                    String replaceBy = dynReplaceDataFnc.apply(matchData);
                    if (replaceBy != null) {
                        matcher.appendReplacement(buffer, replaceBy);
                    }
                    index++;
                } while (matcher.find() == true);
                matcher.appendTail(buffer);
                return buffer.toString();
            }
        } catch (Exception ex) {
            return null;
        }
    }

}
