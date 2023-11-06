/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author João Pedro
 */
public class Barbearia {

    Lock lock = new ReentrantLock();
    Condition pronto = lock.newCondition();

    static Barbearia barbearia = new Barbearia();

    static SalaEspera salaEspera = new SalaEspera();
    static Recepcionista recepcionista = new Recepcionista(barbearia);
    static Barbeiro barbeiro = new Barbeiro(salaEspera, barbearia);

    static boolean aberta = false;

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
        
    }
}
