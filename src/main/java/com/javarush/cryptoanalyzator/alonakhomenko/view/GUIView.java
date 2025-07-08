package com.javarush.cryptoanalyzator.alonakhomenko.view;

import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;
import com.javarush.cryptoanalyzator.alonakhomenko.services.CryptoAnalysesFrame;

import javax.swing.*;

import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.GUI_NOT_FOR_PARAMETERS;
import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.RESULT;

public class GUIView implements View {

    public GUIView() {
            }

    public void start() {
        SwingUtilities.invokeLater(CryptoAnalysesFrame::new);
    }

    @Override
    public String[] getParameters() {
        throw new UnsupportedOperationException(GUI_NOT_FOR_PARAMETERS);
    }

    @Override
    public void printResult(Result result) {
        JOptionPane.showMessageDialog(null, result.toString(), RESULT, JOptionPane.INFORMATION_MESSAGE);
    }

}
