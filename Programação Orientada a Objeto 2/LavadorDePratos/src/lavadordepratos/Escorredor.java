/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

/**
 *
 * @author João Pedro
 */
public class Escorredor {

    private int contagem = 10;
    private BufferFIFO filaEscorredor;

    public synchronized Prato pegarPrato() throws InterruptedException {
        if (contagem == 0) {
            System.out.print("Estou no while");
            wait();
        } else {
            contagem--;
            notifyAll();
            System.out.println("RETIRANDO PRATOS. O Escorredor esta com " + situacaoEscorredor() + " de lotação");
        }
        return filaEscorredor.verificarPratos();
    }

    public synchronized int situacaoEscorredor() {
        return filaEscorredor.getEspacoOcupado();
    }

}
