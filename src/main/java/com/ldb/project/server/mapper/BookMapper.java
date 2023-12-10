package com.ldb.project.server.mapper;

import java.util.List;
import com.ldb.project.server.domain.Book;

/**
 * 书籍Mapper接口
 * 
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
public interface BookMapper 
{
    /**
     * 查询书籍
     * 
     * @param bookId 书籍主键
     * @return 书籍
     */
    public Book selectBookByBookId(Long bookId);

    /**
     * 查询书籍列表
     * 
     * @param book 书籍
     * @return 书籍集合
     */
    public List<Book> selectBookList(Book book);

    /**
     * 新增书籍
     * 
     * @param book 书籍
     * @return 结果
     */
    public int insertBook(Book book);

    /**
     * 修改书籍
     * 
     * @param book 书籍
     * @return 结果
     */
    public int updateBook(Book book);

    /**
     * 删除书籍
     * 
     * @param bookId 书籍主键
     * @return 结果
     */
    public int deleteBookByBookId(Long bookId);

    /**
     * 批量删除书籍
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookByBookIds(Long[] bookIds);
}
