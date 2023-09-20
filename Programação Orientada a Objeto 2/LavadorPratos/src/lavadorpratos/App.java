/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadorpratos;

/**
 *
 * @author João Pedro
 */
public class App {

    private Lavador lavador;
    private Enxugador enxugador;

    public App() {
        Escorredor escorredor = new Escorredor();
        lavador = new Lavador(escorredor);
        enxugador = new Enxugador(escorredor);
    }

    public void work() {
        Thread lavadorThread = new Thread(lavador);
        Thread enxugadorThread = new Thread(enxugador);

        lavadorThread.start();
        enxugadorThread.start();
    }

    public void stop() {
        lavador.setDone(true);
        enxugador.setDone(true);
    }

    public static void main(String[] args) {
        App app = new App();
        app.work();

        // Aguarde um tempo para que a simulação seja executada
        try {
            Thread.sleep(10000); // 10 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        app.stop();
    }
}
