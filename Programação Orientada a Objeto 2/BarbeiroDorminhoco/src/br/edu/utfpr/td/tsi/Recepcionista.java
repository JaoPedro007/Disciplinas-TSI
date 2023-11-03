/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Recepcionista implements Runnable {

    SalaEspera salaEspera = new SalaEspera();
    Barbearia barbearia;
    Random rand = new Random();
    Lock lock = new ReentrantLock();

    public Recepcionista(SalaEspera salaEspera, Barbearia barbearia) {
        this.salaEspera = salaEspera;
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        while (barbearia.isAberta()) {
            lock.lock();
            try {
                int time = rand.nextInt(11000);
                Thread.sleep(time);
                Cliente cliente = new Cliente();
                salaEspera.adicionarCliente(cliente);
                System.out.printf("A %s está tentando adicionar o cliente no tempo %d\n", Thread.currentThread().getName(), time);

            } catch (InterruptedException ex) {
                Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                lock.unlock();
            }
        }
    }

}
