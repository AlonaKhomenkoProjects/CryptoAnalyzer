package com.javarush.cryptoanalyzator.alonakhomenko;

import com.javarush.cryptoanalyzator.alonakhomenko.app.Application;
import com.javarush.cryptoanalyzator.alonakhomenko.controller.MainController;
import com.javarush.cryptoanalyzator.alonakhomenko.services.CryptoAnalysesFrame;
import com.javarush.cryptoanalyzator.alonakhomenko.view.ConsoleView;
import com.javarush.cryptoanalyzator.alonakhomenko.view.View;
import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;

import javax.swing.*;
import java.util.Scanner;

import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.*;
import static com.javarush.cryptoanalyzator.alonakhomenko.constants.EntryPointConstants.*;

public class EntryPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(YOU_ARE_WELCOME);
        System.out.println();
        while (true) {
            System.out.println(START_MODE);
            System.out.println(MODE_CONSOLE);
            System.out.println(MODE_GUI);
            System.out.println(MODE_END);
            System.out.println(MODE_CHOICE);
            String mode = scanner.nextLine();

            switch (mode) {
                case MODE_1:
                    View view = new ConsoleView();
                    MainController mainController = new MainController(view);
                    Application application = new Application(mainController);

                    while (true) {
                        Result result = application.run();
                        application.printResult(result);

                        System.out.println(ONE_MORE_OPERATION);
                        String input = scanner.nextLine().trim().toLowerCase();
                        if (!input.equals(PLUS)) {
                            System.out.println(THE_END_PROGRAMME);
                            break;
                        }
                        System.out.println();
                    }
                    scanner.close();
                    return;


                case MODE_2:
                    SwingUtilities.invokeLater(() -> {
                        CryptoAnalysesFrame cryptoAnalysesFrame = new CryptoAnalysesFrame();
                        cryptoAnalysesFrame.setVisible(true);
                    });
                    System.out.println(CONTINUE_IN_GUI);
                return;

                case MODE_0:
                    System.out.println(THE_END_PROGRAMME);
                    scanner.close();
                    return;

                default:
                    System.out.println(NOT_CORRECT_NUMBER);
                    System.out.println(TRY_AGAIN);
            }
            System.out.println();
        }
    }
}