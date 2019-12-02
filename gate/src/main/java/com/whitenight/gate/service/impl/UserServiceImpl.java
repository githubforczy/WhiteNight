package com.whitenight.gate.service.impl;

import com.whitenight.gate.base.annotation.WnLog;
import com.whitenight.gate.entity.User;
import com.whitenight.gate.model.dao.UserMapper;
import com.whitenight.gate.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper usersMapper;

    @Override
    public List<User> queryAll() {
        return usersMapper.queryAll();
    }

    @Override
    public User getOne(Long id) {
        return null;
    }

    @Override
    @WnLog
    public void addUser(User user) {
        usersMapper.addUser(user);
    }

    @Override
    public void update(User user) {
        usersMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        usersMapper.delete(id);
    }
}
