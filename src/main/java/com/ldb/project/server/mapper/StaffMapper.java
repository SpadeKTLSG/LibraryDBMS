package com.ldb.project.server.mapper;

import com.ldb.project.server.domain.Staff;

import java.util.List;

/**
 * 员工Mapper接口
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
public interface StaffMapper {
    /**
     * 查询员工
     *
     * @param staffId 员工主键
     * @return 员工
     */
    public Staff selectStaffByStaffId(Long staffId);

    /**
     * 查询员工列表
     *
     * @param staff 员工
     * @return 员工集合
     */
    public List<Staff> selectStaffList(Staff staff);

    /**
     * 新增员工
     *
     * @param staff 员工
     * @return 结果
     */
    public int insertStaff(Staff staff);

    /**
     * 修改员工
     *
     * @param staff 员工
     * @return 结果
     */
    public int updateStaff(Staff staff);

    /**
     * 删除员工
     *
     * @param staffId 员工主键
     * @return 结果
     */
    public int deleteStaffByStaffId(Long staffId);

    /**
     * 批量删除员工
     *
     * @param staffIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStaffByStaffIds(Long[] staffIds);
}
