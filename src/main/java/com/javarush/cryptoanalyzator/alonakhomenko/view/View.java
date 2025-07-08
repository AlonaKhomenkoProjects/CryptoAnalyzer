package com.javarush.cryptoanalyzator.alonakhomenko.view;

import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;

public interface View {
    String [] getParameters();
    void printResult(Result result);

}
