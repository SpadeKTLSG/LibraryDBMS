package com.ldb.project.server.domain;

import com.ldb.framework.aspectj.lang.annotation.Excel;
import com.ldb.framework.web.domain.BaseEntity;
import lombok.*;

import java.math.BigDecimal;

import static com.ldb.common.utils.uuid.MyUtils.generateUUID;


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
@Builder
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
     * @description 新增对象需要赋值对应的主键Id属性
     */
    public Book getUUID(Book book) { //使用Name和Author生成UUID
        book.setBookId(generateUUID(book.getBookName() + book.getAuthor()));
        return book;
    }
}
