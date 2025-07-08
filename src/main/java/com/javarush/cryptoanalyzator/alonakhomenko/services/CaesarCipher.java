package com.javarush.cryptoanalyzator.alonakhomenko.services;

import com.javarush.cryptoanalyzator.alonakhomenko.constants.CryptoAlphabet;

public class CaesarCipher {
    public static String encryptText(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (CryptoAlphabet.ALPHABET_UA.contains(ch)) {
                int idx = CryptoAlphabet.ALPHABET_UA.indexOf(ch);
                int newIndex = (idx + shift) % CryptoAlphabet.ALPHABET_UA.size();
                if (newIndex < 0) {
                    newIndex += CryptoAlphabet.ALPHABET_UA.size();
                }
                result.append(CryptoAlphabet.ALPHABET_UA.get(newIndex));
            } else if (CryptoAlphabet.PUNCTUATION_MARKS.contains(ch)) {
                int idx = CryptoAlphabet.PUNCTUATION_MARKS.indexOf(ch);
                int newIndex = (idx + shift) % CryptoAlphabet.PUNCTUATION_MARKS.size();
                if (newIndex < 0) {
                    newIndex += CryptoAlphabet.PUNCTUATION_MARKS.size();
                }
                result.append(CryptoAlphabet.PUNCTUATION_MARKS.get(newIndex));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
    public static String decryptText(String text, int shift) {
        return encryptText(text, -shift);
    }
}
