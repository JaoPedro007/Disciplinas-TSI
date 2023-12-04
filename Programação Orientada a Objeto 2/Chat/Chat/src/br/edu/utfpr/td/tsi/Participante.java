/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Participante implements Runnable {

    static final Logger log = Logger.getLogger(Participante.class.getName());
    private final ConcurrentLinkedQueue<Participante> participantes;
    private final Socket socket;
    private final ExecutorService fofoqueiro;
    private String apelido;

    public Participante(Socket socket, ConcurrentLinkedQueue<Participante> participantes, ExecutorService fofoqueiro) {
        this.socket = socket;
        this.participantes = participantes;
        this.fofoqueiro = fofoqueiro;
        log.setLevel(Level.FINE);

    }

    @Override
    public void run() {
        log.log(Level.INFO, "Conectado: {0}", socket);
        try {
            Scanner in = new Scanner(socket.getInputStream());
            apelido = in.nextLine();

            while (in.hasNextLine()) {
                String mensagem = in.nextLine();

                if ("##sair##".equals(mensagem)) {
                    break;
                }
                fofoqueiro.execute(new ServicoMensagem(mensagem, participantes, apelido));
                log.log(Level.FINE, "{0} enviou {1}", new Object[]{apelido, mensagem});
            }
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Erro: {0}", socket);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                log.log(Level.SEVERE, "Não foi possível fechar a conexão do Socket {0}", ex.getMessage());
            }
            log.log(Level.INFO, "Socket fechado: {0}", socket);
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
