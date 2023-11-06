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
    Random rand = new Random();

    public Recepcionista(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public void run() {

        while (barbearia.aberta) {
            try {
                int time = rand.nextInt(11);
                Cliente cliente = new Cliente();
                Thread.sleep(time);

            } catch (InterruptedException ex) {
                Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }
}
