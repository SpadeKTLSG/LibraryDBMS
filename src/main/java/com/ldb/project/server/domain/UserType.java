package com.ldb.project.server.domain;

import com.ldb.framework.aspectj.lang.annotation.Excel;
import com.ldb.framework.web.domain.BaseEntity;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户类型对象 user_type
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 最长借阅时间
     */
    @Excel(name = "最长借阅时间")
    private Long canBorrowingTime;

    /**
     * 同时借阅数量
     */
    @Excel(name = "同时借阅数量")
    private Long canBorrowNumber;

    //默认不能主动新增UserType - 预先定义的Type
}
