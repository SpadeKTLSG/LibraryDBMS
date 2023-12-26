package com.ldb.project.server.mapper;

import com.ldb.project.server.domain.Borrow;

import java.util.List;

/**
 * 借阅Mapper接口
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
public interface BorrowMapper {
    /**
     * 查询借阅
     *
     * @param cardNumber 借阅主键
     * @return 借阅
     */
    public Borrow selectBorrowByCardNumber(Long cardNumber);

    /**
     * 查询借阅列表
     *
     * @param borrow 借阅
     * @return 借阅集合
     */
    public List<Borrow> selectBorrowList(Borrow borrow);

    /**
     * 新增借阅
     *
     * @param borrow 借阅
     * @return 结果
     */
    public int insertBorrow(Borrow borrow);

    /**
     * 修改借阅
     *
     * @param borrow 借阅
     * @return 结果
     */
    public int updateBorrow(Borrow borrow);

    /**
     * 删除借阅
     *
     * @param cardNumber 借阅主键
     * @return 结果
     */
    public int deleteBorrowByCardNumber(Long cardNumber);

    /**
     * 批量删除借阅
     *
     * @param cardNumbers 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBorrowByCardNumbers(Long[] cardNumbers);

    /**
     * 查询特定读者借阅列表
     *
     * @param borrow 借阅
     * @return 借阅集合
     */
    List<Borrow> selectReaderBorrowList(Borrow borrow);
}
