<%-- 
    Document   : index
    Created on : 22 de ago. de 2023, 20:08:01
    Author     : aluno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="lista" class="java.util.LinkedList" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="incluir" method="POST">
            Item: <input type="text" name="item" size="40"></input><br/>
            <input type="submit" value="incluir"/>
        </form>
        <br/>
        <h2>Itens da Lista</h2>
        <c:forEach items="${lista}" var="it">
            <span>${it}
                <form action="remover" method="POST">
                    <input type="hidden" name="item" value="${it}"/>
                    <input type="submit" value="x"/>


                </form>

            </span><br/>
        </c:forEach>
    </body>
</html>
