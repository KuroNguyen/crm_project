package com.myclass.controller;

import com.myclass.common.TaskStatusConstant;
import com.myclass.common.UrlConstant;
import com.myclass.dto.TaskDto;
import com.myclass.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/3/2021
 * Updated: 1/3/2021
 */
@Component
@RequestMapping(value = {"/","/home"})
public class HomeController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "")
    String index(ModelMap model) {
        // Get tasks
        List<TaskDto> tasks = taskService.findAll();
        // Get number of tasks by status
        int[] taskCount = new int[3];
        // Get todoTask count
        taskCount[0] = (int) tasks
                .stream()
                .filter(task -> task.getStatusId() == TaskStatusConstant.TODO)
                .count();
        // Get inProgressTask count
        taskCount[1] = (int) tasks
                .stream()
                .filter(task -> task.getStatusId() == TaskStatusConstant.IN_PROGRESS)
                .count();
        // Get doneTask count
        taskCount[2] = (int) tasks
                .stream()
                .filter(task -> task.getStatusId() == TaskStatusConstant.DONE)
                .count();
        model.addAttribute("counts", taskCount);
        return "/home/index";
    }

    @GetMapping(value = UrlConstant.URL_404_ERROR)
    String notFound() {
        return UrlConstant.URL_404_ERROR;
    }

    @GetMapping(value = UrlConstant.URL_403_ERROR)
    String notHavePermission() {
        return UrlConstant.URL_403_ERROR;
    }
}
