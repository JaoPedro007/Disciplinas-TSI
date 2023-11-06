/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Barbearia {

    private static final Logger logger = Logger.getLogger(Barbearia.class.getName());

    Lock lock = new ReentrantLock();
    Condition pronto = lock.newCondition();

    static Barbearia barbearia = new Barbearia();

    static SalaEspera salaEspera = new SalaEspera();
    static Recepcionista recepcionista = new Recepcionista(barbearia);
    static Barbeiro barbeiro = new Barbeiro(salaEspera, barbearia);

    static volatile boolean aberta = false;

    static Thread thRecepcionista = new Thread(recepcionista);
    static Thread thBarbeiro = new Thread(barbeiro);

    public static void main(String[] args) throws InterruptedException {
        thRecepcionista.setName("Recepcionista");
        thBarbeiro.setName("Barbeiro");
        abrir();

    }

    private static void abrir() throws InterruptedException {
        aberta = true;
        thRecepcionista.start();
        thBarbeiro.start();
        Thread.sleep(5000);
        fechar();
    }

    private static void fechar() throws InterruptedException {
        aberta = false;
        thRecepcionista.join();
        thBarbeiro.join();
        logger.log(Level.INFO, "A barbearia está fechada");

    }
}
