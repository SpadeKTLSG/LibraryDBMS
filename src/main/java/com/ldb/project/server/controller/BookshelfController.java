package com.ldb.project.server.controller;

import com.ldb.common.utils.poi.ExcelUtil;
import com.ldb.framework.aspectj.lang.annotation.Log;
import com.ldb.framework.aspectj.lang.enums.BusinessType;
import com.ldb.framework.web.controller.BaseController;
import com.ldb.framework.web.domain.AjaxResult;
import com.ldb.framework.web.page.TableDataInfo;
import com.ldb.project.server.domain.Bookshelf;
import com.ldb.project.server.service.IBookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 书架Controller
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
@RestController
@RequestMapping("/server/Bookshelf")
public class BookshelfController extends BaseController {
    @Autowired
    private IBookshelfService bookshelfService;

    /**
     * 查询书架列表
     */
    @PreAuthorize("@ss.hasPermi('server:Bookshelf:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bookshelf bookshelf) {
        startPage();
        List<Bookshelf> list = bookshelfService.selectBookshelfList(bookshelf);
        return getDataTable(list);
    }

    /**
     * 导出书架列表
     */
    @PreAuthorize("@ss.hasPermi('server:Bookshelf:export')")
    @Log(title = "书架", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Bookshelf bookshelf) {
        List<Bookshelf> list = bookshelfService.selectBookshelfList(bookshelf);
        ExcelUtil<Bookshelf> util = new ExcelUtil<Bookshelf>(Bookshelf.class);
        util.exportExcel(response, list, "书架数据");
    }

    /**
     * 获取书架详细信息
     */
    @PreAuthorize("@ss.hasPermi('server:Bookshelf:query')")
    @GetMapping(value = "/{bookshelfNumber}")
    public AjaxResult getInfo(@PathVariable("bookshelfNumber") String bookshelfNumber) {
        return success(bookshelfService.selectBookshelfByBookshelfNumber(bookshelfNumber));
    }

    /**
     * 新增书架
     */
    @PreAuthorize("@ss.hasPermi('server:Bookshelf:add')")
    @Log(title = "书架", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bookshelf bookshelf) {
        return toAjax(bookshelfService.insertBookshelf(bookshelf));
    }

    /**
     * 修改书架
     */
    @PreAuthorize("@ss.hasPermi('server:Bookshelf:edit')")
    @Log(title = "书架", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bookshelf bookshelf) {
        return toAjax(bookshelfService.updateBookshelf(bookshelf));
    }

    /**
     * 删除书架
     */
    @PreAuthorize("@ss.hasPermi('server:Bookshelf:remove')")
    @Log(title = "书架", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bookshelfNumbers}")
    public AjaxResult remove(@PathVariable String[] bookshelfNumbers) {
        return toAjax(bookshelfService.deleteBookshelfByBookshelfNumbers(bookshelfNumbers));
    }
}
