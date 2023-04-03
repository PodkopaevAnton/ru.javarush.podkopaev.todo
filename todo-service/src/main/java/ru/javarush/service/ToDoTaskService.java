package ru.javarush.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.dao.TaskDAO;
import ru.javarush.entity.Status;
import ru.javarush.entity.Task;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ToDoTaskService implements TaskService{
    private final TaskDAO taskDAO;

    @Override
    public List<Task> getAll(int offset, int limit) {
        return taskDAO.getAll(offset, limit);
    }

    @Override
    public int getAllCount() {
        return taskDAO.getAllCount();
    }

    @Transactional
    @Override
    public Task edit(int id, String description, Status status) {
        Task task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Not found");
        }

        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }
    @Transactional
    @Override
    public Task create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    @Transactional
    @Override
    public void delete(int id) {
        Task task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Not found");
        }

        taskDAO.delete(task);
    }
}