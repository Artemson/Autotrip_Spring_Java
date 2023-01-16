package com.main.app.controllers;

import com.main.app.models.Task;
import com.main.app.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PersonalController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/personal")
    public String personal(Model model) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Task> tasks = taskRepository.findAllByObserver(currentUserName);
        List<Task> tasksExec = taskRepository.findAllByExecutor(currentUserName);
        List<Task> tasksAuthor = taskRepository.findAllByAuthor(currentUserName);

        if (tasks.size() == 0) model.addAttribute("error", "Нет ни одной задачи");
        if (tasksExec.size() == 0) model.addAttribute("errorExec", "Нет ни одной задачи");
        if (tasksAuthor.size() == 0) model.addAttribute("errorAuthor", "Нет ни одной задачи");

        model.addAttribute("tasksAuthor", tasksAuthor);
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksExec", tasksExec);

        return "personal-area/home";
    }
}
