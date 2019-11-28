package com.whitenight.gate.model.dao;

import com.whitenight.gate.entity.Users;
import java.util.List;

public interface UsersMapper {
    List<Users> queryAll();

    void addUser(Users user);

    void update(Users user);

    void delete(Long id);
}
