package com.ldb.project.server.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ldb.framework.aspectj.lang.annotation.Log;
import com.ldb.framework.aspectj.lang.enums.BusinessType;
import com.ldb.project.server.domain.UserType;
import com.ldb.project.server.service.IUserTypeService;
import com.ldb.framework.web.controller.BaseController;
import com.ldb.framework.web.domain.AjaxResult;
import com.ldb.common.utils.poi.ExcelUtil;
import com.ldb.framework.web.page.TableDataInfo;

/**
 * 用户类型Controller
 *
 * @author SpadeKTLSG
 * @date 2023-12-11
 */
@RestController
@RequestMapping("/server/UserType")
public class UserTypeController extends BaseController {
    @Autowired
    private IUserTypeService userTypeService;

    /**
     * 查询用户类型列表
     */
    @PreAuthorize("@ss.hasPermi('server:UserType:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserType userType) {
        startPage();
        List<UserType> list = userTypeService.selectUserTypeList(userType);
        return getDataTable(list);
    }

    /**
     * 导出用户类型列表
     */
    @PreAuthorize("@ss.hasPermi('server:UserType:export')")
    @Log(title = "用户类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserType userType) {
        List<UserType> list = userTypeService.selectUserTypeList(userType);
        ExcelUtil<UserType> util = new ExcelUtil<UserType>(UserType.class);
        util.exportExcel(response, list, "用户类型数据");
    }

    /**
     * 获取用户类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('server:UserType:query')")
    @GetMapping(value = "/{userType}")
    public AjaxResult getInfo(@PathVariable("userType") String userType) {
        return success(userTypeService.selectUserTypeByUserType(userType));
    }

    /**
     * 新增用户类型
     * !弃用
     */
    @PreAuthorize("@ss.hasPermi('server:UserType:add')")
    @Log(title = "用户类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserType userType) {
        return toAjax(userTypeService.insertUserType(userType));
    }

    /**
     * 修改用户类型
     */
    @PreAuthorize("@ss.hasPermi('server:UserType:edit')")
    @Log(title = "用户类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserType userType) {
        return toAjax(userTypeService.updateUserType(userType));
    }

    /**
     * 删除用户类型
     */
    @PreAuthorize("@ss.hasPermi('server:UserType:remove')")
    @Log(title = "用户类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userTypes}")
    public AjaxResult remove(@PathVariable String[] userTypes) {
        return toAjax(userTypeService.deleteUserTypeByUserTypes(userTypes));
    }
}
