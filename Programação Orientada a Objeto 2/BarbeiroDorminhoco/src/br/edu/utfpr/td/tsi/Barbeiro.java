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
 * @author JoÃ£o Pedro
 */
public class Barbeiro implements Runnable {

    Cliente cliente;
    SalaEspera salaEspera;
    Barbearia barbearia;
    Random rand = new Random();
    public boolean dormindo;

    public Barbeiro(SalaEspera salaEspera, Barbearia barbearia) {
        this.salaEspera = salaEspera;
        this.barbearia = barbearia;
        this.dormindo=false;
    }

    @Override
    public void run() {

        while (barbearia.aberta || salaEspera.filaEspera.size()>0) {
            barbearia.lock.lock();
            try {
                if (salaEspera.filaEspera.size() == 0) {
                    dormindo = true;
                    System.out.println("Barbeiro está dormindo");
                    barbearia.pronto.await();
                }
                int time = rand.nextInt(11);                
                this.cliente = salaEspera.filaEspera.remove();
                System.err.printf("Cabeleiro está cortando o cabelo do cliente por %d segundos. Tamanho da fila atual: %d\n", time, salaEspera.filaEspera.size());
                Thread.sleep(time);
                barbearia.pronto.signal();
            } catch (InterruptedException ex) {
                Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                barbearia.lock.unlock();
            }
        }

    }
}
