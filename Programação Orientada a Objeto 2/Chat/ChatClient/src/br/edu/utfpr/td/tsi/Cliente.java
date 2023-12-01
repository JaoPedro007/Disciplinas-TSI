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
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(apelido);

            logger.log(Level.INFO, "Digite qualquer coisa. E ##sair## para sair");
            
            Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(socket.getInputStream());

            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
                String mensagemRecebida = in.nextLine();              

                System.out.println(mensagemRecebida);
            }
        } catch (ConnectException ex) {
            logger.log(Level.SEVERE, "Nao foi possivel se conectar ao servidor. Exception é: {0} ", ex.getMessage());
        }

    }

}
