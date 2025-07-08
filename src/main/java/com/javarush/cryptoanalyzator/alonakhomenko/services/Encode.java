package com.javarush.cryptoanalyzator.alonakhomenko.services;
import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;
import com.javarush.cryptoanalyzator.alonakhomenko.exception.ApplicationException;
import com.javarush.cryptoanalyzator.alonakhomenko.repository.ResultCode;
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Paths;

import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.*;
import static com.javarush.cryptoanalyzator.alonakhomenko.repository.ResultCode.ERROR;

public class Encode implements Function {
    @Override
    public Result execute(String[] parameters) {
        try {
            // TODO finish Decode execute method
        String inputFile = parameters[1];
        int shift = Integer.parseInt(parameters[2]);
        String outputFile = DecryptResultWriter.getDecryptedFileName(inputFile, "encrypt");
        try {
            String text = Files.readString(Paths.get(inputFile));
            String encryptedText = CaesarCipher.encryptText(text, shift);
            Files.writeString(Paths.get(outputFile), encryptedText);
            System.out.println(ENCRYPTED_SAVED_FILE + outputFile);
            return new Result(ResultCode.OK, outputFile);
        } catch (IOException e) {
            return new Result(ResultCode.ERROR, new ApplicationException(EXCEPTION_READING_WRITING_FILE + e.getMessage()));
        }
                } catch (Exception e) {
            return new Result(ERROR, new ApplicationException(EXCEPTION_ENCODE, e));
        }
    }
}
