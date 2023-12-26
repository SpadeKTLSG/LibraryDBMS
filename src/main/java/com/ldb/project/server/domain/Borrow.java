package com.ldb.project.server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ldb.framework.aspectj.lang.annotation.Excel;
import com.ldb.framework.web.domain.BaseEntity;
import lombok.*;

import java.util.Date;

/**
 * 借阅对象 borrow
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrow extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 人卡号
     */
    private Long cardNumber;

    /**
     * 书序号
     */
    private Long bookNumber;

    /**
     * 借出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bookBorrowTime;

    /**
     * 归还时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bookReturnTime;

    /**
     * 是否还书
     */
    @Excel(name = "是否还书")
    private Long isReturn;

    //默认不能主动新增借阅关系,需要从对应Service生成借阅关系
}
