package net.thumbtack.school.base;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringOperations {

    public static int getSummaryLength(String[] strings) {
        return String.join("", strings).length();
    }
    //Возвращает суммарную длину строк, заданных массивом strings.

    public static String getFirstAndLastLetterString(String string) {
        return String.valueOf(string.charAt(0)) + string.charAt(string.length() - 1);
    }
    //Возвращает двухсимвольную строку, состоящую из начального и конечного символов заданной строки.

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        return string1.charAt(index) == string2.charAt(index);
    }
    //Возвращает true, если обе строки в позиции index содержат один и тот же символ, иначе false.

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        return string1.indexOf(character) == string2.indexOf(character);
    }
    //Возвращает true, если в обеих строках первый встреченный символ character находится в одной и той же позиции.
    //Просмотр строк ведется от начала.

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
//        String reversedString1 = new StringBuffer(string1).reverse().toString();
//        String reversedString2 = new StringBuffer(string2).reverse().toString();
//        return reversedString1.indexOf(character) == reversedString2.indexOf(character);
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);

    }
    //Возвращает true, если в обеих строках первый встреченный символ character находится в одной и той же позиции.
    //Просмотр строк ведется от конца.

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        return string1.indexOf(str) == string2.indexOf(str);
    }
    //Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
    //Просмотр строк ведется от начала.

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
//        String reversedString1 = new StringBuffer(string1).reverse().toString();
//        String reversedString2 = new StringBuffer(string2).reverse().toString();
//        return reversedString1.indexOf(str) == reversedString2.indexOf(str);
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);

    }
    //Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
    //Просмотр строк ведется от конца.

    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);
    }
    //Возвращает true, если строки равны.

    public static boolean isEqualIgnoreCase(String string1, String string2) {
        return string1.equalsIgnoreCase(string2);

    }
    //Возвращает true, если строки равны без учета регистра (например, строки “abc” и “aBC” в этом смысле равны).

    public static boolean isLess(String string1, String string2) {
        return string1.compareTo(string2) < 0;
    }
    //Возвращает true, если строка string1 меньше строки string2.

    public static boolean isLessIgnoreCase(String string1, String string2) {
        return string1.compareToIgnoreCase(string2) < 0;

    }
    //Возвращает true, если строка string1 меньше строки string2 без учета регистра
    //(например, строка “abc” меньше строки “ABCd” в этом смысле).

    public static String concat(String string1, String string2) {
//        return string1.concat(string2);
        return string1 + string2;
    }
    //Возвращает строку, полученную путем сцепления двух строк.

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
//        if (prefix.length() > string1.length() || prefix.length() > string2.length()) return false;
//        for (int i = 0; i < prefix.length(); i++) {
//            if (string1.charAt(i) != string2.charAt(i) || string1.charAt(i) != prefix.charAt(i)) return false;
//        }
//        return true;
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }
    //Возвращает true, если обе строки string1 и string2 начинаются с одной и той же подстроки prefix.

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
//        if (suffix.length() > string1.length() || suffix.length() > string2.length()) return false;
//        for (int i = 0; i < suffix.length(); i++) {
//            char c = string1.charAt(string1.length() - i - 1);
//            char c1 = string2.charAt(string2.length() - i - 1);
//            char c2 = suffix.charAt(suffix.length() - i - 1);
//            if (c != c1 || c != c2) return false;
//        }
//        return true;
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }
    //Возвращает true, если обе строки string1 и string2 заканчиваются одной и той же подстрокой suffix.

    public static String getCommonPrefix(String string1, String string2) {
        StringBuilder builder = new StringBuilder("");
        int smallestLength = Math.min(string1.length(), string2.length());
        for (int i = 0; i < smallestLength; i++) {
            if (string1.charAt(i) == string2.charAt(i)) builder.append(string1.charAt(i));
            else break;
        }
        return builder.toString();
    }
    //Возвращает самое длинное общее “начало” двух строк. Если у строк нет общего начала, возвращает пустую строку.

    public static String reverse(String string) {
        return new StringBuffer(string).reverse().toString();
    }
    //Возвращает перевернутую строку.

    public static boolean isPalindrome(String string) {
        return string.equals(new StringBuilder(string).reverse().toString());
    }
    //Возвращает true, если строка является палиндромом, то есть читается слева направо так же, как и справа налево.

    public static boolean isPalindromeIgnoreCase(String string) {
        return string.equalsIgnoreCase(new StringBuilder(string).reverse().toString());
    }
    //Возвращает true, если строка является палиндромом, то есть читается слева направо так же,
    //как и справа налево, без учета регистра.

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        String s = "";
        for (String string : strings) {
            StringBuilder palindrom = new StringBuilder(string).reverse();
            if (string.equalsIgnoreCase(palindrom.toString()) && string.length() > s.length()) s = string;
        }
        return s;
    }
    //Возвращает самый длинный палиндром (без учета регистра) из массива заданных строк.
    //Если в массиве нет палиндромов, возвращает пустую строку.

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        if (string1.length() < length + index || string2.length() < length + index) return false;
        return string1.substring(index, length + index).equals(string2.substring(index, length + index));
    }
    //Возвращает true, если обе строки содержат один и тот же фрагмент длиной length, начиная с позиции index.

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1,
                                                        char replaceByInStr1, String string2,
                                                        char replaceInStr2, char replaceByInStr2) {
        return string1.replace(replaceInStr1, replaceByInStr1).equals(string2.replace(replaceInStr2, replaceByInStr2));
    }
    //Возвращает true, если после замены в string1 всех вхождений replaceInStr1 на replaceByInStr1 и
    //замены в string2 всех вхождений replaceInStr2 на replaceByInStr2 полученные строки равны.

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1,
                                                     String replaceByInStr1, String string2, String replaceInStr2,
                                                     String replaceByInStr2) {
        return string1.replace(replaceInStr1, replaceByInStr1).equals(string2.replace(replaceInStr2, replaceByInStr2));

    }
    //Возвращает true, если после замены в string1 всех вхождений строки replaceInStr1 на replaceByInStr1 и
    //замены в string2 всех вхождений replaceInStr2 на replaceByInStr2 полученные строки равны.

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        String string2 = string.replace(" ", "");
        return string2.equalsIgnoreCase(new StringBuilder(string2).reverse().toString());
    }
    //Возвращает true, если строка после выбрасывания из нее всех пробелов является палиндромом, без учета регистра.

    public static boolean isEqualAfterTrimming(String string1, String string2) {
        return string1.trim().equals(string2.trim());
    }
    //Возвращает true, если две строки равны, если не принимать во внимание все пробелы в начале и конце каждой строки.

    public static String makeCsvStringFromInts(int[] array) {
//        String[] strings = new String[array.length];
//        for (int i = 0; i < array.length; i++) {
//            strings[i] = String.valueOf(array[i]);
//        }
//        return String.join(",", strings);
        return Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }
    //Для заданного массива целых чисел создает текстовую строку, в которой числа разделены знаком “запятая”
    //(т.н. формат CSV - comma separated values). Для пустого массива возвращается пустая строка.

    public static String makeCsvStringFromDoubles(double[] array) {
//        String[] strings = new String[array.length];
//        for (int i = 0; i < array.length; i++) {
//            strings[i] = String.format("%.2f", array[i]);
//        }
//        return String.join(",", strings);
        return Arrays.stream(array)
                .mapToObj(v -> String.format("%.2f", v))
                .collect(Collectors.joining(","));
    }
    //Для заданного массива вещественных чисел создает текстовую строку, в которой числа разделены знаком “запятая”,
    //причем каждое число записывается с двумя знаками после точки. Для пустого массива возвращается пустая строка.

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
//        String[] strings = new String[array.length];
//        for (int i = 0; i < array.length; i++) {
//            strings[i] = String.valueOf(array[i]);
//        }
//        return new StringBuilder(String.join(",", strings));
        return new StringBuilder(Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
    }
    //То же, что и в упражнении 25, но возвращает StringBuilder.

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
//        String[] strings = new String[array.length];
//        for (int i = 0; i < array.length; i++) {
//            strings[i] = String.format("%.2f", array[i]);
//        }
//        return new StringBuilder(String.join(",", strings));
        return new StringBuilder(Arrays.stream(array)
                .mapToObj(v -> String.format("%.2f", v))
                .collect(Collectors.joining(",")));
    }
    //То же, что и в упражнении 26, но возвращает StringBuilder.

    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < positions.length; i++) {
            stringBuilder.deleteCharAt(positions[i] - i);
        }
        return stringBuilder;
    }
    //Удаляет из строки символы, номера которых заданы в массиве positions.
    //Предполагается, что будут передаваться только допустимые номера, упорядоченные по возрастанию.
    //Номера позиций для удаления указаны для исходной строки. Возвращает полученный в результате StringBuilder.

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder stringBuilder = new StringBuilder(string);
        int position = 0;
        for (int i = 0; i < positions.length; i++) {
            stringBuilder.insert(positions[i] + position, characters[i]);
            position++;
        }
        return stringBuilder;
    }
    //Вставляет в строку символы. Массивы positions и characters имеют одинаковую длину.
    //В позицию positions[i] в исходной строке string вставляется символ characters[i].
    //Если в массиве positions один и тот же номер позиции повторяется несколько раз, это значит,
    //что в указанную позицию вставляется несколько символов, в том порядке, в котором они перечислены в массиве characters.
    //Предполагается, что будут передаваться только допустимые номера, упорядоченные по неубыванию.
    //Возвращает полученный в результате StringBuilder.

}
