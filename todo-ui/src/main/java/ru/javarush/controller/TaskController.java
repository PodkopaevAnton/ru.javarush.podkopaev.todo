package ru.javarush.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javarush.entity.Task;
import ru.javarush.service.TaskService;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/")
    public String tasks(Model model,
                        @RequestParam(value = "page",required = false,defaultValue = "1") int page,
                        @RequestParam(value = "limit", required = false,defaultValue = "10") int limit
    ){
        List<Task> tasks = taskService.getAll((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/{id}")
    public String edit(Model model,
                     @PathVariable Integer id,
                     @RequestBody TaskInfo info){
        if (isNull(id) || id <= 0){
            throw new RuntimeException("invalid id");
        }
        Task task = taskService.edit(id, info.getDescription(), info.getStatus());
        return tasks(model,1,10);
    }

    @PostMapping("/")
    public String add(Model model,
                     @RequestBody TaskInfo info){
        Task task = taskService.create(info.getDescription(), info.getStatus());
        return tasks(model,1,10);
    }
    @DeleteMapping ("/")
    public String delete(Model model,
                    @PathVariable Integer id){
        taskService.delete(id);
        return tasks(model,1,10);
    }
}