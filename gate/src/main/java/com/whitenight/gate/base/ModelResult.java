package com.whitenight.gate.base;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口响应工具类
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelResult implements Serializable {
    private String msg;
    private String code;
    private Object data;

    public static ModelResult getSuccess(String msg){
        return new ModelResult(msg
            ,ResponseCodeEnum.SUCCESS_CODE.getCode()
            ,null);
    }

    public static ModelResult getSuccess(String msg, Object data){
        return new ModelResult(ResponseCodeEnum.SUCCESS_CODE.getCodeMsg()
            ,ResponseCodeEnum.SUCCESS_CODE.getCode()
            ,data);
    }

    public static ModelResult getError(String msg){
        return new ModelResult(ResponseCodeEnum.SUCCESS_CODE.getCodeMsg()
            ,ResponseCodeEnum.SUCCESS_CODE.getCode()
            ,null);
    }

    public static ModelResult getError(String msg, Object data){
        return new ModelResult(ResponseCodeEnum.ERROR_CODE.getCodeMsg()
            ,ResponseCodeEnum.ERROR_CODE.getCode()
            ,data);
    }

    /**
     * 创建一个未登录响应对象
     *
     * @return
     */
    public static <T> ModelResult unLogin() {
        return new ModelResult(ResponseCodeEnum.UN_LOGIN_CODE.getCodeMsg(), "请先登录", null);
    }
}
