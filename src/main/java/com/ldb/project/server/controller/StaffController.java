package com.ldb.project.server.controller;

import com.ldb.common.utils.poi.ExcelUtil;
import com.ldb.framework.aspectj.lang.annotation.Log;
import com.ldb.framework.aspectj.lang.enums.BusinessType;
import com.ldb.framework.web.controller.BaseController;
import com.ldb.framework.web.domain.AjaxResult;
import com.ldb.framework.web.page.TableDataInfo;
import com.ldb.project.server.domain.Staff;
import com.ldb.project.server.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 员工Controller
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
@RestController
@RequestMapping("/server/Staff")
public class StaffController extends BaseController {
    @Autowired
    private IStaffService staffService;

    /**
     * 查询员工列表
     */
    @PreAuthorize("@ss.hasPermi('server:Staff:list')")
    @GetMapping("/list")
    public TableDataInfo list(Staff staff) {
        startPage();
        List<Staff> list = staffService.selectStaffList(staff);
        return getDataTable(list);
    }

    /**
     * 导出员工列表
     */
    @PreAuthorize("@ss.hasPermi('server:Staff:export')")
    @Log(title = "员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Staff staff) {
        List<Staff> list = staffService.selectStaffList(staff);
        ExcelUtil<Staff> util = new ExcelUtil<Staff>(Staff.class);
        util.exportExcel(response, list, "员工数据");
    }

    /**
     * 获取员工详细信息
     */
    @PreAuthorize("@ss.hasPermi('server:Staff:query')")
    @GetMapping(value = "/{staffId}")
    public AjaxResult getInfo(@PathVariable("staffId") Long staffId) {
        return success(staffService.selectStaffByStaffId(staffId));
    }

    /**
     * 新增员工
     */
    @PreAuthorize("@ss.hasPermi('server:Staff:add')")
    @Log(title = "员工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Staff staff) {
        return toAjax(staffService.insertStaff(staff));
    }

    /**
     * 修改员工
     */
    @PreAuthorize("@ss.hasPermi('server:Staff:edit')")
    @Log(title = "员工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Staff staff) {
        return toAjax(staffService.updateStaff(staff));
    }

    /**
     * 删除员工
     */
    @PreAuthorize("@ss.hasPermi('server:Staff:remove')")
    @Log(title = "员工", businessType = BusinessType.DELETE)
    @DeleteMapping("/{staffIds}")
    public AjaxResult remove(@PathVariable Long[] staffIds) {
        return toAjax(staffService.deleteStaffByStaffIds(staffIds));
    }
}
