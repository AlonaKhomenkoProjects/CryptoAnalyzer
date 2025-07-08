package com.javarush.cryptoanalyzator.alonakhomenko.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class CryptoAlphabet {
    public static final ArrayList<Character> ALPHABET_UA = new ArrayList<>(
            Arrays.asList(
                    'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і',
                    'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
                    'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я',
                    'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І',
                    'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У',
                    'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я')
    );
    public static final ArrayList<Character> PUNCTUATION_MARKS = new ArrayList<>(
            Arrays.asList('.', ',', ':', ';', '!', '?', '-', '—', '(', ')', '[', ']', '{',
                    '}', '"', '\'', '`', '…', '/', '\\', '@', '#', '$', '%', '^', '&',
                    '*', '_', '+', '=', '<', '>')
    );
}
