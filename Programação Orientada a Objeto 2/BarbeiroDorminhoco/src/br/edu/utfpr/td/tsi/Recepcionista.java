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
 * @author João Pedro
 */
public class Recepcionista implements Runnable {

    Barbearia barbearia;
    SalaEspera salaEspera = new SalaEspera();

    Random rand = new Random();

    public Recepcionista(SalaEspera salaEspera, Barbearia barbearia) {
        this.salaEspera = salaEspera;
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        while (barbearia.isAberta()) {
            try {
                int time = rand.nextInt(11000);
                barbearia.lock.lock();
                try {
                    if (salaEspera.filaEspera.size() == 1) {
                        barbearia.cortar.signalAll();
                    }
                    Thread.sleep(time);
                    Cliente cliente = new Cliente();
                    salaEspera.adicionarCliente(cliente);
                    System.out.printf("A %s está tentando adicionar o cliente no tempo %d\n", Thread.currentThread().getName(), time);

                } finally {
                    barbearia.lock.unlock();
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
