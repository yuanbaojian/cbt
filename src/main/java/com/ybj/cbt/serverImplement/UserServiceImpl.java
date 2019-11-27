package com.ybj.cbt.serverImplement;

import com.ybj.cbt.mapper.UserMapper;
import com.ybj.cbt.model.User;
import com.ybj.cbt.serverInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int getUserNum() {
        return 0;
    }

    @Override
    public List<Map<String, String>> GetAlluser() {
        return null;
    }

    @Override
    public User GetUserById(Integer user_id) {
        return null;
    }

    @Override
    public void InsertUser(User user) {

    }

    @Override
    public User login(Integer user_id, String user_password) {
        return null;
    }

    @Override
    public void Update(User user) {

    }

    @Override
    public boolean checkPwd(String pwd, Integer user_id) {
        return false;
    }

    @Override
    public void changePwd(String pwd, Integer user_id) {

    }

    @Override
    public User checkUser(User user) {
        return userMapper.checkUser(user);
    }

    @Override
    public User findByName(String username) {
        return userMapper.getUserByLoginName(username);
    }


}
