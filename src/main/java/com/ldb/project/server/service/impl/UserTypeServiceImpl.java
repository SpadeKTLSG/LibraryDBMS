package com.ldb.project.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ldb.project.server.mapper.UserTypeMapper;
import com.ldb.project.server.domain.UserType;
import com.ldb.project.server.service.IUserTypeService;

/**
 * 用户类型Service业务层处理
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
@Service
public class UserTypeServiceImpl implements IUserTypeService {
    @Autowired
    private UserTypeMapper userTypeMapper;

    /**
     * 查询用户类型
     *
     * @param userType 用户类型主键
     * @return 用户类型
     */
    @Override
    public UserType selectUserTypeByUserType(String userType) {
        return userTypeMapper.selectUserTypeByUserType(userType);
    }

    /**
     * 查询用户类型列表
     *
     * @param userType 用户类型
     * @return 用户类型
     */
    @Override
    public List<UserType> selectUserTypeList(UserType userType) {
        return userTypeMapper.selectUserTypeList(userType);
    }

    /**
     * 新增用户类型
     *
     * @param userType 用户类型
     * @return 结果
     */
    @Override
    public int insertUserType(UserType userType) {
        return userTypeMapper.insertUserType(userType);
    }

    /**
     * 修改用户类型
     *
     * @param userType 用户类型
     * @return 结果
     */
    @Override
    public int updateUserType(UserType userType) {
        return userTypeMapper.updateUserType(userType);
    }

    /**
     * 批量删除用户类型
     *
     * @param userTypes 需要删除的用户类型主键
     * @return 结果
     */
    @Override
    public int deleteUserTypeByUserTypes(String[] userTypes) {
        return userTypeMapper.deleteUserTypeByUserTypes(userTypes);
    }

    /**
     * 删除用户类型信息
     *
     * @param userType 用户类型主键
     * @return 结果
     */
    @Override
    public int deleteUserTypeByUserType(String userType) {
        return userTypeMapper.deleteUserTypeByUserType(userType);
    }
}
