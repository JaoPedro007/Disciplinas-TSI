/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jo√£o Pedro
 */
public class Barbeiro implements Runnable {

    SalaEspera salaEspera;
    Barbearia barbearia;
    Random rand = new Random();
    public boolean dormindo;

    public Barbeiro(SalaEspera salaEspera, Barbearia barbearia) {
        this.salaEspera = salaEspera;
        this.barbearia = barbearia;
    }

    @Override
    public void run() {

        while (barbearia.aberta) {
            barbearia.lock.lock();
            try {
                if (salaEspera.filaEspera.size() == 0) {
                    dormindo = true;
                    System.out.println("Barbeiro est· dormindo");
                    barbearia.pronto.await();
                }
                int time = rand.nextInt(11000);
                salaEspera.filaEspera.remove();
                System.err.printf("Cabeleiro est· cortando o cabelo do cliente por %d segundos. Tamanho da fila atual: %d\n", time, salaEspera.filaEspera.size());
                Thread.sleep(time);

            } catch (InterruptedException ex) {
                Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                barbearia.lock.unlock();
            }
        }

    }
}
