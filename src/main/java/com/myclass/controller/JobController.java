package com.myclass.controller;

import com.myclass.common.StatusCode;
import com.myclass.common.UrlConstant;
import com.myclass.dto.JobDto;
import com.myclass.dto.statistic.StatisticDto;
import com.myclass.service.JobService;
import com.myclass.service.ServiceResponse;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/1/2021
 * Updated: 1/1/2021
 */
@Component
@RequestMapping(value = "/job")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "")
    String index(ModelMap model) {
        model.addAttribute("jobs", jobService.findAll());
        return "/job/index";
    }

    @GetMapping(value = "/add")
    String add() {
        return UrlConstant.URL_JOB_ADD;
    }

    @GetMapping(value = "/edit")
    String edit(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("job",jobService.findById(id));
        return UrlConstant.URL_JOB_EDIT;
    }

    @GetMapping(value = "/delete")
    String delete(@RequestParam("id") int id) {
        jobService.deleteById(id);
        return "redirect:" + UrlConstant.URL_JOB;
    }

    @GetMapping(value = "/info")
    String info(@RequestParam("id") int id, ModelMap model) {
        // Get statistic
        ServiceResponse response = jobService.findJobStatisticById(id);
        StatisticDto statisticDto = (StatisticDto) response.getResObject();
        // Send total statistic
        model.addAttribute("statistic", statisticDto.getStatusStatisticDto());
        // Send task list
        model.addAttribute("todo", statisticDto.getTodoTasks());
        model.addAttribute("inProgress", statisticDto.getInProgressTasks());
        model.addAttribute("done", statisticDto.getDoneTasks());
        return UrlConstant.URL_JOB_INFO;
    }

    /**************** POST METHOD ****************/

    @PostMapping(value = "/add")
    String postAdd(@RequestParam("name") String name,
                   @RequestParam("startDate") String startDate,
                   @RequestParam("endDate") String endDate,
                   ModelMap model) {
        JobDto jobDto = new JobDto(0, name, startDate, endDate);
        ServiceResponse res = jobService.save(jobDto);
        if (res.getStatusCode() == StatusCode.SUCCESS) {
            return "redirect:" + UrlConstant.URL_JOB;
        }
        model.addAttribute("message", res.getResObject());
        return UrlConstant.URL_JOB_ADD;
    }

    @PostMapping(value = "/edit")
    String postEdit(@RequestParam("id") int id,
                    @RequestParam("name") String name,
                    @RequestParam("startDate") String startDate,
                    @RequestParam("endDate") String endDate,
                    ModelMap model) {
        JobDto jobDto = new JobDto(id, name, startDate, endDate);
        ServiceResponse res = jobService.update(jobDto);
        if (res.getStatusCode() == StatusCode.SUCCESS) {
            return "redirect:" + UrlConstant.URL_JOB;
        }
        model.addAttribute("message", res.getResObject());
        model.addAttribute("job",jobService.findById(id));
        return UrlConstant.URL_JOB_EDIT;
    }


}
