/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author João Pedro
 */
public class Cliente {

    static Logger logger = Logger.getLogger(Cliente.class.getName());

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            logger.log(Level.INFO, "Passe o endereço IP do servidor e seu apelido");
            return;
        }
        String ip = args[0];
        String apelido = args[1];
        try (Socket socket = new Socket(ip, 50123)) {

            logger.log(Level.INFO, "Digite qualquer coisa. E ##sair## para sair");
            Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(socket.getInputStream());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
                String mensagem = String.format("%1$td/%1$tm/%1$tY %1$tH:%1$tM FINE (%2$s) - %3$s",
                        new java.util.Date(), apelido, in.nextLine());

                System.out.println(mensagem);
                System.out.flush();
            }
        } catch (ConnectException ex) {
            logger.log(Level.SEVERE, "Nao foi possivel se conectar ao servidor. Exception é: {0} ", ex.getMessage());
        }

    }

}
