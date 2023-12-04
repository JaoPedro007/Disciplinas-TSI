/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author João Pedro
 */
public class ServicoMensagem implements Runnable {

    private final String apelido;
    private final String texto;
    private final ConcurrentLinkedQueue<Participante> participantes;

    static final Logger log = Logger.getLogger(ServicoMensagem.class.getName());

    public ServicoMensagem(String texto, ConcurrentLinkedQueue<Participante> participantes, String apelido) {
        this.texto = texto;
        this.participantes = participantes;
        this.apelido = apelido;
        log.setLevel(Level.FINE);
    }

    @Override
    public void run() {

        for (Participante participante : participantes) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String dataFormatada = dateFormat.format(new Date());
                String mensagemFormatada = String.format("%s (%s) - %s", dataFormatada, apelido, texto.toUpperCase());

                PrintWriter out = new PrintWriter(participante.getSocket().getOutputStream(), true);
                out.println(mensagemFormatada);
                log.log(Level.FINE, mensagemFormatada);
               

            } catch (IOException e) {
                log.log(Level.SEVERE, "Erro ao enviar mensagem: {0}", e);
            }
        }
    }

}
