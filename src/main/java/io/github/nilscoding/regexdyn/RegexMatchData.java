package io.github.nilscoding.regexdyn;

import java.util.regex.Matcher;

/**
 * Replacement matching data for one match.
 * @author NilsCoding
 * @version 2.0.0
 */
public class RegexMatchData {

    /**
     * Parent matcher.
     */
    protected Matcher parentMatcher;
    /**
     * Counter.
     */
    protected int count = -1;
    /**
     * Match index.
     */
    protected int matchIndex = 0;

    /**
     * Creates a new instance with reference to given matcher and current index.
     * @param parentMatcher parent matcher
     * @param matchIndex    matcher index
     */
    RegexMatchData(final Matcher parentMatcher, final int matchIndex) {
        this.parentMatcher = parentMatcher;
        this.matchIndex = matchIndex;
        this.count = this.parentMatcher.groupCount();
    }

    /**
     * Returns the index of the current match operation, counting each call. First match operation is 0.
     * @return match index
     */
    public int getMatchIndex() {
        return this.matchIndex;
    }

    /**
     * Returns the number of captured groups.
     * @return number of captured groups
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Returns the group's data (or null if groupNo is invalid).
     * @param groupNo group number
     * @return group content or null
     */
    public String getData(final int groupNo) {
        if (groupNo < 1) {
            return null;
        }
        if (groupNo > this.getCount()) {
            return null;
        }
        return this.parentMatcher.group(groupNo);
    }

    /**
     * Returns the complete match data.
     * @return complete match data
     */
    public String getData() {
        return this.parentMatcher.group();
    }

    /**
     * Returns the start position (inclusive).
     * @return start position
     */
    public int getStartPosition() {
        return this.parentMatcher.start();
    }

    /**
     * Returns the end position (exclusive).
     * @return end position
     */
    public int getEndPosition() {
        return this.parentMatcher.end();
    }
}
