package ru.javawebinar.topjava.Storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealStorage {
    void save(Meal meal);

    void update(Meal meal);

    void delete(int id);

    Meal get(int id);

    List<Meal> getAll();
}
