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
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Participante implements Runnable {

    static final Logger logger = Logger.getLogger(Participante.class.getName());
    private List<Participante> participantes;
    private Socket socket;
    private PrintWriter out;
    private ExecutorService fofoqueiro;
    private String apelido;
    
    public Participante(Socket socket, List<Participante> participantes) {
        this.socket = socket;
        this.participantes = participantes;

        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.fofoqueiro = Executors.newFixedThreadPool(1);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao criar PrintWriter: {0}", e.getMessage());
        }
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Conectado: {0}", socket);
        try {
            Scanner in = new Scanner(socket.getInputStream());
            apelido = in.nextLine();

            while (in.hasNextLine()) {
                String mensagem = in.nextLine();
                ServicoMensagem servico = new ServicoMensagem(mensagem, participantes, apelido);
                fofoqueiro.execute(servico);
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
            fofoqueiro.shutdown();
        }
    }

    public void enviarMensagemParaTodos(String mensagem) {
        for (Participante participante : participantes) {
            out.println(mensagem.toUpperCase());

        }
    }

}
