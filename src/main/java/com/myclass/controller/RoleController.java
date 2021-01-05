package com.myclass.controller;

import com.myclass.common.StatusCode;
import com.myclass.common.UrlConstant;
import com.myclass.dto.RoleDto;
import com.myclass.service.RoleService;
import com.myclass.service.ServiceResponse;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/26/2020
 * Updated: 12/26/2020
 */
@Component
@RequestMapping(value = {"/role"})
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    String index(ModelMap model) {
        model.addAttribute("roles", roleService.findAll());
        return "/role/index";
    }

    @GetMapping(value = "/add")
    String add(ModelMap model) {
        return UrlConstant.URL_ROLE_ADD;
    }

    @GetMapping(value = "/edit")
    String edit(@RequestParam("id") int id, ModelMap model) {
        RoleDto roleDto = roleService.findById(id);
        model.addAttribute("role", roleDto);
        return UrlConstant.URL_ROLE_EDIT;
    }

    @GetMapping(value = "/delete")
    String delete(@RequestParam("id") int id) {
        roleService.deleteById(id);
        return "redirect:" + UrlConstant.URL_ROLE;
    }

    /**************** POST METHOD ****************/

    @PostMapping(value = "/add")
    String postAdd(@RequestParam("name") String name,
                   @RequestParam("description") String description,
                   ModelMap model) {
        RoleDto roleDto = new RoleDto(0, name, description);
        ServiceResponse res = roleService.save(roleDto);
        if (res.getStatusCode() == StatusCode.SUCCESS) {
            return "redirect:" + UrlConstant.URL_ROLE;
        }
        model.addAttribute("message", res.getResObject());
        return UrlConstant.URL_ROLE_ADD;
    }

    @PostMapping(value = "/edit")
    String postEdit(@RequestParam("id") int id,
                    @RequestParam("name") String name,
                    @RequestParam("description") String description,
                    ModelMap model) {
        RoleDto roleDto = new RoleDto(id, name, description);
        ServiceResponse res = roleService.update(roleDto);
        if (res.getStatusCode() == StatusCode.SUCCESS) {
            return "redirect:" + UrlConstant.URL_ROLE;
        }
        model.addAttribute("message", res.getResObject());
        return UrlConstant.URL_ROLE_EDIT;
    }
}
