package com.whitenight.gate.base.exception;

public class GateException extends RuntimeException {
    public GateException(){}

    public GateException(String msg){
        super(msg);
    }

    public GateException(String msg, Throwable cause){
        super(msg,cause);
    }

    public GateException(Throwable cause) {
        super(cause);
    }
}
