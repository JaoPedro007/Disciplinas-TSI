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
        <h1>Hello World!</h1>
        <%

            for (int i = 0; i <= 10; i++) {
                out.println("<span>" + i + "</span>");
            }
        %>
        
        
        <h2>Outra forma de gerar:</h2>
        
               <% for (int x = 0; x < 10; x += 2) { %>
                <span><%=x%></span><br/>
        <% } %>


        <h3>Terceira forma de gerar:</h3>
        <c:forEach begin="1" end="20" step="3" var="k">
            <span>${k}</span><br/>
        </c:forEach>
    </body>
</html>
