/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Enxugador implements Runnable {

    private boolean done = false;
    private final Escorredor escorredor;

    public Enxugador(Escorredor escorredor) {
        this.escorredor = escorredor;
    }

    @Override
    public void run() {
        while (!done) {
            while (!escorredor.temPrato()) {
                synchronized (escorredor) {
                    try {
                        escorredor.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Enxugador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            try {
                escorredor.pegarPrato();
                enxugarPrato();
                synchronized (escorredor) {
                    escorredor.notify();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Enxugador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void enxugarPrato() {
        long time = ThreadLocalRandom.current().nextLong(3, 11);
        try {
            System.out.print("Enxugando prato\n");
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Enxugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void parar(boolean done) {
        this.done = done;
    }
}
