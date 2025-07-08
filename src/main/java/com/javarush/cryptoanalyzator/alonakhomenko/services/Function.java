package com.javarush.cryptoanalyzator.alonakhomenko.services;

import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;

public interface Function {
    Result execute(String[] parameters);
}
