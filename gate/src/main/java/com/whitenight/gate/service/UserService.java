package com.whitenight.gate.service;

import com.whitenight.gate.entity.User;
import java.util.List;

public interface UserService {
    List<User> queryAll();

    User getOne(Long id);

    void addUser(User user);

    void update(User user);

    void delete(Long id);
}
