/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Participante implements Runnable {

    static final Logger logger = Logger.getLogger(Participante.class.getName());
    private final List<Participante> participantes;
    private final Socket socket;
    private final ExecutorService fofoqueiro;
    private String apelido;

    public Participante(Socket socket, List<Participante> participantes, ExecutorService fofoqueiro) {
        this.socket = socket;
        this.participantes = participantes;
        this.fofoqueiro = fofoqueiro;

    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Conectado: {0}", socket);
        try {
            Scanner in = new Scanner(socket.getInputStream());
            apelido = in.nextLine();

            while (in.hasNextLine()) {
                String mensagem = in.nextLine();

                if ("##sair##".equals(mensagem)) {
                    break;
                }
                fofoqueiro.execute(new ServicoMensagem(mensagem, participantes, apelido));

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

    public void enviarMensagem(String mensagem) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(mensagem);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Erro ao enviar mensagem para {0}: {1}",
                    new Object[]{apelido, ex.getMessage()});
        }
    }

}
