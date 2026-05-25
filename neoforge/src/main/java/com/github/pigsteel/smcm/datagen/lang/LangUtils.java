package com.github.pigsteel.smcm.datagen.lang;

import static com.github.pigsteel.smcm.datagen.lang.SMCMFrenchHelper.MasculineArticle;
import static com.github.pigsteel.smcm.datagen.lang.SMCMFrenchHelper.MasculineArticleShort;

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

    public static String FrenchMasculineOrShort(String string) {
        if (string.startsWith("a") || string.startsWith("e") || string.startsWith("é") || string.startsWith("è") || string.startsWith("i") || string.startsWith("o") || string.startsWith("u") || string.startsWith("h")) {
            return MasculineArticleShort + string;
        } else {
            return MasculineArticle + string;
        }
    }
}
