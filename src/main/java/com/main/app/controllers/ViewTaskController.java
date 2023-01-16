package com.main.app.controllers;

import com.main.app.models.Task;
import com.main.app.repo.TaskRepository;
import com.main.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Objects;

@Controller
public class ViewTaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable(value = "id") long id) {
        model.addAttribute("task", taskRepository.findById(id).get());
        return "task/task-view";
    }

    @GetMapping("/edit-task/{id}")
    public String edit(Model model, @PathVariable(value = "id") long id) {
        Task task = taskRepository.findById(id).get();
        if ((Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), task.author)) || (SecurityContextHolder.getContext().getAuthentication().getName().equals("admin"))) {
            model.addAttribute("task", task);
        }else{
            return "redirect:/view/"+id;
        }
        return "task/task-edit";
    }

    @PostMapping("/edit-task/{id}")
    public String postEdit(Model model,
                           @PathVariable(value = "id") long id,
                           @RequestParam String name,
                           @RequestParam String fullText,
                           @RequestParam String price,
                           @RequestParam String depart,
                           @RequestParam String arrival,
                           @RequestParam String observer,
                           @RequestParam String executor){
        Task task = taskRepository.findById(id).get();

        if (userRepository.findByUsername(observer) == null && !(observer.equals(""))) {
            model.addAttribute("error", "Такого менеджера не существует");
            return "task/task-edit";
        }
        if (name.equals("")) {
            model.addAttribute("error", "Вы не ввели название тура");
            return "task/task-edit";
        }
        if (price.equals("")) {
            model.addAttribute("error", "Вы не указали стоимость тура");
            return "task/task-edit";
        }
        if (fullText.equals("")) {
            model.addAttribute("error", "Вы не добавили описание");
            return "task/task-edit";
        }
        if (depart.equals("")) {
            model.addAttribute("error", "Неверно указана дата вылета");
            return "task/task-edit";
        }
        if (arrival.equals("")) {
            model.addAttribute("error", "Неверно указана дата прилета");
            return "task/task-edit";
        }
        task.setName(name);
        task.setFullText(fullText);
        task.setStatus("Изменен "+new Date());
        task.setDepart(java.sql.Date.valueOf(depart));
        task.setArrival(java.sql.Date.valueOf(arrival));
        task.setObserver(observer);
        task.setExecutor(executor);
        task.setPrice(price);
        taskRepository.save(task);
        return "redirect:/view/"+id;
    }

    @GetMapping("/close-wait/{id}")
    public String closeTask(Model model, @PathVariable(value = "id") long id){
        Task task = taskRepository.findById(id).get();
        if ((Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), task.observer)) || (SecurityContextHolder.getContext().getAuthentication().getName().equals("admin"))) {
            task.setStatus("Подтвержден");
            taskRepository.save(task);
            return "redirect:/view/"+id;
        }
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), task.executor)){
            task.setStatus("Ожидает подтверждения");
            taskRepository.save(task);
            return "redirect:/view/"+id;
        }

        task.setStatus("Закрыт автором");
        taskRepository.save(task);
        return "redirect:/view/"+id;

    }

    @GetMapping("/deny-task/{id}")
    public String denyTask(Model model, @PathVariable(value = "id") long id){
        Task task = taskRepository.findById(id).get();
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), task.executor)) {
            task.setStatus("Отклонен");
            taskRepository.save(task);
            return "redirect:/view/"+id;
        }
        return "redirect:/view/"+id;
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(Model model, @PathVariable(value = "id") long id){
        Task task = taskRepository.findById(id).get();
        if ((Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), task.author)) || (SecurityContextHolder.getContext().getAuthentication().getName().equals("admin"))) {
            taskRepository.delete(task);
            return "redirect:/personal";
        }
        return "redirect:/personal";
    }
}
