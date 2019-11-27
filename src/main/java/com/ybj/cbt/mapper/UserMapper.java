package com.ybj.cbt.mapper;

import com.ybj.cbt.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Long userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
	
	List<User> getAll(Map<String, Object> map, @Param("userType") Integer userType);

	User getUserByLoginName(String loginName);

	User checkUser(User user);

	List<User> getUserExcludeById(@Param("userId") Integer loginUserId);

	//List<User> selectNoRoles(@Param("loginNames") String[] loginNames);

	List<User> searchAuditor(Map<String, Object> map);


	/**
	 * @Title: searchUser
	 * @Description: 查找所有用户-用于转交
	 * @param map
	 * @return List<User>    返回类型
	 * @throws
	 */

	List<User> searchUser(Map<String, Object> map);


	/**
	 * @Title: hasPermission
	 * @Description: 查看用户是否拥有某种权限
	 * @param @param userId
	 * @param @param permissionName    参数
	 * @return void    返回类型
	 * @throws
	 */

	List<User> hasPermission(@Param("userId") Integer userId, @Param("permissionName") String permissionName);

	//查询授权用户信息
	List<User> getGrantByFolder(Long folderId);

	/** 根据培训计划ID  获取它的学生
	 * @param planId
	 * @return
	 */
	 List<User> selectStudentByPlan(@Param(value = "planId") Long planId);

    List<User> selectStudentByGroup(@Param(value = "groupId") Long groupId);

    /***
     * @Description  通过用户名查找用户id列表
     * @param userFullName
     * @return java.util.List<java.lang.Long>
     * @author baojian.yuan
     * @date 2019/9/23
     */
    List<Long> selectUserIdByUserFullName(@Param(value = "userFullName") String userFullName);

    /***
     * @Description  通过组织ID获得直属的用户信息
     * @param orgId
     * @return java.util.List<com.atoz.cbtsys.model.User>
     * @author baojian.yuan
     * @date 2019/9/26
     */
    List<User> selectDirectlyUserByOrgId(Long orgId);


	void updateUserOrgId(@Param(value = "userId") Long userId, @Param(value = "orgId") Long orgId);

	/** orgId 为空
	 *  userType 为 0 或 1
	 *
	 * @return
	 */
    List<User> selectUserListByOrgIdUserType();

    /***
     * @Description 把特定的orgId记录字段置为空
     * @param orgId
     * @return void
     * @author baojian.yuan
     * @date 2019/9/27
     */
    void updateSpecificOrgIdToNull(@Param(value = "orgId") Long orgId);
    /**
     *
     *<p>Title:getAllUserByPlan</p>
     *<p>Description:根据培训计划id找出所有学员 </p>
     * @Param: @param planId
     * @Param: @return
     * @Return: List<User>
     */
    List<User> getAllUserByPlan(@Param("planId") Long planId);

    /***
     * @Description  通过教员ID 查找用户名
     * @param coachId
     * @return java.lang.String
     * @author baojian.yuan
     * @date 2019/10/11
     */
    String selectUserNameByUserId(Long coachId);

    /***  查询所有
     * @param
     * @return void
     * @author baojian.yuan
     * @date 2019/11/11
     */
    List<User> selectAll();
}