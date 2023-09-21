/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

import java.util.Random;

/**
 *
 * @author João Pedro
 */
public class Enxugador implements Runnable {

    private Escorredor escorredor = new Escorredor();
    private Random rand = new Random();
    private boolean enxugar = true;

    @Override
    public void run() {
        while (enxugar == true) {
            try {
                int tempo = rand.nextInt(701)+300;
                escorredor.pegarPrato();
                Thread.sleep(tempo);

            } catch (Exception e) {
            }
        }

    }

    public void parar(boolean enxugar) {
        this.enxugar = enxugar;
    }

}
