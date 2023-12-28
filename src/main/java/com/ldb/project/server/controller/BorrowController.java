package com.ldb.project.server.controller;

import com.ldb.common.utils.poi.ExcelUtil;
import com.ldb.framework.aspectj.lang.annotation.Log;
import com.ldb.framework.aspectj.lang.enums.BusinessType;
import com.ldb.framework.web.controller.BaseController;
import com.ldb.framework.web.domain.AjaxResult;
import com.ldb.framework.web.page.TableDataInfo;
import com.ldb.project.server.domain.Borrow;
import com.ldb.project.server.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 借阅Controller
 *
 * @author SpadeKTLSG
 * @date 2023-12-10
 */
@RestController
@RequestMapping("/server/Borrow")
public class BorrowController extends BaseController {
    @Autowired
    private IBorrowService borrowService;

    /**
     * 查询借阅列表
     */
    @PreAuthorize("@ss.hasPermi('server:Borrow:list')")
    @GetMapping("/list")
    public TableDataInfo list(Borrow borrow) {
        startPage();
        List<Borrow> list = borrowService.selectBorrowList(borrow);
        return getDataTable(list);
    }

    /**
     * 导出借阅列表
     */
    @PreAuthorize("@ss.hasPermi('server:Borrow:export')")
    @Log(title = "借阅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Borrow borrow) {
        List<Borrow> list = borrowService.selectBorrowList(borrow);
        ExcelUtil<Borrow> util = new ExcelUtil<Borrow>(Borrow.class);
        util.exportExcel(response, list, "借阅数据");
    }

    /**
     * 获取借阅详细信息
     */
    @PreAuthorize("@ss.hasPermi('server:Borrow:query')")
    @GetMapping(value = "/{cardNumber}")
    public AjaxResult getInfo(@PathVariable("cardNumber") Long cardNumber) {
        return success(borrowService.selectBorrowByCardNumber(cardNumber));
    }

//    /**
//     * 新增借阅
//     * !弃用
//     */
//    @PreAuthorize("@ss.hasPermi('server:Borrow:add')")
//    @Log(title = "借阅", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody Borrow borrow) {
//        return toAjax(borrowService.insertBorrow(borrow));
//    }

//    /**
//     * 修改借阅
//     */
//    @PreAuthorize("@ss.hasPermi('server:Borrow:edit')")
//    @Log(title = "借阅", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody Borrow borrow) {
//        logger.info("asdsadasd888888888");
//        return toAjax(borrowService.updateBorrow(borrow));
//    }


    /**
     * 新增借阅
     *
     * @Author SpadeK
     */
    @PreAuthorize("@ss.hasPermi('server:Borrow:add')")
    @Log(title = "借阅", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addBorrow(@RequestBody Borrow borrow) {
        logger.info("我新增了");
        return toAjax(borrowService.insertReaderBorrow(borrow));
    }

    /**
     * 删除借阅
     */
    @PreAuthorize("@ss.hasPermi('server:Borrow:remove')")
    @Log(title = "借阅", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cardNumbers}")
    public AjaxResult remove(@PathVariable Long[] cardNumbers) {
        return toAjax(borrowService.deleteBorrowByCardNumbers(cardNumbers));
    }

    /**
     * 查询借阅列表
     *
     * @Author SpadeK
     */
    @PreAuthorize("@ss.hasPermi('server:Borrow:list')")
    @GetMapping("/listReader")
    public TableDataInfo listReader(Borrow borrow) {
        startPage();
        List<Borrow> list = borrowService.selectReaderBorrowList(borrow);
        return getDataTable(list);
    }


    //! 借书 - 生成借阅关系 add



    //! 还书 - 修改借阅关系 edit

    /**
     * 修改借阅
     *
     * @Author SpadeK
     */
    @PreAuthorize("@ss.hasPermi('server:Borrow:edit')")
    @Log(title = "借阅", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateBorrow(@RequestBody Borrow borrow) {
        logger.info("我进来了{}",borrow.toString());
        return toAjax(borrowService.updateReaderBorrow(borrow));
    }
}
