package com.ldb.project.server.service;

import com.ldb.project.server.domain.Bookshelf;

import java.util.List;

/**
 * 书架Service接口
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
public interface IBookshelfService {
    /**
     * 查询书架
     *
     * @param bookshelfNumber 书架主键
     * @return 书架
     */
    public Bookshelf selectBookshelfByBookshelfNumber(String bookshelfNumber);

    /**
     * 查询书架列表
     *
     * @param bookshelf 书架
     * @return 书架集合
     */
    public List<Bookshelf> selectBookshelfList(Bookshelf bookshelf);

    /**
     * 新增书架
     *
     * @param bookshelf 书架
     * @return 结果
     */
    public int insertBookshelf(Bookshelf bookshelf);

    /**
     * 修改书架
     *
     * @param bookshelf 书架
     * @return 结果
     */
    public int updateBookshelf(Bookshelf bookshelf);

    /**
     * 批量删除书架
     *
     * @param bookshelfNumbers 需要删除的书架主键集合
     * @return 结果
     */
    public int deleteBookshelfByBookshelfNumbers(String[] bookshelfNumbers);

    /**
     * 删除书架信息
     *
     * @param bookshelfNumber 书架主键
     * @return 结果
     */
    public int deleteBookshelfByBookshelfNumber(String bookshelfNumber);
}
