package ru.javarush.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javarush.entity.Task;
import ru.javarush.service.TaskService;

import java.util.List;

import static java.util.Objects.isNull;

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
        List<Task> tasks = taskService.getAll((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/{id}")
    public void edit(Model model,
                     @PathVariable Integer id,
                     @RequestBody TaskInfo info){
        if (isNull(id) || id <= 0){
            throw new RuntimeException("invalid id");
        }
        Task task = taskService.edit(id, info.getDescription(), info.getStatus());
    }

    @PostMapping("/")
    public void add(Model model,
                     @RequestBody TaskInfo info){
        Task task = taskService.create(info.getDescription(), info.getStatus());
    }
    @DeleteMapping ("/")
    public String delete(Model model,
                    @PathVariable Integer id){
        taskService.delete(id);
        return "tasks";
    }
}