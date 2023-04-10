package ru.javarush.service;

import ru.javarush.entity.Status;
import ru.javarush.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAll(int offset, int limit);

    int getAllCount();

    Task edit(int id, String description, Status status);

    Task create(String description, Status status);

    void delete(int id);
}
