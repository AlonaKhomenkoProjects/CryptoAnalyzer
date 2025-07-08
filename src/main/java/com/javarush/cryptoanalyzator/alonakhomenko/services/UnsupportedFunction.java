package com.javarush.cryptoanalyzator.alonakhomenko.services;

import com.javarush.cryptoanalyzator.alonakhomenko.entity.Result;
import com.javarush.cryptoanalyzator.alonakhomenko.exception.ApplicationException;
import com.javarush.cryptoanalyzator.alonakhomenko.repository.ResultCode;

import static com.javarush.cryptoanalyzator.alonakhomenko.constants.ApplicationCompletionConstants.UNSUPPORTED_FUNCTION;


public class UnsupportedFunction implements Function {
    @Override
    public Result execute(String[] parameters) {
        // TODO finish UnsupportedFunction execute method
        return new Result (ResultCode.ERROR, new ApplicationException(UNSUPPORTED_FUNCTION));
    }
}
