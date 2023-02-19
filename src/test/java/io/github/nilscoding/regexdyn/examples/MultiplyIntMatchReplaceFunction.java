package io.github.nilscoding.regexdyn.examples;

import io.github.nilscoding.regexdyn.RegexMatchData;

import java.util.function.Function;

/**
 * Replace function to multiply search int.
 * @author NilsCoding
 */
public class MultiplyIntMatchReplaceFunction implements Function<RegexMatchData, String> {

    private int multiplyWith = 1;

    public MultiplyIntMatchReplaceFunction(int multiplyWith) {
        this.multiplyWith = multiplyWith;
    }

    @Override
    public String apply(RegexMatchData regexMatchData) {
        if (regexMatchData == null) {
            return null;
        }
        String numStr = regexMatchData.getData();
        if (numStr == null) {
            return null;
        }
        try {
            int num = Integer.parseInt(numStr);
            return String.valueOf(num * this.multiplyWith);
        } catch (Exception ex) {
            return null;
        }
    }

}
