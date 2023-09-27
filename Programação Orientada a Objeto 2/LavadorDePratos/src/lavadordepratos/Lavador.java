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
    Prato prato = PratosSujosFactory.getPratosSujos();
    int numeroSerie = prato.getNumeroSerie();

    public Lavador(Escorredor escorredor) {
        this.escorredor = escorredor;
    }

    @Override
    public void run() {
        while (!done) {
            synchronized (escorredor) {

                if (!escorredor.temEspacoLivre()) {
                    escorredor.notify();
                } else {
                    lavarPrato(prato, numeroSerie);
                    try {
                        escorredor.colocarPrato(prato);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Lavador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
    }

    private void lavarPrato(Prato prato, int serie) {
        try {
            switch (prato.getNivelSujeira()) {
                case BAIXO:
                    System.out.println("Lavando prato " + serie + " com nivel de sujeira BAIXO");
                    Thread.sleep(3);
                    break;
                case MEDIO:
                    System.out.println("Lavando prato " + serie + " com nivel de sujeira MEDIO");
                    Thread.sleep(5);
                    break;
                case ENGORDURADO:
                    System.out.println("Lavando prato " + serie + " com nivel de sujeira ENGORDURADO");
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
