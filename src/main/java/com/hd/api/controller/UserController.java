package com.hd.api.controller;

import com.hd.app.param.PersonParam;
import com.hd.api.dto.DictDTO;
import com.hd.api.dto.UserFullInfoDTO;
import com.hd.api.model.Userinfo;
import com.hd.api.service.DictService;
import com.hd.api.service.LoginUserService;
import com.hd.api.service.UserInfoService;
import com.hd.app.security.IgnoreSecurity;
import com.hd.app.bean.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 *
 * @author hoda
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private LoginUserService userService;

    @Autowired
    private UserInfoService uiService;

    @Autowired
    private DictService diService;

    /**
     * 注册个人
     *
     * @param param
     * @return
     */
    @IgnoreSecurity
    @RequestMapping(method = RequestMethod.POST)
    public Response signUpPerson(@Valid PersonParam param) {
        userService.signUpPerson(param.getInviteCode(), param.getUserName(),
                param.getPhone(), param.getVerifyCode(), param.getPassWord());
        return new Response().success();
    }

    /**
     * 重置密码
     *
     * @param phone
     * @return
     */
    @IgnoreSecurity
    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public Response updatePwd(
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "passWord") String passWord,
            @RequestParam(value = "verifyCode") String verifyCode) {
        userService.forgetPwd(phone, passWord, verifyCode);
        return new Response().success();
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Response listUserInfo() {
        String userId = getUserId();

        Map<String, Object> dataMap = new HashMap<String, Object>();

        List<UserFullInfoDTO> list = uiService.getUserListByUserId(userId);
        dataMap.put("message", list);

        List<Map<String, String>> level = new LinkedList<Map<String, String>>();
        List<DictDTO> levelList = diService.getByClassCode("Level");
        levelList.forEach(l -> {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", l.getValue());
            map.put("id", l.getCode());
            level.add(map);
        });
        dataMap.put("level", level);

        return new Response().success(dataMap);
    }

    /**
     * 用户删除
     *
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public Response deleteUserInfo(@PathVariable("userId") String userId) {
        if (uiService.delete(userId)) {
            return new Response().success();
        } else {
            return new Response().failure();
        }
    }

    /**
     * 获取个人详细信息
     *
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Response getUserInfo(@PathVariable("userId") String userId) {
        UserFullInfoDTO ui = uiService.getUserFullInfoByUserId(userId);
        return new Response().success(ui);
    }

    /**
     * 修改个人详细信息
     *
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public Response updateUserInfo(@PathVariable("userId") String userId,
                                   @RequestParam(value = "userName", required = false) String userName,
                                   @RequestParam(value = "gender", required = false) String gender,
                                   @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "city", required = false) String city,
                                   @RequestParam(value = "location", required = false) String location,
                                   @RequestParam(value = "school", required = false) String school,
                                   @RequestParam(value = "interest", required = false) String interest) {
        Userinfo ui = new Userinfo();
        ui.setUserid(userId);
        ui.setUsername(userName);
        ui.setGender(gender);
        ui.setEmail(email);
        ui.setCity(city);
        ui.setLocation(location);
        ui.setSchool(school);
        ui.setInterest(interest);
        uiService.update(ui);
        return new Response().success();
    }

}
