/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

import lavadordepratos.pratos.PratosSujosFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import lavadordepratos.pratos.Prato;

/**
 *
 * @author João Pedro
 */
public class Lavador implements Runnable {

    private boolean done = false;
    private final Escorredor escorredor;

    public Lavador(Escorredor escorredor) {
        this.escorredor = escorredor;
    }

    @Override
    public void run() {
        while (!done) {
            while (!escorredor.temEspacoLivre()) {
                synchronized (escorredor) {
                    try {
                        escorredor.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Lavador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            Prato prato = PratosSujosFactory.getPratosSujos();
            int numeroSerie = prato.getNumeroSerie();
            try {
                lavarPrato(prato, numeroSerie);
                escorredor.colocarPrato(prato);
                synchronized (escorredor) {
                    escorredor.notify();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Lavador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void lavarPrato(Prato prato, int serie) {
        try {
            switch (prato.getNivelSujeira()) {
                case BAIXO:
                    System.out.println("Lavando prato " + serie + " com nível de sujeira BAIXO");
                    Thread.sleep(3);
                    break;
                case MEDIO:
                    System.out.println("Lavando prato " + serie + " com nível de sujeira MÉDIO");
                    Thread.sleep(5);
                    break;
                case ENGORDURADO:
                    System.out.println("Lavando prato " + serie + " com nível de sujeira ENGORDURADO");
                    Thread.sleep(10);
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void parar(boolean done) {
        this.done = done;
    }
}
