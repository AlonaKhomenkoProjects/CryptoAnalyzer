package com.javarush.cryptoanalyzator.alonakhomenko.view;

import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.*;

public class ConsoleView implements View {
    @Override
    public String[] getParameters() {
        // TODO finish ConsoleView getParameters method

        Scanner scanner = new Scanner(System.in);
        String mode;
        String inputFile;
        String shift = "";
        while (true){

        System.out.println(ENTER_MODE);
        System.out.println(ENTER_1_ENCODE);
        System.out.println(ENTER_2_DECODE);
        System.out.println(ENTER_3_BRUTE_FORCE);
        System.out.println(ENTER_4_EXIT);

        mode = scanner.nextLine();
            if (mode.equals(MODE_1) || mode.equals(MODE_2) || mode.equals(MODE_3))  {
                break;
            } else if (mode.equals(MODE_4)) {
                System.out.println(THE_END_PROGRAMME);
                System.exit(0);
            }else {
                System.out.println(INVALID_MODE);
                System.out.println(TRY_AGAIN);
                System.out.println();
            }
        }

        while (true) {
        System.out.println(ENTER_NANE_FILE);
        System.out.println(EXAMPLE_FOR_MANE);
       inputFile = scanner.nextLine();
            if (Files.exists(Paths.get(inputFile))) {
                break;
            } else {
                System.out.println(FILE_NOT_FOUND);
                System.out.println(TRY_AGAIN);
                System.out.println();
            }
        }
        if (mode.equals(MODE_1) || mode.equals(MODE_2)) {
        while (true) {
              System.out.println(ENTER_SHIFT);
                shift = scanner.nextLine();
                try {
                    int shiftValue = Integer.parseInt(shift);
                    if (shiftValue >= 0) {
                        break;
                    } else {
                        System.out.println(NON_NEGATIVE_NUMBER);
                        System.out.println();
                    }
                } catch (NumberFormatException e) {
                    System.out.println(NOT_NUMBER);
                    System.out.println(TRY_AGAIN);
                    System.out.println();
                }
            }
        }

        return new String[]{mode, inputFile, shift};
    }


    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }
    }
}
