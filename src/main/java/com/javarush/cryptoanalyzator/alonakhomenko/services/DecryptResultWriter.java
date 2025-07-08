package com.javarush.cryptoanalyzator.alonakhomenko.services;

import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.INVALID_MODE;

public class DecryptResultWriter {public static String getDecryptedFileName(String inputFile, String mode) {
    return getDecryptedFileName(inputFile, mode, -1, -1); // -1 означає: ключі не вказані
}

    public static String getDecryptedFileName(String inputFile, String mode, int letterShift, int punctShift) {
        String suffix;

        switch (mode.toLowerCase()) {
            case "encrypt":
                suffix = "_encrypted";
                break;
            case "decrypt":
                suffix = "_decrypted";
                break;
            case "bruteforce":
                if (letterShift >= 0 && punctShift >= 0) {
                    suffix = "_bruteforce_letter" + letterShift + "punctuation" + punctShift;
                } else {
                    suffix = "_bruteforce";
                }
                break;
            default:
                throw new IllegalArgumentException(INVALID_MODE + mode);
        }

        int dotIndex = inputFile.lastIndexOf('.');
        if (dotIndex != -1) {
            String name = inputFile.substring(0, dotIndex);
            String extension = inputFile.substring(dotIndex);
            return name + suffix + extension;
        } else {
            return inputFile + suffix;
        }
    }
}