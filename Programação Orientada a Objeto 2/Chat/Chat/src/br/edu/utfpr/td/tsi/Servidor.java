/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Servidor {

    static final Logger log = Logger.getLogger(Servidor.class.getName());

    public static void main(String[] args) {

        try (ServerSocket listener = new ServerSocket(50123)) {
            log.info("Servidor executando...");

            ConcurrentLinkedQueue<Participante> participantes = new ConcurrentLinkedQueue<>();
            ExecutorService executorService = Executors.newFixedThreadPool(20);
            ExecutorService fofoqueiro = Executors.newFixedThreadPool(1);
            while (true) {
                Participante participante = new Participante(listener.accept(), participantes, fofoqueiro);
                participantes.add(participante);
                executorService.execute(participante);
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
