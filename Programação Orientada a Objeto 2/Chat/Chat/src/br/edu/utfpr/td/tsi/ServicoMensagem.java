/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author João Pedro
 */
public class ServicoMensagem implements Runnable {

    private String apelido;
    private String texto;
    private List<Participante> participantes;
    static final Logger logger = Logger.getLogger(ServicoMensagem.class.getName());

    public ServicoMensagem(String texto, List<Participante> participantes, String apelido) {
        this.texto = texto;
        this.participantes = participantes;
        this.apelido = apelido;
    }

    @Override
    public void run() {
        for (Participante participante : participantes) {
            try {
                // Adicionando sincronização para garantir acesso seguro à lista de participantes
                synchronized (participante) {
                    // Enviando a mensagem para o participante
                    participante.enviarMensagem(formatarMensagem());
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Erro ao enviar mensagem: {0}",
                        new Object[]{e.getMessage()});
            }
        }
    }

    private String formatarMensagem() {
        return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS FINE %2$s - %3$s",
                System.currentTimeMillis(), apelido, texto);
    }
}
