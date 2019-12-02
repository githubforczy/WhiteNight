package com.whitenight.gate.base;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum ResponseCodeEnum {
    SUCCESS_CODE("成功","200"),
    ERROR_CODE("失败","400"),
    UN_LOGIN_CODE("请登陆","401");

    private String codeMsg;
    private String code;

    public String getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseCodeEnum{" +
            "codeMsg='" + codeMsg + '\'' +
            ", code='" + code + '\'' +
            '}';
    }
}
