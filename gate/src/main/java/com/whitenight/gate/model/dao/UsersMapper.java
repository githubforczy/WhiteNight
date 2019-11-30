package com.whitenight.gate.model.dao;

import com.whitenight.gate.entity.User;
import java.util.List;

public interface UsersMapper {
    List<User> queryAll();

    void addUser(User user);

    void update(User user);

    void delete(Long id);
}
