<%-- 
    Document   : index
    Created on : 28 de ago. de 2023, 20:23:05
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="emprestimo" class="java.util.LinkedList" scope="session"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Simular empréstimo!</h1>
        <form action="resultado" method="POST">
            Valor <input type="text" name="valor" size="40"></input><br/>
            Taxa: <input type="text" name="taxa" size="40"></input><br/>
            Custo Fixo: <input type="text" name="custo" size="40"></input><br/>
            Prazo: <input type="text" name="prazo" size="40"></input><br/>
            <input type="submit" value="Simular"/>
        </form>
        <br/>

    </body>
</html>
