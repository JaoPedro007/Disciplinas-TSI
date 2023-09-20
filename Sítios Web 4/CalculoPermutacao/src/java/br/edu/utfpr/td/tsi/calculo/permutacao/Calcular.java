/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi.calculo.permutacao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author João Pedro
 */
@WebServlet(name = "Calcular", urlPatterns = {"/Calcular"})

public class Calcular extends HttpServlet{
     private static long fatorial(int x) {
        long fat = 1;
        for (int i = x; i > 1; i--) {
            fat = fat * i;
        }
        return fat;
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String valorN = request.getParameter("N");
            String valorK = request.getParameter("K");

            if (isNumeric(valorN) && isNumeric(valorK)) {
                int N = Integer.parseInt(valorN);
                int K = Integer.parseInt(valorK);

                long permutacoes = fatorial(N) / fatorial(N - K);
                long combinacoes = fatorial(N) / (fatorial(K) * fatorial(N - K));

                request.setAttribute("permutacoes", permutacoes);
                request.setAttribute("combinacoes", combinacoes);

                request.getRequestDispatcher("resultado.jsp").forward(request, response);
            } else {
                request.setAttribute("Erro", "Insira valores numéricos válidos para N e K.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("Erro", "Insira valores numéricos válidos para N e K.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
