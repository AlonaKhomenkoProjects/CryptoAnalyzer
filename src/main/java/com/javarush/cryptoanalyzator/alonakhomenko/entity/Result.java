package com.javarush.cryptoanalyzator.alonakhomenko.entity;

import com.javarush.cryptoanalyzator.alonakhomenko.exception.ApplicationException;
import com.javarush.cryptoanalyzator.alonakhomenko.repository.ResultCode;
public class Result {
    private ResultCode resultCode;
    private ApplicationException applicationException;
    private String messageOrPath; // може бути шлях до файлу або повідомлення

    public Result(ResultCode resultCode, ApplicationException applicationException) {
        this.resultCode = resultCode;
        this.applicationException = applicationException;
        this.messageOrPath = String.valueOf(applicationException);
    }

    public Result(ResultCode resultCode, String messageOrPath) {
        this.resultCode = resultCode;
        this.messageOrPath = messageOrPath;
    }

    public Result(ResultCode resultCode, ApplicationException applicationException, String messageOrPath) {
        this.resultCode = resultCode;
        this.applicationException = applicationException;
        this.messageOrPath = messageOrPath;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public String getMessageOrPath() {
        return messageOrPath;
    }

    public ApplicationException getApplicationException() {
        return applicationException;
    }
}
