package com.myclass.controller;

import com.myclass.common.StatusCode;
import com.myclass.common.UrlConstant;
import com.myclass.dto.UserDto;
import com.myclass.dto.statistic.StatisticDto;
import com.myclass.service.RoleService;
import com.myclass.service.ServiceResponse;
import com.myclass.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/25/2020
 * Updated: 12/25/2020
 */
@Component
@RequestMapping(value = {"/user"})
public class UserController {

    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    String index(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "/user/index";
    }

    @GetMapping(value = "/add")
    String add(ModelMap model) {
        model.addAttribute("roles", roleService.findAll());
        return UrlConstant.URL_USER_ADD;
    }

    @GetMapping(value = "/edit")
    String edit(@RequestParam("id") int id, ModelMap model) {
        UserDto userDto = userService.findById(id);
        model.addAttribute("user", userDto);
        model.addAttribute("roles", roleService.findAll());
        return UrlConstant.URL_USER_EDIT;
    }

    @GetMapping(value = "/delete")
    String delete(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:" + UrlConstant.URL_USER;
    }

    @GetMapping(value = "/info")
    String info(@RequestParam("id") int id, ModelMap model) {
        // Get statistic
        ServiceResponse response = userService.findUserStatisticById(id);
        StatisticDto statisticDto = (StatisticDto) response.getResObject();
        // Send total statistic
        model.addAttribute("statistic", statisticDto.getStatusStatisticDto());
        // Send task list
        model.addAttribute("todo", statisticDto.getTodoTasks());
        model.addAttribute("inProgress", statisticDto.getInProgressTasks());
        model.addAttribute("done", statisticDto.getDoneTasks());
        // Send userDto
        model.addAttribute("user",userService.findById(id));
        return UrlConstant.URL_USER_INFO;
    }

    /**************** POST METHOD ****************/

    @PostMapping(value = "/add")
    String postAdd(@RequestParam("email") String email,
               @RequestParam("password") String password,
               @RequestParam("fullName") String fullName,
               @RequestParam("avatar") String avatar,
               @RequestParam("roleId") int roleId,
               ModelMap model) {
        UserDto userDto = new UserDto(email,password,fullName,avatar,roleId);
        ServiceResponse res = userService.save(userDto);
        if (res.getStatusCode() == StatusCode.SUCCESS) {
            return "redirect:" + UrlConstant.URL_USER;
        }
        model.addAttribute("message", res.getResObject());
        return UrlConstant.URL_USER_ADD;
    }

    @PostMapping(value = "/edit")
    String postEdit(@RequestParam("id") int id,
                    @RequestParam("email") String email,
                    @RequestParam("password") String password,
                    @RequestParam("fullName") String fullName,
                    @RequestParam("avatar") String avatar,
                    @RequestParam("roleId") int roleId,
                    ModelMap model) {
        UserDto userDto = new UserDto(id,email,password,fullName,avatar,roleId);
        ServiceResponse res = userService.update(userDto);
        if (res.getStatusCode() == StatusCode.SUCCESS) {
            return "redirect:" + UrlConstant.URL_USER;
        }
        model.addAttribute("message", res.getResObject());
        return UrlConstant.URL_USER_EDIT;
    }
}
