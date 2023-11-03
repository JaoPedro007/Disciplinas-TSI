/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Jo√£o Pedro
 */
public class Barbeiro implements Runnable {

    SalaEspera salaEspera;
    Barbearia barbearia;
    Random rand = new Random();

    public Barbeiro(SalaEspera salaEspera, Barbearia barbearia) {
        this.salaEspera = salaEspera;
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        int time = rand.nextInt(11000);
        while (barbearia.aberta && salaEspera.filaEspera.size() != 0) {
            System.err.println("ABERTA? " + barbearia.aberta + "FILA? " + salaEspera.filaEspera.size());

            salaEspera.filaEspera.remove();
            System.err.printf("Barbeiro est· cortando o cabelo no tempo %d\n", time);
            System.err.println("O tamanho da fila de espera È" + salaEspera.filaEspera.size());
        }
        while (barbearia.aberta && salaEspera.filaEspera.size() == 0) {
            System.err.println("ABERTA? " + barbearia.aberta + "FILA? " + salaEspera.filaEspera.size());

            System.out.println("Barbeiro est· dormindo");

        }

    }

}
