package com.ldb.project.server.service;

import com.ldb.project.server.domain.Reader;

import java.util.List;

/**
 * 读者Service接口
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
public interface IReaderService {
    /**
     * 查询读者
     *
     * @param cardNumber 读者主键
     * @return 读者
     */
    public Reader selectReaderByCardNumber(Long cardNumber);

    /**
     * 查询读者列表
     *
     * @param reader 读者
     * @return 读者集合
     */
    public List<Reader> selectReaderList(Reader reader);

    /**
     * 新增读者
     *
     * @param reader 读者
     * @return 结果
     */
    public int insertReader(Reader reader);

    /**
     * 修改读者
     *
     * @param reader 读者
     * @return 结果
     */
    public int updateReader(Reader reader);

    /**
     * 批量删除读者
     *
     * @param cardNumbers 需要删除的读者主键集合
     * @return 结果
     */
    public int deleteReaderByCardNumbers(Long[] cardNumbers);

    /**
     * 删除读者信息
     *
     * @param cardNumber 读者主键
     * @return 结果
     */
    public int deleteReaderByCardNumber(Long cardNumber);
}
