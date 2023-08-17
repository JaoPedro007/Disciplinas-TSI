package mega;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GeradorMega", urlPatterns = {"/mega"})
public class GeradorMega extends HttpServlet {

    private Random rand = new Random();

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String nums = request.getParameter("nums");
        int qtde = 6;
        try {
            qtde = Integer.parseInt(nums);
        } catch (Exception ex) {
        }
        ArrayList<Integer> sorteio = new ArrayList<>(qtde);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Numeros da Sorte</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Meu palpite &eacute;:</h1>");
            
            //Estrutura para não repetir números
            for (int i = 0; i < qtde;) {
                int novo = rand.nextInt(60) + 1;
                if (!sorteio.contains(novo)) {
                    sorteio.add(novo);
                    i++;
                }
            }

            
            //Estrutura para ordenar números
            Collections.sort(sorteio);
            for (int i=0; i<sorteio.size(); i++) {
                int num = sorteio.get(i);
                out.println(num + " ");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
