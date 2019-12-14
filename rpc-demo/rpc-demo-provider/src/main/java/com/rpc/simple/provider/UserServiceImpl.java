package com.rpc.simple.provider;

import com.rpc.demo.model.User;
import com.rpc.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public List<User> findById(int id) {
        User user = new User();
        user.setName("lxz");
        user.setAge(30);
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        return users;
    }
}
