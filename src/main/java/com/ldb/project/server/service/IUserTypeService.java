package com.ldb.project.server.service;

import com.ldb.project.server.domain.UserType;

import java.util.List;

/**
 * 用户类型Service接口
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
public interface IUserTypeService {
    /**
     * 查询用户类型
     *
     * @param userType 用户类型主键
     * @return 用户类型
     */
    public UserType selectUserTypeByUserType(String userType);

    /**
     * 查询用户类型列表
     *
     * @param userType 用户类型
     * @return 用户类型集合
     */
    public List<UserType> selectUserTypeList(UserType userType);

    /**
     * 新增用户类型
     *
     * @param userType 用户类型
     * @return 结果
     */
    public int insertUserType(UserType userType);

    /**
     * 修改用户类型
     *
     * @param userType 用户类型
     * @return 结果
     */
    public int updateUserType(UserType userType);

    /**
     * 批量删除用户类型
     *
     * @param userTypes 需要删除的用户类型主键集合
     * @return 结果
     */
    public int deleteUserTypeByUserTypes(String[] userTypes);

    /**
     * 删除用户类型信息
     *
     * @param userType 用户类型主键
     * @return 结果
     */
    public int deleteUserTypeByUserType(String userType);
}
