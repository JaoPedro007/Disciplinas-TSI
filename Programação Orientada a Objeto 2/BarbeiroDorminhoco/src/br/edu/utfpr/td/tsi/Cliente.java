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
        while (barbearia.aberta) {
            if (salaEspera.filaEspera.size() >= 5) {
                System.out.printf("O %s foi embora, pois não há mais espaço. Tamanho da fila: %s\n", Thread.currentThread().getName(), salaEspera.filaEspera.size());
            } else {
                barbearia.lock.lock();
                try {
                    if (barbeiro.dormindo) {

                        salaEspera.filaEspera.add(this);
                        System.out.printf("O %s foi adicionado na fila. Tamanho da fila: %d\n", Thread.currentThread().getName(), salaEspera.filaEspera.size());
                        
                        barbeiro.dormindo = false;
                        barbearia.pronto.signal();
                        System.out.println("Cliente sentou na cadeira e acordou o Barbeiro");

                    } else {
                        salaEspera.filaEspera.add(this);
                        System.out.printf("O %s foi adicionado na fila e está esperando. Tamanho da fila: %d\n", Thread.currentThread().getName(), salaEspera.filaEspera.size());
                    }

                } finally {
                    barbearia.lock.unlock();
                }
            }
        }
    }

}
