package com.ldb.project.server.service.impl;

import com.ldb.project.server.domain.Book;
import com.ldb.project.server.domain.Borrow;
import com.ldb.project.server.domain.Reader;
import com.ldb.project.server.mapper.BorrowMapper;
import com.ldb.project.server.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ReaderServiceImpl readerService;

    @Autowired
    private BookServiceImpl bookService;

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

    /**
     * 查询特定读者借阅
     *
     * @param borrow 借阅
     * @return 借阅集合
     * @Author SpadeK
     */
    @Override
    public List<Borrow> selectReaderBorrowList(Borrow borrow) {
        return borrowMapper.selectReaderBorrowList(borrow);
    }

    /**
     * 新增特定读者借阅 - 借书
     *
     * @param borrow 借阅
     * @return 结果
     * @Author SpadeK
     */
    @Override
    @Transactional
    public int insertReaderBorrow(Borrow borrow) {
        return borrowMapper.insertReaderBorrow(borrow);
    }

    /**
     * 修改特定读者借阅 - 还书
     *
     * @param borrow 借阅
     * @return 结果
     * @Author SpadeK
     */
    @Override
    @Transactional
    public int updateReaderBorrow(Borrow borrow) {


        //?还书时,将isReturn置为1
        borrow.setIsReturn(1L);

        //?修改reader表中borrowing_number(正在借的-1)和borrowed_number(借过的不动)字段

        //找到对象
        Long cardNumber = borrow.getCardNumber();

        //封装修改后的Reader对象更新到updateReader
        Reader originalReader = readerService.selectReaderByCardNumber(cardNumber);

        Reader updatedReader = Reader.builder()
                .cardNumber(originalReader.getCardNumber())
                .cardNumber(originalReader.getCardNumber())
                .readerType(originalReader.getReaderType())
                .sex(originalReader.getSex())
                .borrowingNumber(originalReader.getBorrowingNumber() - 1) //正在借的-1
                .borrowedNumber(originalReader.getBorrowedNumber())
                .build();

        readerService.updateReader(updatedReader);


        //? 将book表中（in_libraries_number）+1 和（borrowed_number）-1

        //找到书籍
        Long bookId = borrow.getBookNumber();

        //封装修改后的Book对象更新到updateReader
        Book originalBook = bookService.selectBookByBookId(bookId);

        Book updatedBook = Book.builder()
                .bookId(originalBook.getBookId())
                .bookName(originalBook.getBookName())
                .author(originalBook.getAuthor())
                .bookType(originalBook.getBookType())
                .bookPrice(originalBook.getBookPrice())
                .publishingHouse(originalBook.getPublishingHouse())
                .summary(originalBook.getSummary())
                .bookshelfNumber(originalBook.getBookshelfNumber())
                .collectionNumber(originalBook.getCollectionNumber())
                .borrowedNumber(originalBook.getBorrowedNumber() - 1)
                .inLibrariesNumber(originalBook.getInLibrariesNumber() + 1)
                .build();

        bookService.updateBook(updatedBook);

        //?.调用updateBook
        return borrowMapper.updateReaderBorrow(borrow);
    }
}
