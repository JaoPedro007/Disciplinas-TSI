/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

/**
 *
 * @author João Pedro
 */
public class App {

    static Escorredor escorredor = new Escorredor();
    static Enxugador enxugador = new Enxugador(escorredor);
    static Lavador lavador = new Lavador(escorredor);

    public static void work() throws InterruptedException {

        Thread threadEnxugador = new Thread(enxugador);
        Thread threadLavador = new Thread(lavador);
        threadLavador.start();
        threadEnxugador.start();

        Thread.sleep(2000);
        stop();
    }

    private static void stop() throws InterruptedException {
        enxugador.parar(true);
        lavador.parar(true);
    }

    public static void main(String[] args) throws InterruptedException {
        work();

    }

}
