package com.whitenight.gate.security.service;

import com.whitenight.gate.entity.User;
import com.whitenight.gate.model.dao.UserMapper;
import java.security.Principal;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
public class CasUserDetailsServiceImpl extends AbstractCasAssertionUserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    protected UserDetails loadUserDetails(final Assertion assertion) {
        Principal principal = assertion.getPrincipal();
        String principalName = principal.getName();
        log.info("用户：{} 单点登录成功",principalName);
        User user = userMapper.findByUserName(principalName);
        Assert.isNull(user,"User Not Found");
        return user;
    }
}
