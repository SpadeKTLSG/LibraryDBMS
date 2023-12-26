package com.ldb.project.server.domain;


import com.ldb.framework.aspectj.lang.annotation.Excel;
import com.ldb.framework.web.domain.BaseEntity;
import lombok.*;

import static com.ldb.common.utils.uuid.MyUtils.generateUUID;


/**
 * 书架对象 bookshelf
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookshelf extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 书架序号
     */
    private String bookshelfNumber;

    /**
     * 书架类型
     */
    @Excel(name = "书架类型")
    private String bookshelfType;

    /**
     * @param bookshelf 前端接收的没有ID的bookshelf对象
     * @return 完成ID赋值的bookshelf对象
     * @Author SpadeKTLSG
     * @description 新增对象需要赋值对应的主键Id属性
     */
    public Bookshelf getUUID(Bookshelf bookshelf) { //使用Type生成UUID
        bookshelf.setBookshelfNumber(String.valueOf(generateUUID(bookshelf.getBookshelfType())));
        return bookshelf;
    }


}
