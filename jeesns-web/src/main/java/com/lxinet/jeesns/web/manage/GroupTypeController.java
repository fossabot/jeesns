package com.lxinet.jeesns.web.manage;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.group.GroupType;
import com.lxinet.jeesns.service.group.IGroupTypeService;
import com.lxinet.jeesns.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午1:17
 */
@Controller("manageGroupTypeController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class GroupTypeController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/group/type/";
    @Resource
    private IGroupTypeService groupTypeService;

    @RequestMapping(value = "${managePath}/group/type/list")
    public String index(Model model) {
        List<GroupType> list = groupTypeService.list();
        model.addAttribute("list", list);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping(value = "${managePath}/group/type/add", method = RequestMethod.GET)
    public Object add() {
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping(value = "${managePath}/group/type/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(GroupType groupType) {
        ResponseModel responseModel = groupTypeService.save(groupType);
        if (responseModel.getCode() == 0) {
            responseModel.setCode(3);
        }
        return responseModel;
    }


    @RequestMapping(value = "${managePath}/group/type/edit/{id}", method = RequestMethod.GET)
    public Object edit(@PathVariable("id") int id, Model model) {
        GroupType groupType = groupTypeService.findById(id);
        model.addAttribute("groupType", groupType);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping(value = "${managePath}/group/type/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(GroupType groupType) {
        ResponseModel responseModel = groupTypeService.update(groupType);
        if (responseModel.getCode() == 0) {
            responseModel.setCode(3);
        }
        return responseModel;
    }


    @RequestMapping(value = "${managePath}/group/type/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id) {
        if (id == 1){
            return new ResponseModel(-1,"系统默认分类无法删除");
        }
        ResponseModel responseModel = groupTypeService.delete(id);
        if (responseModel.getCode() == 0) {
            responseModel.setCode(1);
        }
        return responseModel;
    }


}