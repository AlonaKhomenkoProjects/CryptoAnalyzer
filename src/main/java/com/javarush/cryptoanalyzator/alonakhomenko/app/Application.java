package com.javarush.cryptoanalyzator.alonakhomenko.app;
import com.javarush.cryptoanalyzator.alonakhomenko.controller.MainController;
import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;
import com.javarush.cryptoanalyzator.alonakhomenko.repository.FunctionCode;
import com.javarush.cryptoanalyzator.alonakhomenko.services.Function;
import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.*;
import static com.javarush.cryptoanalyzator.alonakhomenko.constants.FunctionCodeConstants.*;


public class Application {
    private final MainController mainController;

    public Application(MainController mainController) {
        this.mainController = mainController;
    }
    public Result run() {
        String[] parameters;

        // Пріоритет мають параметри з контролера (GUI їх передає напряму)
        if (mainController.getParameters() != null) {
            parameters = mainController.getParameters();
        } else {
            // Консольний режим — параметри з view
            parameters = mainController.getView().getParameters();
        }

        String mode = normalizeMode(parameters[0]);
        Function function = getFunction(mode);

        return function.execute(parameters);
    }

    public void printResult(Result result) {
        mainController.getView().printResult(result);
    }
    // Перетворення режиму "1" → "encode", "2" → "decode" тощо
    private String normalizeMode(String mode) {
        return switch (mode.trim()) {
            case MODE_1 -> ENCODE;
            case MODE_2 -> DECODE;
            case MODE_3 -> BRUTE_FORCE;
            case MODE_4 -> EXIT;
            default -> mode.toLowerCase();
        };
    }
    // Отримання функції з FunctionCode
    private Function getFunction(String mode) {
        try {
            return FunctionCode.valueOf(mode.toUpperCase()).getFunction();
        } catch (IllegalArgumentException e) {
            return FunctionCode.UNSUPPORTED_FUNCTION.getFunction();
        }
    }
}
