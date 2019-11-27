package com.ybj.cbt.serverInterface;

import com.ybj.cbt.model.User;

import java.util.List;
import java.util.Map;


public interface UserService {

    public int getUserNum();

    public List<Map<String, String>> GetAlluser();

    public User GetUserById(Integer user_id);

    public void InsertUser(User user);

    public User login(Integer user_id, String user_password);

    public  void Update(User user);

    public  boolean checkPwd(String pwd, Integer user_id);

    void changePwd(String pwd, Integer user_id);


    User checkUser(User user);

    User findByName(String username);
}
