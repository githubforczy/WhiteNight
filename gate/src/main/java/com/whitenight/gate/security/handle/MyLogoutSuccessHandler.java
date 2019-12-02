package com.whitenight.gate.security.handle;

import com.whitenight.gate.base.ModelResult;
import com.whitenight.gate.utils.RequestUtil;
import com.whitenight.gate.utils.ResponseUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        // ajax登出
        if (RequestUtil.isAjaxRequest(request)) {
            ResponseUtil.printJson(response, ModelResult.getSuccess("成功"));
        } else {
            response.sendRedirect("/");
        }
    }
}
