package com.ldb.project.server.service.impl;

import com.ldb.project.server.domain.Staff;
import com.ldb.project.server.mapper.StaffMapper;
import com.ldb.project.server.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工Service业务层处理
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
@Service
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private StaffMapper staffMapper;

    /**
     * 查询员工
     *
     * @param staffId 员工主键
     * @return 员工
     */
    @Override
    public Staff selectStaffByStaffId(Long staffId) {
        return staffMapper.selectStaffByStaffId(staffId);
    }

    /**
     * 查询员工列表
     *
     * @param staff 员工
     * @return 员工
     */
    @Override
    public List<Staff> selectStaffList(Staff staff) {
        return staffMapper.selectStaffList(staff);
    }

    /**
     * 新增员工
     *
     * @param staff 员工
     * @return 结果
     */
    @Override
    public int insertStaff(Staff staff) {
        staff = staff.getUUID(staff);
        return staffMapper.insertStaff(staff);
    }

    /**
     * 修改员工
     *
     * @param staff 员工
     * @return 结果
     */
    @Override
    public int updateStaff(Staff staff) {
        return staffMapper.updateStaff(staff);
    }

    /**
     * 批量删除员工
     *
     * @param staffIds 需要删除的员工主键
     * @return 结果
     */
    @Override
    public int deleteStaffByStaffIds(Long[] staffIds) {
        return staffMapper.deleteStaffByStaffIds(staffIds);
    }

    /**
     * 删除员工信息
     *
     * @param staffId 员工主键
     * @return 结果
     */
    @Override
    public int deleteStaffByStaffId(Long staffId) {
        return staffMapper.deleteStaffByStaffId(staffId);
    }
}
