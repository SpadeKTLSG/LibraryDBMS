package com.ldb.project.server.domain;

import com.ldb.framework.aspectj.lang.annotation.Excel;
import com.ldb.framework.web.domain.BaseEntity;
import lombok.*;

import java.math.BigDecimal;

import static com.ldb.common.utils.uuid.MyUtils.generateUUID;

/**
 * 员工对象 staff
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 员工号
     */
    private Long staffId;

    /**
     * 员工姓名
     */
    @Excel(name = "员工姓名")
    private String staffName;

    /**
     * 员工科室
     */
    @Excel(name = "员工科室")
    private String staffOffice;

    /**
     * 员工薪资
     */
    @Excel(name = "员工薪资")
    private BigDecimal staffWages;

    /**
     * @param staff 前端接收的没有ID的staff对象
     * @return 完成ID赋值的staff对象
     * @Author SpadeKTLSG
     * @description 新增对象需要赋值对应的主键Id属性
     */
    public Staff getUUID(Staff staff) { //使用读者姓名生成UUID
        staff.setStaffId(generateUUID(staff.getStaffName()));
        return staff;
    }

}
