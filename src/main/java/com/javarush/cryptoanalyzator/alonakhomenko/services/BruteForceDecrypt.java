package com.javarush.cryptoanalyzator.alonakhomenko.services;

import com.javarush.cryptoanalyzator.alonakhomenko.constants.CryptoAlphabet;
import com.javarush.cryptoanalyzator.alonakhomenko.entity.BruteForceResult;

import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.*;

public class BruteForceDecrypt {

    public static BruteForceResult bruteForceDecrypt(String encryptedText) {
        int maxAlphabetShift = CryptoAlphabet.ALPHABET_UA.size();
        int maxPunctuationShift = CryptoAlphabet.PUNCTUATION_MARKS.size();

        int bestLetterShift = 0;
        int bestPunctShift = 0;
        String bestDecryption = "";
        int bestScore = 0;

        // 1. Шукаємо найкращий letterShift (punctShift = 0 або фіксований)
        for (int letterShift = 1; letterShift < maxAlphabetShift; letterShift++) {
            String candidate = decryptWithShifts(encryptedText, letterShift, 0);
            int score = RegexScorer.scoreByRegex(candidate);

            if (score > bestScore) {
                bestScore = score;
                bestLetterShift = letterShift;
                bestDecryption = candidate;
            }
        }

        // 2. Після фіксації letterShift — підбираємо кращий punctShift
        bestScore = 0;

        for (int punctShift = 1; punctShift < maxPunctuationShift; punctShift++) {
            String candidate = decryptWithShifts(encryptedText, bestLetterShift, punctShift);
            int score = RegexScorer.scoreByRegex(candidate);

            if (score > bestScore) {
                bestScore = score;
                bestPunctShift = punctShift;
                bestDecryption = candidate;
            }
        }

        // 3. Виводимо результат
        System.out.println(BETTER_RESULT);
        System.out.println(KEY_LETTERS + bestLetterShift + KEY_PUNCTUATION + bestPunctShift);
//        System.out.println(bestDecryption);
        return new BruteForceResult(bestDecryption, bestLetterShift, bestPunctShift);
    }

    private static String decryptWithShifts(String text, int letterShift, int punctShift) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (CryptoAlphabet.ALPHABET_UA.contains(c)) {
                int index = CryptoAlphabet.ALPHABET_UA.indexOf(c);
                int newIndex = (index - letterShift + CryptoAlphabet.ALPHABET_UA.size()) % CryptoAlphabet.ALPHABET_UA.size();
                result.append(CryptoAlphabet.ALPHABET_UA.get(newIndex));
            } else if (CryptoAlphabet.PUNCTUATION_MARKS.contains(c)) {
                int index = CryptoAlphabet.PUNCTUATION_MARKS.indexOf(c);
                int newIndex = (index - punctShift + CryptoAlphabet.PUNCTUATION_MARKS.size()) % CryptoAlphabet.PUNCTUATION_MARKS.size();
                result.append(CryptoAlphabet.PUNCTUATION_MARKS.get(newIndex));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}