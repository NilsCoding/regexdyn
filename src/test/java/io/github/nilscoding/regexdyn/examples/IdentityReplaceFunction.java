package io.github.nilscoding.regexdyn.examples;

import io.github.nilscoding.regexdyn.RegexMatchData;

import java.util.function.Function;

/**
 * Replace function that returns the input as result.
 * @author NilsCoding
 */
public class IdentityReplaceFunction implements Function<RegexMatchData, String> {

    @Override
    public String apply(RegexMatchData regexMatchData) {
        if (regexMatchData == null) {
            return null;
        }
        return regexMatchData.getData();
    }

}
