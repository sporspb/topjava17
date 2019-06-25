package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal USER_MEAL_1 = new Meal(START_SEQ + 2, LocalDateTime.of(2019, Month.JUNE, 24, 12, 0), "Завтрак", 700);
    public static final Meal ADMIN_MEAL_1 = new Meal(START_SEQ + 3, LocalDateTime.of(2019, Month.JUNE, 24, 11, 0), "Завтрак", 800);
    public static final Meal USER_MEAL_2 = new Meal(START_SEQ + 4, LocalDateTime.of(2019, Month.JUNE, 25, 16, 0), "Обед", 1200);
    public static final Meal USER_MEAL_3 = new Meal(START_SEQ + 5, LocalDateTime.of(2019, Month.JUNE, 25, 20, 0), "Ужин", 1500);
    public static final Meal ADMIN_MEAL_2 = new Meal(START_SEQ + 6, LocalDateTime.of(2019, Month.JUNE, 26, 21, 0), "Ужин", 1500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    private static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
