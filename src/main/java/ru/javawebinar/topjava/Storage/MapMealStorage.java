package ru.javawebinar.topjava.Storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MapMealStorage extends AbstractMealStorage {

    public static final AtomicInteger COUNT = new AtomicInteger();
    private static final Map<Integer, Meal> STORAGE = new ConcurrentHashMap<>();

    static {
        int id = COUNT.getAndIncrement();
        Meal meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        STORAGE.put(id, meal);
        id = COUNT.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        STORAGE.put(id, meal);
        id = COUNT.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        STORAGE.put(id, meal);
        id = COUNT.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        STORAGE.put(id, meal);
        id = COUNT.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        STORAGE.put(id, meal);
        id = COUNT.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
        STORAGE.put(id, meal);
    }

    public static Map<Integer, Meal> getSTORAGE() {
        return STORAGE;
    }

    public static AtomicInteger getCOUNT() {
        return COUNT;
    }

    @Override
    public void save(Meal meal) {
        STORAGE.put(meal.getId(), meal);
    }

    @Override
    public void update(Meal meal) {
        STORAGE.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        STORAGE.remove(id);
    }

    @Override
    public Meal get(int id) {
        return STORAGE.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return STORAGE.values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList());
    }
}