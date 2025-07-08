package com.javarush.cryptoanalyzator.alonakhomenko.services;

import com.javarush.cryptoanalyzator.alonakhomenko.app.Application;
import com.javarush.cryptoanalyzator.alonakhomenko.controller.MainController;
import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;
import com.javarush.cryptoanalyzator.alonakhomenko.repository.ResultCode;
import com.javarush.cryptoanalyzator.alonakhomenko.view.GUIView;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;


import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.*;
import static com.javarush.cryptoanalyzator.alonakhomenko.constants.FunctionCodeConstants.*;

public class CryptoAnalysesFrame extends JFrame {

    private JButton button1, button2, button3;
    private JPanel mainPanel;

    public CryptoAnalysesFrame() {
        initComponents();
        addActionListenerClickHere();
        displayTheWindow();
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout(25, 25));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 25));

        JLabel titleLabel = new JLabel(ENTER_MODE, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 15));

        button1 = new JButton(ENCODE);
        JLabel label1 = new JLabel(ENTER_1_ENCODE_GUI);
        button2 = new JButton(DECODE);
        JLabel label2 = new JLabel(ENTER_2_DECODE_GUI);
        button3 = new JButton(BRUTE_FORCE);
        JLabel label3 = new JLabel(ENTER_3_BRUTE_FORCE_GUI);

        centerPanel.add(button1); centerPanel.add(label1);
        centerPanel.add(button2); centerPanel.add(label2);
        centerPanel.add(button3); centerPanel.add(label3);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        this.setContentPane(mainPanel);
    }

    private void addActionListenerClickHere() {
        button1.addActionListener(e -> showParameterInputs(ENCODE));
        button2.addActionListener(e -> showParameterInputs(DECODE));
        button3.addActionListener(e -> showParameterInputs(BRUTE_FORCE));
    }

    private void showParameterInputs(String mode) {
        mainPanel.removeAll();

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 100, 100));
        JLabel titleLabel = new JLabel(MODE + mode, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Вхідний файл
        JTextField inputFileField = new JTextField();
        JButton inputBrowse = createBrowseButton(CHOICE_FILE, false, path -> inputFileField.setText(path));
        inputPanel.add(new JLabel(INPUT_FILE));
        inputPanel.add(createFileChooserField(inputFileField, inputBrowse));

        // Зсув (тільки для encode/decode)
        JTextField shiftField = new JTextField();
        if (!mode.equals(BRUTE_FORCE)) {
            inputPanel.add(new JLabel(ENTER_SHIFT));
            inputPanel.add(shiftField);
        }

        // Кнопки "Запустити" і "Назад"
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton runButton = new JButton(RUN_GUI);
        JButton backButton = new JButton(BACK);
        buttonRow.add(backButton);
        buttonRow.add(runButton);

        inputPanel.add(new JLabel()); // Порожня клітинка
        inputPanel.add(buttonRow);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

// Обробка кнопки запуску
        runButton.addActionListener(e -> {
            // ...
        });

// Обробка кнопки "Назад"
        backButton.addActionListener(e2 -> resetToMainMenu());

        mainPanel.revalidate();
        mainPanel.repaint();


        mainPanel.add(inputPanel, BorderLayout.CENTER);

        runButton.addActionListener(e -> {
            String inputFile = inputFileField.getText().trim();
            String shift = (!mode.equals(BRUTE_FORCE)) ? shiftField.getText().trim() : "";

            if (!validateInputs(inputFile, "auto-generated", shift, mode)) return;

            try {
                // Формуємо parameters без outputFile — він буде згенерований у Function
                String[] parameters = (!mode.equals(BRUTE_FORCE))
                        ? new String[]{mode, inputFile, shift}
                        : new String[]{mode, inputFile};

                // Створюємо контролер і GUIView
                MainController controller = new MainController();
                GUIView guiView = new GUIView();
                controller.setView(guiView);
                controller.setParameters(parameters);

                // Запускаємо застосунок
                Application app = new Application(controller);
                Result result = app.run();

                // Обробка результату
                if (result.getResultCode() == ResultCode.OK) {
                    String outputFilePath = String.valueOf(result.getMessageOrPath()); // <-- беремо шлях із Result
                    showSuccessPanel(outputFilePath);  // <-- показуємо правильний файл
                } else {
                    JOptionPane.showMessageDialog(this,
                            EXCEPTION_TITTLE+ result.getMessageOrPath(),
                            EXCEPTION_TITTLE, JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        EXCEPTION_WORK + ex.getMessage(),
                        EXCEPTION_TITTLE, JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showSuccessPanel(String outputFile) {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout(15, 15));

        JLabel messageLabel = new JLabel(
                "<html><div style='text-align: center;'>Дані отримано успішно!<br><br>Файл збережено у:<br><b>" +
                        outputFile + "</b></div></html>", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton mainMenuButton = new JButton(BACK_TO_MENU);
        JButton exitButton = new JButton(END_PROGRAMME);

        buttonPanel.add(mainMenuButton);
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainMenuButton.addActionListener(e -> resetToMainMenu());
        exitButton.addActionListener(e -> System.exit(0));

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void resetToMainMenu() {
        initComponents();
        addActionListenerClickHere();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JButton createBrowseButton(String title, boolean saveDialog, Consumer<String> pathConsumer) {
        JButton button = new JButton(title);
        button.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = saveDialog ? chooser.showSaveDialog(this) : chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                pathConsumer.accept(chooser.getSelectedFile().getAbsolutePath());
            }
        });
        return button;
    }

    private JPanel createFileChooserField(JTextField field, JButton browseButton) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(field, BorderLayout.CENTER);
        panel.add(browseButton, BorderLayout.EAST);
        return panel;
    }

    private boolean validateInputs(String inputFile, String outputFile, String shift, String mode) {
        if (inputFile.isEmpty() || outputFile == null || outputFile.isEmpty()
                || (!mode.equals(BRUTE_FORCE) && shift.isEmpty())) {
            JOptionPane.showMessageDialog(this, INFORM_FOR_FIELDS);
            return false;
        }

        if (!mode.equals(BRUTE_FORCE)) {
            try {
                int shiftValue = Integer.parseInt(shift);
                if (shiftValue < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, NON_NEGATIVE_NUMBER);
                return false;
            }
        }

        return true;
    }

    private void displayTheWindow() {
        this.setTitle(YOU_ARE_WELCOME);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CryptoAnalysesFrame::new);
    }
}
