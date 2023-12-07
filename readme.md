# regexdyn

**regexdyn** is a tiny Java library to simplify the process of performing dynamic replacements based on regular expressions.

## key features
- just ony tiny JAR to be included in your project
- compiles against Java 1.8, works seamlessly with newer Java versions
- uses a `Function` to resolve the replacement value
- permissive, open license

## new and noteworthy

In version 2.0.0 the default behavior has been changed so that if the regular expression does not match at all. For more details see version history.

## origin and development

This was one of my first open-source projects, published in the early 2010's on SourceForge.

However, over the years I was not very happy with the license and the code lacked some details like a POM and gitignore file.
So I decided to rewrite it a little and re-publish it on GitHub.

## example usage

Let's say you have a text like this:

`There are 2 kinds of requests, which are handled by 6 threads. The total amount of memory used is 56 MB.`

The task is to triple all numbers in this string. While this could be done by manually parsing the text, you could find each numeric value via a regular expression.
Sadly, Java does not come with a builtin method to apply dynamic replacement - that is, the replacement string is based on the found matching string.

This is what **regexdyn** can do here:

```java
String input = "There are 2 kinds of requests, which are handled by 6 threads. The total amount of memory used is 56 MB.";
String result = RegexReplace.replaceAll(input, "(\\d+)", replaceData -> {
            return String.valueOf(3 * Integer.parseInt(replaceData.getData(1)));
        });
```

How does it work?
- The pattern must contain a group, in this case numeric values will be found
- The replacement data is of type `RegexMatchData` which gives access to the matching group, in this case at index 1
- The found group content is parsed as an Integer, multiplied by 3 and returned as replacement string

## other usages

The code above is just one example to use **jRegexDyn** but there are many more (and of course, most of them are more useful than this small example):
- find and manipulate links in HTML data
- replace or remove tags in HTML
- process special markers and convert them to HTML tags
- modify CSV files
- re-format phone numbers
- ... and may more, you name them

## additional information

`RegexMatchData` provides a zero-based index for subsequent replacements (e.g. the 3rd replacement has index 2), so you can assemble
the replacement text based on its occurrence in the data.

If you want to leave one replacement match unchanged, just return `null` (no need to rebuild the replacement string based on the search regex).

Make sure that you check the result of `getCount()` in `RegexMatchData` and/or logically check the result of `getData(int)` because it might return `null` if the index does not exist.

If the regular expression does not contain any groups, the callbacks will be done but no replacement string will be available in `RegexMatchData` (because it's group-based), so this might be rather useless (hint: don't forget to specify groups in your regex). Maybe `getStartPosition()` and `getEndPosition()` might help in this case, but I highly suggest using groups in the regular expression.

## version history

### 2.0.0 (Dec 2023)
The behavior in case of not-matching regex was neither clearly documented nor intuitive to use in version 1.0.0, so this was improved with this version.

The default behavior in 1.0.0 was returning `null` if the regular expression does not match. In 2.0.0 a method parameter `nullIfNoneMatch` was added to the methods to specify if `null` (use parameter value `false`) or the unchanged input string (use parameter value `true`) should be returned.

Methods without the `nullIfNoneMatch` parameter will assume `false` and therefore will now return the unchanged input string instead of `null` like in the previous version.

### 1.0.0 (Feb 2023)
Rewrite of the initial version, changed the package, improved code and javadoc, converted the project to Maven and uploaded it to GitHub

### (Oct. 2010)
Initial version (on SourceForge)

## license

The original version was licensed under LGPL and for some time I thought this might the right license.

However, the code of this project is not very complex - it even might be called trivial. The code also might be useful in commercial projects.

So I decided to update the project and documentation. And I also switched to the `MIT License` which is more permissive.
