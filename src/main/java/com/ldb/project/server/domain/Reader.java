package com.ldb.project.server.domain;

import com.ldb.framework.aspectj.lang.annotation.Excel;
import com.ldb.framework.web.domain.BaseEntity;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static com.ldb.common.utils.uuid.MyUtils.generateUUID;

/**
 * 读者对象 reader
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reader extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 卡号
     */
    private Long cardNumber;

    /**
     * 读者姓名
     */
    @Excel(name = "读者姓名")
    private String readerName;

    /**
     * 读者类型
     */
    @Excel(name = "读者类型")
    private String readerType;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;

    /**
     * 借阅中数量
     */
    @Excel(name = "借阅中数量")
    private Long borrowingNumber;

    /**
     * 已归还数量
     */
    @Excel(name = "已归还数量")
    private Long borrowedNumber;

    /**
     * @param reader 前端接收的没有ID的reader对象
     * @return 完成ID赋值的reader对象
     * @Author SpadeKTLSG
     * @description 新增对象需要赋值对应的主键Id属性
     */
    public Reader getUUID(Reader reader) { //使用读者姓名生成UUID
        reader.setCardNumber(generateUUID(reader.getReaderName()));
        return reader;
    }


}
