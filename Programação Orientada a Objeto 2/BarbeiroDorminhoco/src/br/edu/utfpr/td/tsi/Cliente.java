/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Cliente implements Runnable {

    private static AtomicLong proximoId = new AtomicLong(1);
    private long id;
    Barbeiro barbeiro;
    Barbearia barbearia;
    SalaEspera salaEspera;
    private static final Logger logger = Logger.getLogger(Cliente.class.getName());

    public Cliente(Barbearia barbearia, Barbeiro barbeiro, SalaEspera salaEspera) {
        this.barbearia = barbearia;
        this.barbeiro = barbeiro;
        this.salaEspera = salaEspera;
        this.id = proximoId.getAndIncrement();
    }

    @Override
    public void run() {
        while (barbearia.aberta && !Thread.currentThread().isInterrupted()) {
            if (salaEspera.filaEspera.size() == 5) {
                logger.log(Level.INFO, String.format("Um cliente foi embora. Não há mais espaço. Tamanho da fila: %s\n", salaEspera.filaEspera.size()));
                Thread.currentThread().interrupt();
            } else {
                barbearia.lock.lock();
                try {
                    if (barbeiro.dormindo) {
                        if (salaEspera.filaEspera.size() < 5) {
                            if (!salaEspera.filaEspera.contains(this)) {
                                salaEspera.filaEspera.add(this);
                                logger.log(Level.INFO, String.format("O Cliente %d foi adicionado na fila. Tamanho da fila: %d\n", getId(), salaEspera.filaEspera.size()));
                            }
                        }
                        barbeiro.dormindo = false;
                        barbearia.pronto.signal();
                        logger.log(Level.INFO, String.format("O Cliente %d sentou na cadeira e acordou o Barbeiro\n", getId()));

                    } else {
                        if (salaEspera.filaEspera.size() < 5) {
                            if (!salaEspera.filaEspera.contains(this)) {
                                salaEspera.filaEspera.add(this);
                                logger.log(Level.INFO, String.format("O Cliente %d foi adicionado na fila e está esperando. Tamanho da fila: %d\n", getId(), salaEspera.filaEspera.size()));
                            }
                        }
                    }
                } finally {
                    barbearia.lock.unlock();
                }

            }
        }
    }

    public long getId() {
        return id;
    }
}
