package com.nhnacademy.gateway.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/main")
    public String getTest(Model model) {
        // 프로젝트 정보 설정
        String projectTitle = "Dooray 프로젝트";
        model.addAttribute("projectTitle", projectTitle);

        // Task 리스트 설정
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", "Task 1에 대한 설명입니다."));
        tasks.add(new Task("Task 2", "Task 2에 대한 설명입니다."));
        model.addAttribute("tasks", tasks);
        return "main";
    }

    @AllArgsConstructor
    @Data
    // Task 클래스 정의
    public static class Task {
        private String title;
        private String description;
    }
}
