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

    public static void iniciar() throws InterruptedException {
        enxugador = new Enxugador();
        lavador = new Lavador();

        threadEnxugador = new Thread(enxugador);
        threadLavador = new Thread(lavador);

        threadEnxugador.start();

        Thread.sleep(1000);
    }

    public static void parar() throws InterruptedException {

        enxugador.parar(false);

        threadEnxugador.join();

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Começando a lavar");
        iniciar();

        System.out.println("Parando de lavar");
        parar();

        System.out.print("Processos finalizados");
    }

}
