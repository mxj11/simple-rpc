package com.rpc.demo.service;

import com.rpc.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findById(int id);
}
