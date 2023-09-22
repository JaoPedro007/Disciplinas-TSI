/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

import lavadordepratos.pratos.Prato;

/**
 *
 * @author João Pedro
 */
public class Escorredor {

    static final int MAX = 10;
    private int quantidadePratos = 0;
    private int espacoOcupado = 0;
    private String situacaoEscorredor;

    public synchronized Prato pegarPrato() throws InterruptedException {
        if (quantidadePratos <= 0) {
            wait();
        } else {
            quantidadePratos--;
            espacoOcupado--;
            notifyAll();
            System.out.println(verificarSituacaoCapacidade(true));
        }
        return null;
    }

    public synchronized Prato colocarPrato(Prato prato) throws InterruptedException {

        if (espacoOcupado > MAX) {
            System.err.println("Erro: Capacidade máxima do escorredor foi violada, o limite é " + MAX + " pratos.\nA aplicação será encerrada.");
            System.exit(1);
        } else {
            espacoOcupado++;
            quantidadePratos++;
            notifyAll();
            System.out.println(verificarSituacaoCapacidade(false));
            
        }

        return null;
    }

    public synchronized String verificarSituacaoCapacidade(boolean isRetirandoPratos) {
        if (espacoOcupado == 0) {
            return "O Escorredor está vazio, possui " + this.getEspacoOcupado() + " de lotação";
        }
        if (espacoOcupado == MAX) {
            return "O Escorredor está cheio, possui " + this.getEspacoOcupado() + " de lotação";
        }
        if (isRetirandoPratos) {
            return "RETIRANDO PRATO DO ESCORREDOR";
        } else {
            return "COLOCANDO PRATO NO ESCORREDOR";
        }
    }
    
    public boolean temEspacoLivre(){
        return espacoOcupado < MAX;
    }

    public int getEspacoOcupado() {
        return espacoOcupado = quantidadePratos;
    }

    public int getQuantidadePratos() {
        return quantidadePratos;
    }

    public void setQuantidadePratos(int quantidadePratos) {
        this.quantidadePratos = quantidadePratos;
    }

}
