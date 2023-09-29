/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

import lavadordepratos.pratos.PratosSujosFactory;
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
            synchronized (escorredor) {
                while (!escorredor.temEspacoLivre()) {
                    try {
                        escorredor.wait();
                    } catch (InterruptedException ex) {
                    }
                }

                Prato prato = PratosSujosFactory.getPratosSujos();

                try {
                    if (escorredor.getEspacoOcupado() < 10) {
                        lavarPrato(prato, prato.getNumeroSerie());
                    }
                    escorredor.colocarPrato(prato);
                    escorredor.notify();
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    private void lavarPrato(Prato prato, int serie) {
        try {
            switch (prato.getNivelSujeira()) {
                case BAIXO -> {
//                    System.out.println("Lavando prato " + serie + " com nivel de sujeira BAIXO");
                    Thread.sleep(3);
                }
                case MEDIO -> {
//                    System.out.println("Lavando prato " + serie + " com nivel de sujeira MEDIO");
                    Thread.sleep(5);
                }
                case ENGORDURADO -> {
//                    System.out.println("Lavando prato " + serie + " com nivel de sujeira ENGORDURADO");
                    Thread.sleep(10);
                }
            }
        } catch (InterruptedException e) {
        }
    }

    public void parar(boolean done) {
        this.done = done;
    }
}
