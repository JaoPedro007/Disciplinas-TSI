/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

import java.util.logging.Level;
import java.util.logging.Logger;
import lavadordepratos.pratos.Prato;

/**
 *
 * @author João Pedro
 */
public class Lavador implements Runnable {

    private boolean done = false;
    private Escorredor escorredor = new Escorredor();

    @Override
    public void run() {
        while (!done) {
            synchronized (this.escorredor) {
                while (!escorredor.temEspacoLivre()) {
                    try {
                        escorredor.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Lavador.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (!done) {
                    Prato prato = PratosSujosFactory.getPratosSujos();
                    lavarPrato(prato);
                    try {
                        System.out.print("Lavando prato com nível de sujeira " + prato.getNivelSujeira() + "\n");
                        escorredor.colocarPrato(prato);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Lavador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    private void lavarPrato(Prato prato) {
        try {
            switch (prato.getNivelSujeira()) {
                case BAIXO:
                    Thread.sleep(3000);
                    break;
                case MEDIO:
                    Thread.sleep(5000);
                    break;
                case ENGORDURADO:
                    Thread.sleep(10000);
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
