package com.github.pigsteel.smcm.datagen.lang;

public class LangUtils {
    public static String c(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        int firstCodePoint = input.codePointAt(0);
        int titleCase = Character.toTitleCase(firstCodePoint);

        return new StringBuilder()
                .appendCodePoint(titleCase)
                .append(input.substring(Character.charCount(firstCodePoint)))
                .toString();
    }

    public static String s(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        int firstCodePoint = input.codePointAt(0);
        int titleCase = Character.toLowerCase(firstCodePoint);

        return new StringBuilder()
                .appendCodePoint(titleCase)
                .append(input.substring(Character.charCount(firstCodePoint)))
                .toString();
    }
}
