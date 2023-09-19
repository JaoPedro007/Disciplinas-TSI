/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadorpratos;

/**
 *
 * @author João Pedro
 */
public class Escorredor {

    private static final int MAX_ESCORREDOR = 10;
    private static final Object lock = new Object();

    private Prato[] pratos;
    private int quantidadePratos;

    public Escorredor() {
        pratos = new Prato[MAX_ESCORREDOR];
        quantidadePratos = 0;
    }

    public synchronized boolean colocarPrato(Prato prato) {
        synchronized (lock) {
            while (quantidadePratos >= MAX_ESCORREDOR) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            pratos[quantidadePratos++] = prato;

            if (quantidadePratos == 1) {
                System.out.println("O escorredor está cheio. Quantidade de pratos: " + quantidadePratos);
                lock.notifyAll();
            }

            return true;
        }
    }

    public synchronized Prato tirarPrato() {
        synchronized (lock) {
            while (quantidadePratos <= 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Prato prato = pratos[--quantidadePratos];
            pratos[quantidadePratos] = null;

            if (quantidadePratos == MAX_ESCORREDOR - 1) {
                System.out.println("O escorredor está vazio. Quantidade de pratos: " + quantidadePratos);
                lock.notifyAll();
            }

            return prato;
        }
    }

}
