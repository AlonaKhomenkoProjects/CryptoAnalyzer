package com.javarush.cryptoanalyzator.alonakhomenko.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexScorer {
    private static final Pattern[] PATTERNS = new Pattern[] {
            // Початок з великої літери та кінець крапкою
            Pattern.compile("[А-ЯІЄҐЇ][а-яієґї\\-’\\s]+\\."),
            // Займенник + дієслово (простий варіант)
            Pattern.compile("\\b(я|ти|він|вона|воно|ми|ви|вони)\\s+[а-яієґї]{3,}\\b"),
            // Прикметникові закінчення
            Pattern.compile("[а-яієґї]{3,}(ий|а|е|і|ої|ого)\\b"),
            // слова з комою
            Pattern.compile("[а-яієґї]{3,},\\s"),
            // слова з крапкою
            Pattern.compile("[а-яієґї]{3,}\\."),
            // речення
            Pattern.compile("[А-ЯІЄҐЇ][^.!?]*[.!?]"),
            // початок слова з великої літери
            Pattern.compile("\\b[А-ЯІЄҐЇ][а-яієґї']+\\b"),
    };

    public static int scoreByRegex(String text) {
        int score = 0;
        text = text.toLowerCase();

        for (Pattern pattern : PATTERNS) {
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                score++;
            }
        }
        return score;
    }
}
