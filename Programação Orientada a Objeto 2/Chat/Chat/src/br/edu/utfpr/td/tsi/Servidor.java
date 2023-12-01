/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Servidor {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Servidor.class.getName());

        try (ServerSocket listener = new ServerSocket(50123)) {
            logger.log(Level.INFO, "Servidor executando...");

            List<Participante> participantes = new ArrayList<>();
            ExecutorService fofoqueiro = Executors.newFixedThreadPool(20);
            while (true) {
                Participante participante = new Participante(listener.accept(), participantes);
                participantes.add(participante);
                fofoqueiro.execute(participante);
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
