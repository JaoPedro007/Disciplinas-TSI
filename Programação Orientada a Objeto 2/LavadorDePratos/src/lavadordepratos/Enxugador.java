/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

import java.util.concurrent.ThreadLocalRandom;

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
            synchronized (escorredor) {
                while (!escorredor.temPrato()) {
                    try {
                        escorredor.wait();
                    } catch (InterruptedException ex) {
                    }
                }

                try {
                    escorredor.pegarPrato();
                    if (escorredor.getEspacoOcupado() > 0) {
                        enxugarPrato();

                    }
                    escorredor.notify();
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public void enxugarPrato() {
        long time = ThreadLocalRandom.current().nextLong(3, 11);
        try {
//            System.out.print("Enxugando prato\n");
            Thread.sleep(time);

        } catch (InterruptedException ex) {

        }
    }

    public void parar(boolean done) {
        this.done = done;
    }
}
