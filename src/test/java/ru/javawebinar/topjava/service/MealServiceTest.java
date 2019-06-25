package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.now(), "description", 500);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), newMeal, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_1.getId(), USER_ID);
        assertMatch(service.getAll(USER_ID), USER_MEAL_3, USER_MEAL_2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteOtherUserMeal() {
        service.delete(ADMIN_MEAL_1.getId(), USER_ID);
    }

    @Test
    public void get() {
        Meal meal = service.get(USER_MEAL_1.getId(), USER_ID);
        assertMatch(meal, USER_MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getOtherUserMeal() {
        service.get(ADMIN_MEAL_1.getId(), USER_ID);
    }

    @Test
    public void update() {
        Meal updated = new Meal(USER_MEAL_2);
        updated.setCalories(1000);
        updated.setDescription("new Desc");
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL_2.getId(), USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        service.update(new Meal(100, LocalDateTime.now(), "test", USER_ID), USER_ID);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }
}
