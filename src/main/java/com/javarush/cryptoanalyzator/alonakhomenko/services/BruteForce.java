package com.javarush.cryptoanalyzator.alonakhomenko.services;

import com.javarush.cryptoanalyzator.alonakhomenko.entity.BruteForceResult;
import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;
import com.javarush.cryptoanalyzator.alonakhomenko.exception.ApplicationException;
import com.javarush.cryptoanalyzator.alonakhomenko.repository.ResultCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.DECRYPTED_SAVED_FILE;
import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.EXCEPTION_READING_WRITING_FILE;
import static java.nio.file.Files.writeString;

public class BruteForce implements Function {
    @Override
    public Result execute(String[] parameters) {
        try {
            // TODO finish BruteForce execute method
            String inputFile = parameters[1];
            String encryptedText = Files.readString(Paths.get(inputFile));
            BruteForceResult result = BruteForceDecrypt.bruteForceDecrypt(encryptedText);
            String outputFile = DecryptResultWriter.getDecryptedFileName(inputFile, "bruteforce", result.getLetterShift(), result.getPunctShift()); // файл с результатом
            writeString(Paths.get(outputFile), result.getDecryptedText());
            System.out.println(DECRYPTED_SAVED_FILE + outputFile);
            return new Result(ResultCode.OK, outputFile);
        } catch (IOException e) {
            return new Result(ResultCode.ERROR, new ApplicationException(EXCEPTION_READING_WRITING_FILE + e.getMessage()));
        }
    }
}