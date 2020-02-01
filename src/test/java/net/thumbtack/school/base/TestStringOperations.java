package net.thumbtack.school.base;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class TestStringOperations {

    @Test
    public void testGetSummaryLength() {
        String[] first = {"Hello", "World"};
        assertEquals(10, StringOperations.getSummaryLength(first));
        String[] second = "Hello, World".split(" ");
        assertEquals(11, StringOperations.getSummaryLength(second));
        String[] third = "The quick brown fox jumps over the lazy dog".split(" ");
        assertEquals(35, StringOperations.getSummaryLength(third));
    }

    @Test
    public void testGetFirstAndLastLetterString() {
        String first = "Hello";
        assertEquals("Ho", StringOperations.getFirstAndLastLetterString(first));
        String second = "World";
        assertEquals("Wd", StringOperations.getFirstAndLastLetterString(second));
        String third = "A";
        assertEquals("AA", StringOperations.getFirstAndLastLetterString(third));
    }

    @Test
    public void testIsSameCharAtPosition() {
        String first = "Hello";
        String second = "Cell";
        assertAll(
                () -> assertTrue(StringOperations.isSameCharAtPosition(first, second, 1)),
                () -> assertTrue(StringOperations.isSameCharAtPosition(first, second, 2)),
                () -> assertFalse(StringOperations.isSameCharAtPosition(first, second, 0))
        );

    }

    @Test
    public void testIsSameFirstCharPosition() {
        String first = "Hello";
        String second = "Cell";
        assertAll(
                () -> assertTrue(StringOperations.isSameFirstCharPosition(first, second, 'e')),
                () -> assertTrue(StringOperations.isSameFirstCharPosition(first, second, 'l')),
                () -> assertFalse(StringOperations.isSameFirstCharPosition(first, second, 'H'))
        );
    }

    @Test
    public void testIsSameLastCharPosition() {
        String first = "Waiting";
        String second = "Testing";
        assertAll(
                () -> assertTrue(StringOperations.isSameLastCharPosition(first, second, 'g')),
                () -> assertTrue(StringOperations.isSameLastCharPosition(first, second, 'n')),
                () -> assertFalse(StringOperations.isSameLastCharPosition(first, second, 'a'))
        );
    }

    @Test
    public void testIsSameFirstStringPosition() {
        String first = "Hello";
        String second = "Cell";
        assertAll(
                () -> assertTrue(StringOperations.isSameFirstStringPosition(first, second, "el")),
                () -> assertTrue(StringOperations.isSameFirstStringPosition(first, second, "ell")),
                () -> assertFalse(StringOperations.isSameFirstStringPosition(first, second, "Hel"))
        );
    }

    @Test
    public void testIsSameLastStringPosition() {
        String first = "Waiting";
        String second = "Testing";
        assertAll(
                () -> assertTrue(StringOperations.isSameLastStringPosition(first, second, "ng")),
                () -> assertTrue(StringOperations.isSameLastStringPosition(first, second, "ing")),
                () -> assertFalse(StringOperations.isSameLastStringPosition(first, second, "a"))
        );
    }

    @Test
    public void testIsEqual() {
        String first = new String("Hello");
        String second = new String("Hello");
        String third = "World";
        assertTrue(StringOperations.isEqual(first, second));
        assertFalse(StringOperations.isEqual(first, third));
    }

    @Test
    public void testIsEqualIgnoreCase() {
        String first = new String("Hello");
        String second = new String("HELLO");
        String third = new String("HeLlO");
        String fourth = "World";
        assertTrue(StringOperations.isEqualIgnoreCase(first, second));
        assertTrue(StringOperations.isEqualIgnoreCase(first, third));
        assertTrue(StringOperations.isEqualIgnoreCase(second, third));
        assertFalse(StringOperations.isEqualIgnoreCase(first, fourth));
        assertFalse(StringOperations.isEqualIgnoreCase(second, fourth));
    }

    @Test
    public void testIsLess() {
        String first = "Hello";
        String second = "Hello, World";
        String third = "World";
        String fourth = "Hell";
        assertTrue(StringOperations.isLess(first, second));
        assertTrue(StringOperations.isLess(second, third));
        assertFalse(StringOperations.isLess(first, fourth));
    }

    @Test
    public void testIsLessIgnoreCase() {
        String first = "Hello";
        String second = "HELLO";
        String third = "HeLlO";
        String fourth = "World";
        String fifth = "Hell";
        assertFalse(StringOperations.isLessIgnoreCase(first, second));
        assertFalse(StringOperations.isLessIgnoreCase(first, third));
        assertFalse(StringOperations.isLessIgnoreCase(second, third));
        assertTrue(StringOperations.isLessIgnoreCase(first, fourth));
        assertFalse(StringOperations.isLessIgnoreCase(second, fifth));
    }

    @Test
    public void testConcat() {
        assertEquals("HelloWorld", StringOperations.concat("Hello", "World"));
        assertEquals("Hello World", StringOperations.concat(StringOperations.concat("Hello", " "), "World"));
        assertEquals("Hello, World", StringOperations.concat(StringOperations.concat("Hello", ", "), "World"));
        assertEquals("Hello, World", StringOperations.concat("Hello,", " World"));
    }

    @Test
    public void testIsSamePrefix() {
        String first = "Hello";
        String second = "Hell";
        String third = "Helloween";
        assertAll(
                () -> assertTrue(StringOperations.isSamePrefix(first, second, "He")),
                () -> assertTrue(StringOperations.isSamePrefix(first, second, "Hell")),
                () -> assertFalse(StringOperations.isSamePrefix(first, second, "Hello")),
                () -> assertTrue(StringOperations.isSamePrefix(first, third, "Hello")),
                () -> assertFalse(StringOperations.isSamePrefix(first, third, "Bye"))
        );
    }

    @Test
    public void testIsSameSuffix() {
        String first = "Training";
        String second = "Searching";
        String third = "Ping";
        assertAll(
                () -> assertTrue(StringOperations.isSameSuffix(first, second, "g")),
                () -> assertTrue(StringOperations.isSameSuffix(first, second, "ng")),
                () -> assertTrue(StringOperations.isSameSuffix(first, second, "ing"))
        );
        assertAll(
                () -> assertTrue(StringOperations.isSameSuffix(first, third, "ng")),
                () -> assertFalse(StringOperations.isSameSuffix(first, third, "ning"))
        );
    }

    @Test
    public void testGetCommonPrefix() {
        String first = "Hello";
        String second = "Hell";
        String third = "Helloween";
        String fourth = "World";
        String fifth = "Hello, my friend";
        assertEquals("Hell", StringOperations.getCommonPrefix(first, second));
        assertEquals("Hello", StringOperations.getCommonPrefix(first, third));
        assertEquals("Hello", StringOperations.getCommonPrefix(third, fifth));
        assertEquals("", StringOperations.getCommonPrefix(first, fourth));
    }

    @Test
    public void testIsPalindrome() {
        String first = "level";
        String second = "racecar";
        String third = "";
        String fourth = "Racercar";
        String fifth = "Racecar!";
        String sixth = "Radecar";
        assertTrue(StringOperations.isPalindrome(first));
        assertTrue(StringOperations.isPalindrome(second));
        assertTrue(StringOperations.isPalindrome(third));
        assertFalse(StringOperations.isPalindrome(fourth));
        assertFalse(StringOperations.isPalindrome(fifth));
        assertFalse(StringOperations.isPalindrome(sixth));
    }

    @Test
    public void testIsPalindromeIgnoreCase() {
        String first = "level";
        String second = "racecar";
        String third = "";
        String fourth = "Racecar";
        String fifth = "Racecar!";
        String sixth = "Radecar";
        assertTrue(StringOperations.isPalindromeIgnoreCase(first));
        assertTrue(StringOperations.isPalindromeIgnoreCase(second));
        assertTrue(StringOperations.isPalindromeIgnoreCase(third));
        assertTrue(StringOperations.isPalindromeIgnoreCase(fourth));
        assertFalse(StringOperations.isPalindromeIgnoreCase(fifth));
        assertFalse(StringOperations.isPalindromeIgnoreCase(sixth));
    }

    @Test
    public void testGetLongestPalindromeIgnoreCase() {
        String[] stringsEnglish = {"Level", "test", "Racecar", "RaceCar !"};
        assertEquals("Racecar", StringOperations.getLongestPalindromeIgnoreCase(stringsEnglish));
        String[] stringsRussian = {"казак", "шалаш", "Ротатор", "Анна"};
        assertEquals("Ротатор", StringOperations.getLongestPalindromeIgnoreCase(stringsRussian));
    }

    @Test
    public void testReverse() {
        assertEquals("olleH", StringOperations.reverse("Hello"));
        assertEquals("ремирп", StringOperations.reverse("пример"));
        assertEquals("шалаш", StringOperations.reverse("шалаш"));
        assertEquals("", StringOperations.reverse(""));
    }

    @Test
    public void testHasSameSubstring() {
        String first = "Hello";
        String second = "Hell";
        String third = "Helloween";
        assertAll(
                () -> assertTrue(StringOperations.hasSameSubstring(first, second, 0, 4)),
                () -> assertFalse(StringOperations.hasSameSubstring(first, second, 0, 5)),
                () -> assertTrue(StringOperations.hasSameSubstring(first, second, 1, 3)),
                () -> assertFalse(StringOperations.hasSameSubstring(first, second, 1, 4)),
                () -> assertFalse(StringOperations.hasSameSubstring(first, third, 2, 4)),
                () -> assertTrue(StringOperations.hasSameSubstring(first, third, 2, 3))
        );
    }

    @Test
    public void testEqualAfterReplaceCharacters() {
        String first = "AbcAbd";
        String second = "XbcXbd";
        String third = "141414";
        String fourth = "656565";
        assertAll(
                () -> assertTrue(StringOperations.isEqualAfterReplaceCharacters(first, 'A', 'z', second, 'X', 'z')),
                () -> assertFalse(StringOperations.isEqualAfterReplaceCharacters(first, 'A', 'y', second, 'X', 'z')),
                () -> assertFalse(StringOperations.isEqualAfterReplaceCharacters(first, 'B', 'z', second, 'X', 'z')),
                () -> assertTrue(StringOperations.isEqualAfterReplaceCharacters(third, '4', '5', fourth, '6', '1')),
                () -> assertFalse(StringOperations.isEqualAfterReplaceCharacters(third, '4', '8', fourth, '6', '1'))
        );
    }

    @Test
    public void testEqualAfterReplaceStrings() {
        String first = "AbcAbd";
        String second = "XbcXbd";
        String third = "141414";
        String fourth = "656565";
        assertAll(
                () -> assertTrue(StringOperations.isEqualAfterReplaceStrings(first, "Ab", "CD", second, "Xb", "CD")),
                () -> assertTrue(StringOperations.isEqualAfterReplaceStrings(first, "A", "CD", second, "X", "CD"))
        );
        assertAll(
                () -> assertTrue(StringOperations.isEqualAfterReplaceStrings(third, "14", "88", fourth, "65", "88")),
                () -> assertTrue(StringOperations.isEqualAfterReplaceStrings(third, "1", "88", fourth, "65", "884")),
                () -> assertFalse(StringOperations.isEqualAfterReplaceStrings(third, "1", "98", fourth, "65", "884")),
                () -> assertFalse(StringOperations.isEqualAfterReplaceStrings(third, "14", "88", fourth, "6", "88")),
                () -> assertFalse(StringOperations.isEqualAfterReplaceStrings(third, "35", "43", fourth, "16", "23"))
        );
    }

    @Test
    public void testIsPalindromeAfterRemovingSpaces() {
        String first = "Was it a car or a cat I saw";
        String second = "Do geese see God";
        String third = "А роза упала на лапу Азора";
        String fourth = "Аргентина манит негра";
        String fifth = "Кабан упал и лапу набок";
        assertTrue(StringOperations.isPalindromeAfterRemovingSpacesIgnoreCase(first));
        assertTrue(StringOperations.isPalindromeAfterRemovingSpacesIgnoreCase(second));
        assertTrue(StringOperations.isPalindromeAfterRemovingSpacesIgnoreCase(third));
        assertTrue(StringOperations.isPalindromeAfterRemovingSpacesIgnoreCase(fourth));
        assertFalse(StringOperations.isPalindromeAfterRemovingSpacesIgnoreCase(fifth));
    }

    @Test
    public void testIsEqualAfterTrimming() {
        String first = "Hello, World";
        String second = " Hello, World ";
        String third = " Hello , World ";
        assertTrue(StringOperations.isEqualAfterTrimming(first, second));
        assertFalse(StringOperations.isEqualAfterTrimming(first, third));
    }

    @Test
    public void testMakeCsvStringFromInts() {
        int[] array1 = {100, 200, 300, 400};
        assertEquals("100,200,300,400", StringOperations.makeCsvStringFromInts(array1));
        int[] array2 = {100};
        assertEquals("100", StringOperations.makeCsvStringFromInts(array2));
        int[] array3 = {};
        assertEquals("", StringOperations.makeCsvStringFromInts(array3));
    }

    @Test
    public void testMakeCsvStringFromDoubles() {
        Locale.setDefault(Locale.ENGLISH);
        double[] array1 = {100.35, 200.456, 300.1, 400.888888888};
        assertEquals("100.35,200.46,300.10,400.89", StringOperations.makeCsvStringFromDoubles(array1));
        double[] array2 = {100.12345};
        assertEquals("100.12", StringOperations.makeCsvStringFromDoubles(array2));
        double[] array3 = {};
        assertEquals("", StringOperations.makeCsvStringFromDoubles(array3));
    }

    @Test
    public void testMakeCsvStringBuilderFromInts() {
        int[] array1 = {100, 200, 300, 400};
        assertEquals("100,200,300,400", StringOperations.makeCsvStringBuilderFromInts(array1).toString());
        int[] array2 = {100};
        assertEquals("100", StringOperations.makeCsvStringBuilderFromInts(array2).toString());
        int[] array3 = {};
        assertEquals("", StringOperations.makeCsvStringBuilderFromInts(array3).toString());
    }

    @Test
    public void testMakeCsvStringBuilderFromDoubles() {
        Locale.setDefault(Locale.ENGLISH);
        double[] array1 = {100.35, 200.456, 300.1, 400.888888888};
        assertEquals("100.35,200.46,300.10,400.89", StringOperations.makeCsvStringBuilderFromDoubles(array1).toString());
        double[] array2 = {100.12345};
        assertEquals("100.12", StringOperations.makeCsvStringBuilderFromDoubles(array2).toString());
        double[] array3 = {};
        assertEquals("", StringOperations.makeCsvStringBuilderFromDoubles(array3).toString());
    }

    @Test
    public void testRemoveCharacters() {
        assertEquals("13456789", StringOperations.removeCharacters("0123456789", new int[]{0, 2}).toString());
        assertEquals("13579", StringOperations.removeCharacters("0123456789", new int[]{0, 2, 4, 6, 8}).toString());
        assertEquals("", StringOperations.removeCharacters("0123456789", new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}).toString());
    }

    @Test
    public void testInsertCharacters() {
        assertEquals("0123456789", StringOperations.insertCharacters("13456789", new int[]{0, 1}, new char[]{'0', '2'}).toString());
        assertEquals("0123456789", StringOperations.insertCharacters("13579", new int[]{0, 1, 2, 3, 4}, new char[]{'0', '2', '4', '6', '8'}).toString());
        assertEquals("0123456789", StringOperations.insertCharacters("19", new int[]{0, 1, 1, 1, 1, 1, 1, 1}, new char[]{'0', '2', '3', '4', '5', '6', '7', '8'}).toString());
    }

}
