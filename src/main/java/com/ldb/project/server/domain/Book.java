package com.ldb.project.server.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ldb.framework.aspectj.lang.annotation.Excel;
import com.ldb.framework.web.domain.BaseEntity;

/**
 * 书籍对象 book
 * 
 * @author SpadeKTLSG
 * @date 2023-12-09
 */
public class Book extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号")
    private Long bookId;

    /** 名称 */
    @Excel(name = "名称")
    private String bookName;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 类型 */
    @Excel(name = "类型")
    private String bookType;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal bookPrice;

    /** 出版商 */
    @Excel(name = "出版商")
    private String publishingHouse;

    /** 概要 */
    @Excel(name = "概要")
    private String summary;

    /** 书架序号 */
    @Excel(name = "书架序号")
    private String bookshelfNumber;

    /** 收集总数 */
    @Excel(name = "收集总数")
    private Long collectionNumber;

    /** 借出数量 */
    @Excel(name = "借出数量")
    private String borrowedNumber;

    /** 持有数量 */
    @Excel(name = "持有数量")
    private String inLibrariesNumber;

    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }
    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookName() 
    {
        return bookName;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setBookType(String bookType) 
    {
        this.bookType = bookType;
    }

    public String getBookType() 
    {
        return bookType;
    }
    public void setBookPrice(BigDecimal bookPrice) 
    {
        this.bookPrice = bookPrice;
    }

    public BigDecimal getBookPrice() 
    {
        return bookPrice;
    }
    public void setPublishingHouse(String publishingHouse) 
    {
        this.publishingHouse = publishingHouse;
    }

    public String getPublishingHouse() 
    {
        return publishingHouse;
    }
    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }
    public void setBookshelfNumber(String bookshelfNumber) 
    {
        this.bookshelfNumber = bookshelfNumber;
    }

    public String getBookshelfNumber() 
    {
        return bookshelfNumber;
    }
    public void setCollectionNumber(Long collectionNumber) 
    {
        this.collectionNumber = collectionNumber;
    }

    public Long getCollectionNumber() 
    {
        return collectionNumber;
    }
    public void setBorrowedNumber(String borrowedNumber) 
    {
        this.borrowedNumber = borrowedNumber;
    }

    public String getBorrowedNumber() 
    {
        return borrowedNumber;
    }
    public void setInLibrariesNumber(String inLibrariesNumber) 
    {
        this.inLibrariesNumber = inLibrariesNumber;
    }

    public String getInLibrariesNumber() 
    {
        return inLibrariesNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookId", getBookId())
            .append("bookName", getBookName())
            .append("author", getAuthor())
            .append("bookType", getBookType())
            .append("bookPrice", getBookPrice())
            .append("publishingHouse", getPublishingHouse())
            .append("summary", getSummary())
            .append("bookshelfNumber", getBookshelfNumber())
            .append("collectionNumber", getCollectionNumber())
            .append("borrowedNumber", getBorrowedNumber())
            .append("inLibrariesNumber", getInLibrariesNumber())
            .toString();
    }
}
