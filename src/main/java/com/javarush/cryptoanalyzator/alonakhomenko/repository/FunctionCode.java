package com.javarush.cryptoanalyzator.alonakhomenko.repository;

import com.javarush.cryptoanalyzator.alonakhomenko.services.Decode;
import com.javarush.cryptoanalyzator.alonakhomenko.services.Encode;
import com.javarush.cryptoanalyzator.alonakhomenko.services.Function;
import com.javarush.cryptoanalyzator.alonakhomenko.services.UnsupportedFunction;
import com.javarush.cryptoanalyzator.alonakhomenko.services.BruteForce;

public enum FunctionCode {
    ENCODE(new Encode()), DECODE (new Decode()), UNSUPPORTED_FUNCTION (new UnsupportedFunction()), BRUTE_FORCE(new BruteForce());
    private Function function;

    FunctionCode(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}
