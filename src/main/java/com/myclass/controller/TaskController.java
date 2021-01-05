package com.myclass.controller;

import com.myclass.common.*;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserLoginDto;
import com.myclass.service.ServiceResponse;
import com.myclass.service.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 12/26/2020
 * Updated: 12/26/2020
 */
@Component
@RequestMapping(value = {"/task"})
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "")
    String index(HttpSession session,ModelMap model) {
        UserLoginDto userLoginDto = (UserLoginDto) session.getAttribute(SessionConstant.USER_LOGIN);
        System.out.println(userLoginDto.toString());
        // ROLE_USER can only see tasks assigned
        switch (userLoginDto.getRoleName()) {
            case RoleConstant.ROLE_USER:
                model.addAttribute("tasks", taskService.findAllByUserId(userLoginDto.getId()));
                break;
            default:
                model.addAttribute("tasks", taskService.findAll());
                break;
        }
        return "/task/index";
    }

    @GetMapping(value = "/add")
    String add(ModelMap model) {
        // Send job list
        model.addAttribute("jobs", taskService.findAllJobs());
        // Send user list
        model.addAttribute("users", taskService.findAllUsers());
        // Send status list
        model.addAttribute("statuses", taskService.findAllStatuses());
        return UrlConstant.URL_TASK_ADD;
    }

    @GetMapping(value = "/edit")
    String edit(@RequestParam("id") int id, ModelMap model) {
        TaskDto taskDto = taskService.findById(id);
        model.addAttribute("task", taskDto);
        // Send user list
        model.addAttribute("users", taskService.findAllUsers());
        // Send job list
        model.addAttribute("jobs", taskService.findAllJobs());
        // Send status list
        model.addAttribute("statuses", taskService.findAllStatuses());
        return UrlConstant.URL_TASK_EDIT;
    }

    @GetMapping(value = "/delete")
    String delete(@RequestParam("id") int id) {
        System.out.println("ID: " + id);
        taskService.deleteById(id);
        return "redirect:" + UrlConstant.URL_TASK;
    }

//    @GetMapping(value = "/info")
//    String info(@RequestParam("id") int id, ModelMap model) {
//        model.addAttribute("user",taskService.findById(id));
//        return UrlConstant.URL_USER_INFO;
//    }

    /**************** POST METHOD ****************/

    @PostMapping(value = "/add")
    String postAdd(@RequestParam("name") String name,
                   @RequestParam("jobId") int jobId,
                   @RequestParam("userId") int userId,
                   @RequestParam("startDate") String startDate,
                   @RequestParam("endDate") String endDate,
                   ModelMap model) {
        System.out.println("Start Date: " + startDate);
        // Crate dto
        TaskDto taskDto = new TaskDto();
        taskDto.setId(0);
        taskDto.setName(name);
        taskDto.setJobId(jobId);
        taskDto.setUserId(userId);
        taskDto.setStatusId(1);
        taskDto.setStartDate(DateUtils.stringToDate(startDate));
        taskDto.setEndDate(DateUtils.stringToDate(endDate));

        // Execute and get response
        ServiceResponse res = taskService.
                save(taskDto);
        if (res.getStatusCode() == StatusCode.SUCCESS) {
            return "redirect:" + UrlConstant.URL_TASK;
        }
        // Process if not success
        // Send error message
        model.addAttribute("message", res.getResObject());
        // Send user list
        model.addAttribute("users", taskService.findAllUsers());
        // Send job list
        model.addAttribute("jobs", taskService.findAllJobs());
        return UrlConstant.URL_TASK_ADD;
    }

    @PostMapping(value = "/edit")
    String postEdit(@RequestParam("id") int id,
                    @RequestParam("name") String name,
                    @RequestParam("jobId") int jobId,
                    @RequestParam("userId") int userId,
                    @RequestParam("statusId") int statusId,
                    @RequestParam("startDate") String startDate,
                    @RequestParam("endDate") String endDate,
                    ModelMap model) {
        // Crate dto
        TaskDto taskDto = new TaskDto();
        taskDto.setId(id);
        taskDto.setName(name);
        taskDto.setJobId(jobId);
        taskDto.setUserId(userId);
        taskDto.setStatusId(statusId);
        taskDto.setStartDate(DateUtils.stringToDate(startDate));
        taskDto.setEndDate(DateUtils.stringToDate(endDate));

        // TODO: Get session role

        // Execute and get response
        ServiceResponse res = taskService.update(taskDto, RoleConstant.ROLE_USER);
        if (res.getStatusCode() == StatusCode.SUCCESS) {
            return "redirect:" + UrlConstant.URL_TASK;
        }
        // Process if not success
        // Send error message
        model.addAttribute("message", res.getResObject());
        // Send task information
        taskDto = taskService.findById(id);
        model.addAttribute("task", taskDto);
        // Send user list
        model.addAttribute("users", taskService.findAllUsers());
        // Send job list
        model.addAttribute("jobs", taskService.findAllJobs());
        // Send status list
        model.addAttribute("statuses", taskService.findAllStatuses());
        return UrlConstant.URL_TASK_EDIT;
    }
}
