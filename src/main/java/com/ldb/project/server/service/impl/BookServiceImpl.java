package com.ldb.project.server.service.impl;

import com.ldb.project.server.domain.Book;
import com.ldb.project.server.mapper.BookMapper;
import com.ldb.project.server.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * 书籍Service业务层处理
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookMapper bookMapper;

    /**
     * 查询书籍
     *
     * @param bookId 书籍主键
     * @return 书籍
     */
    @Override
    public Book selectBookByBookId(Long bookId) {
        return bookMapper.selectBookByBookId(bookId);
    }

    /**
     * 查询书籍列表
     *
     * @param book 书籍
     * @return 书籍
     */
    @Override
    public List<Book> selectBookList(Book book) {
        return bookMapper.selectBookList(book);
    }

    /**
     * 新增书籍
     *
     * @param book 书籍
     * @return 结果
     */
    @Override
    public int insertBook(Book book) {
        book = book.getUUID(book);
        return bookMapper.insertBook(book);
    }

    /**
     * 修改书籍
     *
     * @param book 书籍
     * @return 结果
     */
    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    /**
     * 批量删除书籍
     *
     * @param bookIds 需要删除的书籍主键
     * @return 结果
     */
    @Override
    public int deleteBookByBookIds(Long[] bookIds) {
        return bookMapper.deleteBookByBookIds(bookIds);
    }

    /**
     * 删除书籍信息
     *
     * @param bookId 书籍主键
     * @return 结果
     */
    @Override
    public int deleteBookByBookId(Long bookId) {
        return bookMapper.deleteBookByBookId(bookId);
    }
}
