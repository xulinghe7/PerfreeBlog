package com.perfree.controller.admin;

import com.perfree.commons.Constants;
import com.perfree.commons.ResponseBean;
import com.perfree.base.BaseController;
import com.perfree.model.Option;
import com.perfree.permission.AdminMenu;
import com.perfree.service.OptionService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiresRoles(value={Constants.ROLE_ADMIN}, logical= Logical.OR)
public class SettingController extends BaseController {

    @Autowired
    private OptionService optionService;

    @GetMapping("/setting")
    @AdminMenu(name = "网站设置", seq = 1, groupId = Constants.ADMIN_MENU_GROUP_SETTING)
    public String index() {
        return view("static/admin/pages/settings/setting.html");
    }


    /**
     * 保存设置信息
     * @param param param
     * @return ResponseBean
     */
    @PostMapping("/setting/save")
    @ResponseBody
    public ResponseBean saveSetting(@RequestBody HashMap<String, String> param) {
        List<Option> options = new ArrayList<>();
        for(Map.Entry<String, String> entry : param.entrySet()){
            Option option = new Option();
            option.setKey(entry.getKey());
            option.setValue(entry.getValue());
            options.add(option);
        }
        if (optionService.addOrUpdateOptions(options) > 0) {
            return ResponseBean.success("保存成功", null);
        }
        return ResponseBean.fail("保存失败", null);
    }
}
