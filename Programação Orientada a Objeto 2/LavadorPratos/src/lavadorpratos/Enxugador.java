/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadorpratos;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author João Pedro
 */
public class Enxugador implements Runnable {

    private static final int TEMPO_ENXUGAGEM_MIN = 3;
    private static final int TEMPO_ENXUGAGEM_MAX = 10;

    private Escorredor escorredor;
    private volatile boolean done;

    public Enxugador(Escorredor escorredor) {
        this.escorredor = escorredor;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public void run() {
        while (!done) {
            boolean temPrato = escorredor.tirarPrato() != null;

            if (temPrato) {
                try {
                    int tempoEnxugagem = ThreadLocalRandom.current().nextInt(TEMPO_ENXUGAGEM_MIN, TEMPO_ENXUGAGEM_MAX + 1);

                    System.out.println("Enxugando prato por " + tempoEnxugagem + "ms");
                    Thread.sleep(tempoEnxugagem);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
