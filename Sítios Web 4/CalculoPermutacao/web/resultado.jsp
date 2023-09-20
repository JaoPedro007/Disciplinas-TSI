<%-- 
    Document   : resultado
    Created on : 17 de set. de 2023, 21:02:43
    Author     : João Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado</title>
    </head>
    <body>
        <div align="center">
            <h1>Resultados</h1>
            <br/><br/>
            <h2>Permutações:</h2>
            <p>Número de permutações: <%= request.getAttribute("permutacoes")%></p>
            <br/><br/>

            <h2>Combinações:</h2>
            <p>Número de combinações: <%= request.getAttribute("combinacoes")%></p>
        </div>
    </body>
</html>
