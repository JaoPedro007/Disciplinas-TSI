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
                    System.out.println("Barbeiro est· dormindo");
                    barbearia.cortar.await();
                }
                salaEspera.chamarCliente();
                cortarCabelo();
            } catch (InterruptedException ex) {
                Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                barbearia.lock.unlock();
            }
        }

    }

    private void cortarCabelo() {
        int time = rand.nextInt(11000);
        System.err.printf("Cabeleiro est· cortando o cabelo do cliente por %d segundos\n", time);
    }
}
