package com.ldb.project.server.service;

import com.ldb.project.server.domain.Book;

import java.util.List;

/**
 * 书籍Service接口
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
public interface IBookService {
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
     * 批量删除书籍
     *
     * @param bookIds 需要删除的书籍主键集合
     * @return 结果
     */
    public int deleteBookByBookIds(Long[] bookIds);

    /**
     * 删除书籍信息
     *
     * @param bookId 书籍主键
     * @return 结果
     */
    public int deleteBookByBookId(Long bookId);

}
