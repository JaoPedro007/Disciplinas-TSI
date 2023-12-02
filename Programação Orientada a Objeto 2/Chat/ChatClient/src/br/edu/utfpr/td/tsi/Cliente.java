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

    static final Logger log = Logger.getLogger(Cliente.class.getName());

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            log.info("Passe o endereço IP do servidor e seu apelido");
            return;
        }
        String ip = args[0];
        String apelido = args[1];
        try (Socket socket = new Socket(ip, 50123)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(apelido);

            log.info("Digite qualquer coisa. E ##sair## para sair");

            Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(socket.getInputStream());
            try {
                while (scanner.hasNextLine()) {
                    out.println(scanner.nextLine());
                    String mensagemRecebida = in.nextLine();

                    log.info(mensagemRecebida);
                }

            } catch (Exception e) {
                log.severe("Voce escolheu sair");
            }

        } catch (ConnectException ex) {
            log.log(Level.SEVERE, "Nao foi possivel se conectar ao servidor. Exception é: {0} ", ex.getMessage());
        }

    }

}
