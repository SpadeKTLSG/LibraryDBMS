package com.ldb.project.server.service.impl;

import com.ldb.project.server.domain.Bookshelf;
import com.ldb.project.server.mapper.BookshelfMapper;
import com.ldb.project.server.service.IBookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书架Service业务层处理
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
@Service
public class BookshelfServiceImpl implements IBookshelfService {
    @Autowired
    private BookshelfMapper bookshelfMapper;

    /**
     * 查询书架
     *
     * @param bookshelfNumber 书架主键
     * @return 书架
     */
    @Override
    public Bookshelf selectBookshelfByBookshelfNumber(String bookshelfNumber) {
        return bookshelfMapper.selectBookshelfByBookshelfNumber(bookshelfNumber);
    }

    /**
     * 查询书架列表
     *
     * @param bookshelf 书架
     * @return 书架
     */
    @Override
    public List<Bookshelf> selectBookshelfList(Bookshelf bookshelf) {
        return bookshelfMapper.selectBookshelfList(bookshelf);
    }

    /**
     * 新增书架
     *
     * @param bookshelf 书架
     * @return 结果
     */
    @Override
    public int insertBookshelf(Bookshelf bookshelf) {
        return bookshelfMapper.insertBookshelf(bookshelf);
    }

    /**
     * 修改书架
     *
     * @param bookshelf 书架
     * @return 结果
     */
    @Override
    public int updateBookshelf(Bookshelf bookshelf) {
        return bookshelfMapper.updateBookshelf(bookshelf);
    }

    /**
     * 批量删除书架
     *
     * @param bookshelfNumbers 需要删除的书架主键
     * @return 结果
     */
    @Override
    public int deleteBookshelfByBookshelfNumbers(String[] bookshelfNumbers) {
        return bookshelfMapper.deleteBookshelfByBookshelfNumbers(bookshelfNumbers);
    }

    /**
     * 删除书架信息
     *
     * @param bookshelfNumber 书架主键
     * @return 结果
     */
    @Override
    public int deleteBookshelfByBookshelfNumber(String bookshelfNumber) {
        return bookshelfMapper.deleteBookshelfByBookshelfNumber(bookshelfNumber);
    }
}
