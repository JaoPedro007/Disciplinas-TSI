/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Cliente implements Runnable {

    Barbeiro barbeiro;
    Barbearia barbearia;
    SalaEspera salaEspera;

    public Cliente(Barbearia barbearia, Barbeiro barbeiro, SalaEspera salaEspera) {
        this.barbearia = barbearia;
        this.barbeiro = barbeiro;
        this.salaEspera = salaEspera;
    }

    public Cliente() {

    }

    @Override
    public void run() {
        if (salaEspera.filaEspera.size() > 5) {
            System.out.println("Cliente foi embora pois a fila está cheia");
        } else {
            while (barbearia.aberta) {
                barbearia.lock.lock();
                try {
                    if (barbeiro.dormindo) {
                        System.out.println("Cliente sentou na cadeira e acordou o Barbeiro");
                        salaEspera.filaEspera.add(this);
                        System.out.printf("O %s foi adicionado na fila. Tamanho da fila: %d\n", Thread.currentThread().getName(), salaEspera.filaEspera.size());
                        barbeiro.dormindo = false;
                        barbearia.pronto.signal();
                    } else {
                        salaEspera.filaEspera.add(this);
                        System.out.printf("O %s foi adicionado na fila. Tamanho da fila: %d\n", Thread.currentThread().getName(), salaEspera.filaEspera.size());
                        System.out.println("Cliente sentou na cadeira ");
                        barbearia.pronto.await();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    barbearia.lock.unlock();

                }
            }
        }

    }

}
