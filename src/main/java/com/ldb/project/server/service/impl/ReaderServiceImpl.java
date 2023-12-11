package com.ldb.project.server.service.impl;

import com.ldb.project.server.domain.Reader;
import com.ldb.project.server.mapper.ReaderMapper;
import com.ldb.project.server.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 读者Service业务层处理
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
@Service
public class ReaderServiceImpl implements IReaderService {
    @Autowired
    private ReaderMapper readerMapper;

    /**
     * 查询读者
     *
     * @param cardNumber 读者主键
     * @return 读者
     */
    @Override
    public Reader selectReaderByCardNumber(Long cardNumber) {
        return readerMapper.selectReaderByCardNumber(cardNumber);
    }

    /**
     * 查询读者列表
     *
     * @param reader 读者
     * @return 读者
     */
    @Override
    public List<Reader> selectReaderList(Reader reader) {
        return readerMapper.selectReaderList(reader);
    }

    /**
     * 新增读者
     *
     * @param reader 读者
     * @return 结果
     */
    @Override
    public int insertReader(Reader reader) {
        reader = reader.getUUID(reader);
        return readerMapper.insertReader(reader);
    }

    /**
     * 修改读者
     *
     * @param reader 读者
     * @return 结果
     */
    @Override
    public int updateReader(Reader reader) {
        return readerMapper.updateReader(reader);
    }

    /**
     * 批量删除读者
     *
     * @param cardNumbers 需要删除的读者主键
     * @return 结果
     */
    @Override
    public int deleteReaderByCardNumbers(Long[] cardNumbers) {
        return readerMapper.deleteReaderByCardNumbers(cardNumbers);
    }

    /**
     * 删除读者信息
     *
     * @param cardNumber 读者主键
     * @return 结果
     */
    @Override
    public int deleteReaderByCardNumber(Long cardNumber) {
        return readerMapper.deleteReaderByCardNumber(cardNumber);
    }
}
