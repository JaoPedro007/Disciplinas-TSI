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
        if (barbearia.aberta && barbeiro.dormindo) {
            barbearia.lock.lock();
            try {
                System.out.println("Cliente sentou na cadeira");
                barbearia.pronto.signal();

                System.out.println("Cliente acordou o Barbeiro");
                barbearia.pronto.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                barbearia.lock.unlock();
            }
        }

    }
}
