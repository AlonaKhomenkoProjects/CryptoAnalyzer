package com.javarush.cryptoanalyzator.alonakhomenko.entity;

public class BruteForceResult {
    private final String decryptedText;
    private final int letterShift;
    private final int punctShift;

    public BruteForceResult(String decryptedText, int letterShift, int punctShift) {
        this.decryptedText = decryptedText;
        this.letterShift = letterShift;
        this.punctShift = punctShift;
    }

    public String getDecryptedText() {
        return decryptedText;
    }

    public int getLetterShift() {
        return letterShift;
    }

    public int getPunctShift() {
        return punctShift;
    }
}
