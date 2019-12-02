package com.whitenight.gate.security.handle;

import com.whitenight.gate.base.ModelResult;
import com.whitenight.gate.utils.RequestUtil;
import com.whitenight.gate.utils.ResponseUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    /**
     * 登录成功处理器
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws ServletException, IOException {
        // ajax
        if (RequestUtil.isAjaxRequest(request)) {
            ResponseUtil.printJson(response,  ModelResult.getSuccess("成功"));
        } else {
            response.sendRedirect("/");
        }
    }

}
