<%-- 
    Document   : index
    Created on : 17 de set. de 2023, 21:03:23
    Author     : João Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Calculos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div align="center">

            
            <h3>Insira os valores</h3>
            <form action="Calcular" method="POST">
                <input placeholder="Tamanho do conjunto (N)" type="text" name="N" />
                <input placeholder="Tamanho do conjunto (K)" type="text" name="K" />
                <input type="submit" value="Calcular" />
            </form>

            <!-- Verifica se há mensagens de erro definidas na requisição -->
            <% String erro = (String) request.getAttribute("Erro");
                if (erro != null && !erro.isEmpty()) {%>
            <p style="color: red;"><%= erro%></p>
            <% }%>
        </div>
    </body>
</html>
