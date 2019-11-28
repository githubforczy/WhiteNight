package com.whitenight.gate.service.impl;

import com.whitenight.gate.entity.Users;
import com.whitenight.gate.model.dao.UsersMapper;
import com.whitenight.gate.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UsersMapper usersMapper;

    @Override
    public List<Users> queryAll() {
        return usersMapper.queryAll();
    }

    @Override
    public Users getOne(Long id) {
        return null;
    }

    @Override
    public void addUser(Users user) {
        usersMapper.addUser(user);
    }

    @Override
    public void update(Users user) {
        usersMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        usersMapper.delete(id);
    }
}
