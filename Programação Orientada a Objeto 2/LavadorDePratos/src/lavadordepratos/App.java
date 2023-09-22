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

    private static Enxugador enxugador;
    private static Lavador lavador;
    private static Thread threadEnxugador, threadLavador;

    public static void work() throws InterruptedException {
        enxugador = new Enxugador();
        lavador = new Lavador();

        threadEnxugador = new Thread(enxugador);
        threadLavador = new Thread(lavador);

        threadLavador.start();

        Thread.sleep(10000);
        System.err.print("ERRO: A aplicação travou e será encerrada");
        System.exit(1);
    }

    public static void stop() throws InterruptedException {

        enxugador.parar(false);

        threadEnxugador.join();

    }

    public static void main(String[] args) throws InterruptedException {
        work();

    }

}
