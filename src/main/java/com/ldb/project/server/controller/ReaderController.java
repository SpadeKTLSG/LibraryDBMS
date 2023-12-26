package com.ldb.project.server.controller;

import com.ldb.common.utils.poi.ExcelUtil;
import com.ldb.framework.aspectj.lang.annotation.Log;
import com.ldb.framework.aspectj.lang.enums.BusinessType;
import com.ldb.framework.web.controller.BaseController;
import com.ldb.framework.web.domain.AjaxResult;
import com.ldb.framework.web.page.TableDataInfo;
import com.ldb.project.server.domain.Reader;
import com.ldb.project.server.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.ldb.project.server.domain.GlobalField.BOOK_TYPE_LIST;
import static com.ldb.project.server.domain.GlobalField.READER_TYPE_LIST;

/**
 * 读者Controller
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
@RestController
@RequestMapping("/server/Reader")
public class ReaderController extends BaseController {
    @Autowired
    private IReaderService readerService;

    /**
     * 查询读者列表
     */
    @PreAuthorize("@ss.hasPermi('server:Reader:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reader reader) {
        startPage();
        List<Reader> list = readerService.selectReaderList(reader);
        return getDataTable(list);
    }

    /**
     * 导出读者列表
     */
    @PreAuthorize("@ss.hasPermi('server:Reader:export')")
    @Log(title = "读者", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reader reader) {
        List<Reader> list = readerService.selectReaderList(reader);
        ExcelUtil<Reader> util = new ExcelUtil<>(Reader.class);
        util.exportExcel(response, list, "读者数据");
    }

    /**
     * 获取读者详细信息
     */
    @PreAuthorize("@ss.hasPermi('server:Reader:query')")
    @GetMapping(value = "/{cardNumber}")
    public AjaxResult getInfo(@PathVariable("cardNumber") Long cardNumber) {
        return success(readerService.selectReaderByCardNumber(cardNumber));
    }

    /**
     * 新增读者
     */
    @PreAuthorize("@ss.hasPermi('server:Reader:add')")
    @Log(title = "读者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reader reader) {
        return toAjax(readerService.insertReader(reader));
    }

    /**
     * 修改读者
     */
    @PreAuthorize("@ss.hasPermi('server:Reader:edit')")
    @Log(title = "读者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reader reader) {
        return toAjax(readerService.updateReader(reader));
    }

    /**
     * 删除读者
     */
    @PreAuthorize("@ss.hasPermi('server:Reader:remove')")
    @Log(title = "读者", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cardNumbers}")
    public AjaxResult remove(@PathVariable Long[] cardNumbers) {
        return toAjax(readerService.deleteReaderByCardNumbers(cardNumbers));
    }

    /**
     * @Author SpadeKTLSG
     * @description 要获取当前的读者分类列表
     */
    @PreAuthorize("@ss.hasPermi('server:Reader:list')")
    @GetMapping("/getTypeOptions")
    public TableDataInfo getTypeOptions() {
        return getDataTable(READER_TYPE_LIST);
    }
}
