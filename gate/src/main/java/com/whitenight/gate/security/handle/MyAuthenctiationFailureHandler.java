package com.whitenight.gate.security.handle;

import com.whitenight.gate.base.ModelResult;
import com.whitenight.gate.utils.RequestUtil;
import com.whitenight.gate.utils.ResponseUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public MyAuthenctiationFailureHandler(String loginFromUrl){
        super(loginFromUrl);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

        if(RequestUtil.isAjaxRequest(request)){
            ResponseUtil.printJson(response, ModelResult.getError("用户名和密码不匹配，请重新登录"));
        }else{
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
