package ru.javarush.controller;

import lombok.*;
import ru.javarush.entity.Status;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskInfo {
    private String description;
    private Status status;
}

