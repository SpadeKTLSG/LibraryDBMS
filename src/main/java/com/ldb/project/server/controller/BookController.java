package com.ldb.project.server.controller;

import com.ldb.common.utils.poi.ExcelUtil;
import com.ldb.framework.aspectj.lang.annotation.Log;
import com.ldb.framework.aspectj.lang.enums.BusinessType;
import com.ldb.framework.web.controller.BaseController;
import com.ldb.framework.web.domain.AjaxResult;
import com.ldb.framework.web.page.TableDataInfo;
import com.ldb.project.server.domain.Book;
import com.ldb.project.server.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 书籍Controller
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
@RestController
@RequestMapping("/server/Book")
public class BookController extends BaseController {
    @Autowired
    private IBookService bookService;

    /**
     * 查询书籍列表
     */
    @PreAuthorize("@ss.hasPermi('server:Book:list')")
    @GetMapping("/list")
    public TableDataInfo list(Book book) {
        startPage();
        List<Book> list = bookService.selectBookList(book);
        return getDataTable(list);
    }

    /**
     * 导出书籍列表
     */
    @PreAuthorize("@ss.hasPermi('server:Book:export')")
    @Log(title = "书籍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Book book) {
        List<Book> list = bookService.selectBookList(book);
        ExcelUtil<Book> util = new ExcelUtil<Book>(Book.class);
        util.exportExcel(response, list, "书籍数据");
    }

    /**
     * 获取书籍详细信息
     */
    @PreAuthorize("@ss.hasPermi('server:Book:query')")
    @GetMapping(value = "/{bookId}")
    public AjaxResult getInfo(@PathVariable("bookId") Long bookId) {
        return success(bookService.selectBookByBookId(bookId));
    }

    /**
     * 新增书籍
     */
    @PreAuthorize("@ss.hasPermi('server:Book:add')")
    @Log(title = "书籍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Book book) {
        return toAjax(bookService.insertBook(book));
    }


    /**
     * 修改书籍
     */
    @PreAuthorize("@ss.hasPermi('server:Book:edit')")
    @Log(title = "书籍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Book book) {
        return toAjax(bookService.updateBook(book));
    }

    /**
     * 删除书籍
     */
    @PreAuthorize("@ss.hasPermi('server:Book:remove')")
    @Log(title = "书籍", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bookIds}")
    public AjaxResult remove(@PathVariable Long[] bookIds) {
        return toAjax(bookService.deleteBookByBookIds(bookIds));
    }
}
