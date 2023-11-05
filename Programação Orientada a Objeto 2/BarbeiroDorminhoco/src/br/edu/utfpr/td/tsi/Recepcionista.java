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

    public Recepcionista(Barbearia barbearia, SalaEspera salaEspera) {
        this.barbearia = barbearia;
        this.salaEspera = salaEspera;
    }

    @Override
    public void run() {

        while (barbearia.aberta && salaEspera.filaEspera.size() < 5) {
            if (salaEspera.filaEspera.size() >= 5) {
                System.err.printf("Cliente foi embora. Tamanho da fila atual %d\n", salaEspera.filaEspera.size());
            }
            barbearia.lock.lock();
            try {
                int time = rand.nextInt(1100);

                Cliente cliente = new Cliente();
                salaEspera.filaEspera.add(cliente);
                System.out.printf("A %s adicionou o cliente. Tamanho da fila: %d\n", Thread.currentThread().getName(), salaEspera.filaEspera.size());
                barbearia.pronto.signal();
                Thread.sleep(time);

            } catch (InterruptedException ex) {
                Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                barbearia.lock.unlock();
            }
        }
    }
}
