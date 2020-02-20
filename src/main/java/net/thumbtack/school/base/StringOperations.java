package net.thumbtack.school.base;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StringOperations {

    public static int getSummaryLength(String[] strings) {
        return Arrays.stream(strings).mapToInt(String::length).sum();
    }

    public static String getFirstAndLastLetterString(String string) {
        return String.valueOf(string.charAt(0)) + string.charAt(string.length() - 1);
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        return string1.charAt(index) == string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        return string1.indexOf(str) == string2.indexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {
        return string1.equalsIgnoreCase(string2);
    }

    public static boolean isLess(String string1, String string2) {
        return string1.compareTo(string2) < 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {
        return string1.compareToIgnoreCase(string2) < 0;
    }

    public static String concat(String string1, String string2) {
        return string1 + string2;
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2) {
        int smallestLength = Math.min(string1.length(), string2.length());
        int i;
        for (i = 0; i < smallestLength; i++) {
            if (string1.charAt(i) != string2.charAt(i)) {
                break;
            }
        }
        return string1.substring(0, i);
    }

    public static String reverse(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    public static boolean isPalindrome(String string) {
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) != string.charAt(string.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeIgnoreCase(String string) {
        return isPalindrome(string.toLowerCase());
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        return Arrays.stream(strings)
                .filter(StringOperations::isPalindromeIgnoreCase)
                .max(Comparator.comparingInt(String::length)).orElse("");
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        if (string1.length() < length + index
                || string2.length() < length + index) {
            return false;
        }
        return string1.substring(index, length + index)
                .equals(string2.substring(index, length + index));
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1,
                                                        char replaceByInStr1, String string2,
                                                        char replaceInStr2, char replaceByInStr2) {
        return string1.replace(replaceInStr1, replaceByInStr1)
                .equals(string2.replace(replaceInStr2, replaceByInStr2));
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1,
                                                     String replaceByInStr1, String string2, String replaceInStr2,
                                                     String replaceByInStr2) {
        return string1.replace(replaceInStr1, replaceByInStr1)
                .equals(string2.replace(replaceInStr2, replaceByInStr2));

    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        return isPalindromeIgnoreCase(string.replaceAll(" ", ""));
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {
        return string1.trim().equals(string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array) {
        return Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public static String makeCsvStringFromDoubles(double[] array) {
        return Arrays.stream(array)
                .mapToObj(v -> String.format("%.2f", v))
                .collect(Collectors.joining(","));
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        return new StringBuilder(Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        return new StringBuilder(Arrays.stream(array)
                .mapToObj(v -> String.format("%.2f", v))
                .collect(Collectors.joining(",")));
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < positions.length; i++) {
            stringBuilder.deleteCharAt(positions[i] - i);
        }
        return stringBuilder;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = positions.length - 1; i >= 0; i--) {
            stringBuilder.insert(positions[i], characters[i]);
        }
        return stringBuilder;
    }
}
