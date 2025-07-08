package com.javarush.cryptoanalyzator.alonakhomenko.controller;

import com.javarush.cryptoanalyzator.alonakhomenko.view.View;

public class MainController {
    private View view;
    private String[] parameters;

    public MainController() {
    }

    public MainController(View view) {
        this.view = view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }
}
