package com.whitenight.gate.utils;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class RequestUtil {
    /**
     * 判断是否是ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xReq = request.getHeader("x-requested-with");
        return StringUtils.isNotBlank(xReq) && "XMLHttpRequest".equalsIgnoreCase(xReq);
    }


}
