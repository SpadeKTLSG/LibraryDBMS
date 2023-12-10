package com.ldb.project.server.domain;

import com.ldb.common.utils.uuid.UUID;
import com.ldb.framework.aspectj.lang.annotation.Excel;
import com.ldb.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * 书籍对象 book
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {


    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private Long bookId;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String bookName;

    /**
     * 作者
     */
    @Excel(name = "作者")
    private String author;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private String bookType;

    /**
     * 价格
     */
    @Excel(name = "价格")
    private BigDecimal bookPrice;

    /**
     * 出版商
     */
    @Excel(name = "出版商")
    private String publishingHouse;

    /**
     * 概要
     */
    @Excel(name = "概要")
    private String summary;

    /**
     * 书架序号
     */
    @Excel(name = "书架序号")
    private String bookshelfNumber;

    /**
     * 收集总数
     */
    @Excel(name = "收集总数")
    private Long collectionNumber;

    /**
     * 借出数量
     */
    @Excel(name = "借出数量")
    private Long borrowedNumber;

    /**
     * 持有数量
     */
    @Excel(name = "持有数量")
    private Long inLibrariesNumber;

    /**
     * @param book 前端接收的没有ID的book对象
     * @return 完成ID赋值的book对象
     * @Author SpadeKTLSG
     * @description 为book对象赋值bookId属性; 新增书籍需要赋值对应的bookId属性; 控制大小量级: 千万级 {99999999~00000001} 避免万到十万级哈希冲突
     */
    public Book getUUID(Book book) {
        // 将传入的字节数组(书名+作者名)进行MD5加密
        byte[] bookNameBytes = (book.getBookName() + book.getAuthor() + Math.random()).getBytes(StandardCharsets.UTF_8);
        long uuid = (long) UUID.nameUUIDFromBytes(bookNameBytes).hashCode();
        uuid = uuid > 0 ? uuid : -uuid;

        // 如果生成的uuid长度不等于8位, 抛弃位数或者增加位数(补零)来保障万级别的hash冲突, 同时缓解存储展示压力

        if (String.valueOf(uuid).length() > 8) {// 使用length与8比较, 如果大于8, 则截取前8位; 如果小于8, 则补零
            uuid = Long.parseLong(String.valueOf(uuid).substring(0, 8)); // 截取前8位
        } else {
            uuid = Long.parseLong(uuid + "00000000".substring(0, 8 - String.valueOf(uuid).length())); // 补零
        }

        book.setBookId(uuid);
        return book;
    }
}
