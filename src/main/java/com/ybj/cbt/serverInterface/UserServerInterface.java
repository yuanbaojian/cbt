package com.ybj.cbt.serverInterface;

import com.ybj.cbt.model.User;

import java.util.List;

public interface UserServerInterface {

/***
 * @Description  登录
 * @param userId
 * @return com.ybj.cbt.model.User
 * @author baojian.yuan
 * @date 2019/10/14
 */
    User login(String userId);

    /***  获取所有的用户
     * @param
     * @return java.util.List<com.ybj.cbt.model.User>
     * @author baojian.yuan
     * @date 2019/11/11
     */
    List<User> getAllUser();

}
