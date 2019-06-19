package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService {

    Meal get(int userId, int id) throws NotFoundException;

    void delete(int userId, int id) throws NotFoundException;

    List<Meal> getAll(int userId);

    void update(int userId, Meal meal) throws NotFoundException;

    Meal create(int userId, Meal meal);

    List<Meal> getByDateTime(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}