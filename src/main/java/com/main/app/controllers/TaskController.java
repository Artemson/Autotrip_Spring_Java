package com.main.app.controllers;

import com.main.app.models.Task;
import com.main.app.repo.TaskRepository;
import com.main.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/task")
    public String task(Model model) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Task> tasks = taskRepository.findAllByAuthor(currentUser);
        Iterable<Task> tasksObserver = taskRepository.findAllByObserver(currentUser);
        Iterable<Task> tasksExec = taskRepository.findAllByExecutor(currentUser);
        Iterable<Task> tasksAdmin = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksObserver", tasksObserver);
        model.addAttribute("tasksExec", tasksExec);
        model.addAttribute("tasksAdmin", tasksAdmin);
        return "task/task";
    }

    @GetMapping("/task/author")
    public String taskAuthor(Model model) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Task> tasks = taskRepository.findAllByAuthor(currentUser);
        model.addAttribute("tasks", tasks);
        return "task/task-author";
    }

    @GetMapping("/task/executor")
    public String taskExec(Model model) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Task> tasks = taskRepository.findAllByExecutor(currentUser);
        model.addAttribute("tasks", tasks);
        return "task/task-exec";
    }

    @GetMapping("/task/observer")
    public String taskObserver(Model model) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Task> tasks = taskRepository.findAllByObserver(currentUser);
        model.addAttribute("tasks", tasks);
        return "task/task-observ";
    }
}
