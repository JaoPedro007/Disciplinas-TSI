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
    private static final Logger logger = Logger.getLogger(Barbeiro.class.getName());
    Random rand = new Random();
    public boolean dormindo;

    public Barbeiro(SalaEspera salaEspera, Barbearia barbearia) {
        this.salaEspera = salaEspera;
        this.barbearia = barbearia;
        this.dormindo = false;
    }

    @Override
    public void run() {
        while (barbearia.aberta || !salaEspera.filaEspera.isEmpty()) {
            barbearia.lock.lock();
            try {
                if (salaEspera.filaEspera.isEmpty()) {
                    if (!barbearia.aberta) {
                        Thread.currentThread().interrupt();
                    }
                    dormindo = true;
                    logger.log(Level.FINE, "Barbeiro est· dormindo");
                    barbearia.pronto.await();
                } else {
                    int time = rand.nextInt(10) + 1;
                    Cliente cliente = salaEspera.filaEspera.poll();
                    logger.log(Level.FINE, String.format("Barbeiro est· cortando o cabelo do cliente %d. Tamanho da fila atual: %d", cliente.getId(), salaEspera.filaEspera.size()));
                    Thread.sleep(time);
                    barbearia.pronto.signal();
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                barbearia.lock.unlock();
            }

        }

    }
}
