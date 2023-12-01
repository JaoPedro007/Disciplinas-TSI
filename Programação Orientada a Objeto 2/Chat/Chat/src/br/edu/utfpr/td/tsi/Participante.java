/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Participante implements Runnable {

    static final Logger logger = Logger.getLogger(Participante.class.getName());

    private Socket socket;

    public Participante(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        logger.log(Level.INFO, "Conectado: {0}", socket);
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (in.hasNextLine()) {
                out.println(in.nextLine().toUpperCase());
            }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Erro: {0}", socket);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Não foi possível fechar a conexão do Socket {0}", ex.getMessage());
            }
            logger.log(Level.INFO, "Socket fechado: {0}", socket);

        }
    }
}
