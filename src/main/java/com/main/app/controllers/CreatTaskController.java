package com.main.app.controllers;

import com.main.app.models.Task;
import com.main.app.repo.TaskRepository;
import com.main.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;
import java.util.Calendar;

@Controller
public class CreatTaskController {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/creatTask")
    public String creat() {
        return "task/task-creat";
    }

    @PostMapping("/creatTask")
    public String creatPost(Model model, @RequestParam String name, @RequestParam String fullText, @RequestParam String price, @RequestParam String depart, @RequestParam String arrival, @RequestParam String observer, @RequestParam String executor) {

        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (userRepository.findByUsername(observer) == null && !(observer.equals(""))) {
            model.addAttribute("error", "Такого менеджера не существует");
            return "task/task-creat";
        }
        if (name.equals("")) {
            model.addAttribute("error", "Вы не ввели название тура");
            return "task/task-creat";
        }
        if (price.equals("")) {
            model.addAttribute("error", "Вы не указали стоимость тура");
            return "task/task-creat";
        }
        if (fullText.equals("")) {
            model.addAttribute("error", "Вы не добавили описание");
            return "task/task-creat";
        }
        if (depart.equals("")) {
            model.addAttribute("error", "Вы не ввели дату вылета");
            return "task/task-creat";
        }
        if (arrival.equals("")) {
            model.addAttribute("error", "Вы не ввели дату прилета");
            return "task/task-creat";
        }
        if (executor.equals("")) {
            executor = "Нет клиента";
        }
        Task task = new Task(name, "Открыт", currentUserName, fullText, price, Date.valueOf(depart), Date.valueOf(arrival), executor, observer, new java.sql.Date(Calendar.getInstance().getTime().getTime()), userRepository.findByUsername(currentUserName));
        taskRepository.save(task);
        return "redirect:/personal";
    }
}
