package com.ldb.project.server.service.impl;

import com.ldb.project.server.domain.Book;
import com.ldb.project.server.domain.Borrow;
import com.ldb.project.server.domain.Reader;
import com.ldb.project.server.mapper.BorrowMapper;
import com.ldb.project.server.service.IBorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ldb.project.server.domain.GlobalField.MAX_BORROW_NUM;


/**
 * 借阅Service业务层处理
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
@Service
@Slf4j
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
        log.info("sadasdasdasdqqqqqqqqqqqqqq");
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
        //TODO 修改Mybatis逻辑, 让双主键生效, 保证一个人能够借多本书
        //FIXME 优化, 如果可能
        //? 鉴权 判定能否借书
        //1. 先查出对应用户
        log.info(borrow.toString());
        Long cardNumber = borrow.getCardNumber();
        Reader reader = readerService.selectReaderByCardNumber(cardNumber);
        if (reader==null){
            log.info("没有这个用户");
            return 0;
        }
        //找到对应用户类型规定的最大借书量
        String Type = reader.getReaderType();
        Integer maxAmount = MAX_BORROW_NUM.get(Type);

        //2. 看看他还能不能再借书: maxAmount>reader.getBorrowingNumber()
        if (maxAmount <= reader.getBorrowingNumber()) {
            //不能借书!
            log.info("你的借书数量已达到上限");
            return 0; //FIXME 优化, 如果可能
        }

        //3. 可以则看看这本书的再馆库存: book.getInLibrariesNumber() > 0
        Long bookId = borrow.getBookNumber();
        Book originalBook = bookService.selectBookByBookId(bookId);
        if (originalBook.getInLibrariesNumber() <= 0) {
            //没有库存了!
            log.info("这本书没库存了");
            return 0; //FIXME 优化, 如果可能
        }


        //1. book表中（in_libraries_number）减1

        //找到对象book
        //封装修改后的Book对象更新到updateReader

        Book updatedBook = Book.builder()
                .bookId(originalBook.getBookId())
                .borrowedNumber(originalBook.getBorrowedNumber() + 1) //借出数量+1
                .inLibrariesNumber(originalBook.getInLibrariesNumber() - 1) //在馆数量-1
                .build();

        bookService.updateBook(updatedBook);


        //2. reader中borrowing_number和borrowed_number字段都+1

        Reader originalReader = readerService.selectReaderByCardNumber(cardNumber);

        Reader updatedReader = Reader.builder()
                .cardNumber(originalReader.getCardNumber())
                .borrowingNumber(originalReader.getBorrowingNumber() + 1)//正在借的+1
                .build();
        readerService.updateReader(updatedReader);
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
        //TODO 修改Mybatis逻辑, 让双主键生效, 保证一个人能够借多本书
        //FIXME 优化, 如果可能

        //?还书时,将isReturn置为1
        borrow.setIsReturn(0L);

        //?修改reader表中borrowing_number(正在借的-1)和borrowed_number(借过的不动)字段

        //找到对象
        Long cardNumber = borrow.getCardNumber();

        //封装修改后的Reader对象更新到updateReader
        Reader originalReader = readerService.selectReaderByCardNumber(cardNumber);

        Reader updatedReader = Reader.builder()
                .cardNumber(originalReader.getCardNumber())
                .borrowedNumber(originalReader.getBorrowedNumber()+1)
                .borrowingNumber(originalReader.getBorrowingNumber() - 1) //正在借的-1
                .build();

        readerService.updateReader(updatedReader);


        //? 将book表中（in_libraries_number）+1 和（borrowed_number）-1

        //找到书籍
        Long bookId = borrow.getBookNumber();

        //封装修改后的Book对象更新到updateReader
        Book originalBook = bookService.selectBookByBookId(bookId);

        Book updatedBook = Book.builder()
                .bookId(originalBook.getBookId())
                .borrowedNumber(originalBook.getBorrowedNumber() - 1)
                .inLibrariesNumber(originalBook.getInLibrariesNumber() + 1)
                .build();

        bookService.updateBook(updatedBook);

        log.info("我走到了这里");
        //?.调用修改借阅关系
        return borrowMapper.updateReaderBorrow(borrow);
    }
}
