package com.ybj.cbt.serverImplement;

import com.ybj.cbt.mapper.UserMapper;
import com.ybj.cbt.model.User;
import com.ybj.cbt.serverInterface.UserServerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServerImpl implements UserServerInterface {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String userId) {
        User user=userMapper.selectByPrimaryKey(Long.valueOf(userId));
        return user;
    }

    @Override
    public List<User> getAllUser() {
       return userMapper.selectAll();
    }
}
