<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Time</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Excess</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr bgcolor="${meal.excess ? "red" : "green"}">
                <td><%= meal.getDateTime().format(TimeUtil.getFormatter())%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.excess}</td>
                <td><a href="meals?id=${meal.id}&action=edit" class="button">Edit</a></td>
                <td><a href="meals?id=${meal.id}&action=delete" class="button">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="meals?action=add" class="button">ADD NEW MEAL</a>
</section>
</body>
</html>