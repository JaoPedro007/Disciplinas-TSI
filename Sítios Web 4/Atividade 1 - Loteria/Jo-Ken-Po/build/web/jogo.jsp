<%-- 
    Document   : jogo
    Created on : 11 de set. de 2023, 20:12:10
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
 <head>
        <title>Jo-Ken-Po</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <center>
        <h3>Escolha</h3>
    <br><br>
    <form method="POST" action="jogar"> 

            <table>

                <tbody>
                    <tr>
                        <td><img src="imgs/pedra_jogador.png" alt="Pedra" style="width: 50%"/></td>
                        <td><img src="imgs/papel_jogador.png" alt="Papel" style="width: 50%"/></td>
                        <td><img src="imgs/tesoura_jogador.png" alt="Tesoura" style="width: 50%"/></td>

                    </tr>
                    <tr>
                        <td><input value="Pedra" name="opcao" type="radio" id="pedra">Pedra</td>
                        <td><input value="Papel" name="opcao" type="radio" id="papel">Papel</td>
                        <td><input value="Tesoura" name="opcao" type="radio" id="tesoura"> Tesoura</td>
                    </tr>
                </tbody>
            </table>
            <br><br>
            <input type="submit" value="Enviar"/>
        </form>
    </center>
    </body>
</html>
