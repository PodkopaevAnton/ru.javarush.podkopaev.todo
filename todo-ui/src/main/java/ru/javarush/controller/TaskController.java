package ru.javarush.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javarush.service.TaskService;

@Controller
@RequestMapping("/")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String tasks(Model model,
                        @RequestParam(value = "page",required = false,defaultValue = "1") int page,
                        @RequestParam(value = "limit", required = false,defaultValue = "1") int limit
    ){
        return "tasks";
    }

    @PostMapping("/{id}")
    public void edit(Model model,
                     @PathVariable Integer id,
                     @RequestBody TaskInfo info){

    }
}