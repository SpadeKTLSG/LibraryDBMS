package com.ldb.project.server.service.impl;

import com.ldb.project.server.domain.Borrow;
import com.ldb.project.server.mapper.BorrowMapper;
import com.ldb.project.server.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 借阅Service业务层处理
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
@Service
public class BorrowServiceImpl implements IBorrowService {
    @Autowired
    private BorrowMapper borrowMapper;

    /**
     * 查询借阅
     *
     * @param cardNumber 借阅主键
     * @return 借阅
     */
    @Override
    public Borrow selectBorrowByCardNumber(Long cardNumber) {
        return borrowMapper.selectBorrowByCardNumber(cardNumber);
    }

    /**
     * 查询借阅列表
     *
     * @param borrow 借阅
     * @return 借阅
     */
    @Override
    public List<Borrow> selectBorrowList(Borrow borrow) {
        return borrowMapper.selectBorrowList(borrow);
    }

    /**
     * 新增借阅
     *
     * @param borrow 借阅
     * @return 结果
     */
    @Override
    public int insertBorrow(Borrow borrow) {
        return borrowMapper.insertBorrow(borrow);
    }

    /**
     * 修改借阅
     *
     * @param borrow 借阅
     * @return 结果
     */
    @Override
    public int updateBorrow(Borrow borrow) {
        return borrowMapper.updateBorrow(borrow);
    }

    /**
     * 批量删除借阅
     *
     * @param cardNumbers 需要删除的借阅主键
     * @return 结果
     */
    @Override
    public int deleteBorrowByCardNumbers(Long[] cardNumbers) {
        return borrowMapper.deleteBorrowByCardNumbers(cardNumbers);
    }

    /**
     * 删除借阅信息
     *
     * @param cardNumber 借阅主键
     * @return 结果
     */
    @Override
    public int deleteBorrowByCardNumber(Long cardNumber) {
        return borrowMapper.deleteBorrowByCardNumber(cardNumber);
    }
}
