package com.whitenight.gate.service;

import com.whitenight.gate.entity.Users;
import java.util.List;

public interface UserService {
    List<Users> queryAll();

    Users getOne(Long id);

    void addUser(Users user);

    void update(Users user);

    void delete(Long id);
}
