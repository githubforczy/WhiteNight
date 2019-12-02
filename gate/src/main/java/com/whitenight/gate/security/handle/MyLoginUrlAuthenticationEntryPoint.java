package com.whitenight.gate.security.handle;

import com.whitenight.gate.base.ModelResult;
import com.whitenight.gate.utils.RequestUtil;
import com.whitenight.gate.utils.ResponseUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class MyLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    /**
     * @param loginFormUrl URL where the login page can be found. Should either be relative to the
     * web-app context path (include a leading {@code /}) or an absolute URL.
     */
    public MyLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        // ajax
        if (RequestUtil.isAjaxRequest(request)) {
            ResponseUtil.printJson(response, ModelResult.unLogin());
        } else {
            super.commence(request, response, authException);
        }
    }
}
