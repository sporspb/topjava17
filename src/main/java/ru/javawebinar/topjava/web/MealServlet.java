package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Storage.AbstractMealStorage;
import ru.javawebinar.topjava.Storage.MapMealStorage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final AbstractMealStorage storage = new MapMealStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("dateTime"), TimeUtil.getFormatter());
        String desc = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (id.equals("-1")) {
            storage.save(new Meal(MapMealStorage.COUNT.incrementAndGet(), date, desc, calories));
        } else {
            storage.update(new Meal(Integer.parseInt(id), date, desc, calories));
        }
        log.info("redirect to meals");
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            log.info("getAll");
            request.setAttribute("meals", MealsUtil.getFilteredWithExcess(storage.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            return;
        }
        switch (action) {
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                log.info("delete {}", id);
                storage.delete(id);
                response.sendRedirect("meals");
                break;
            case "add":
                log.info("add");
                request.setAttribute("meal", Meal.EMPTYMEAL);
                request.getRequestDispatcher("edit.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                log.info("edit {}", id);
                request.setAttribute("meal", storage.get(id));
                request.getRequestDispatcher("edit.jsp").forward(request, response);
                break;
            default:
                throw new IllegalArgumentException(String.format("Action %s is illegal", action));
        }
    }
}
