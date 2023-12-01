/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.List;

/**
 *
 * @author João Pedro
 */
public class ServicoMensagem implements Runnable {

    private String texto;
    private List<Participante> participantes;

    public ServicoMensagem(String texto, List<Participante> participantes) {
        this.texto = texto;
        this.participantes = participantes;
    }

    @Override
    public void run() {
        String mensagem = String.format("%1$td/%1$tm/%1$tY %1$tH:%1$tM FINE - %2$s",
                new java.util.Date(), texto.toUpperCase());

        System.out.println(mensagem);

        for (Participante participante : participantes) {
            participante.enviarMensagemParaTodos(mensagem);
        }
    }
}
