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

    private static final int MAX = 10;
    private int quantidadePratos = 0;
    private int espacoOcupado = 0;

    public void pegarPrato() throws InterruptedException {
        if (espacoOcupado > MAX) {
            throw new RuntimeException("A capacidade maxima do escorredor foi violada");
        }
        try {
            quantidadePratos--;
            espacoOcupado--;
            System.out.println(verificarSituacaoCapacidade(true));
        } catch (Exception e) {
        }

    }

    public void colocarPrato(Prato prato) throws InterruptedException {
        if (espacoOcupado > MAX) {
            throw new RuntimeException("A capacidade maxima do escorredor foi violada");
        }
        try {
            espacoOcupado++;
            quantidadePratos++;
            System.out.println(verificarSituacaoCapacidade(false));
        } catch (Exception e) {

        }
    }

    private String verificarSituacaoCapacidade(boolean isRetirandoPratos) {
        if (espacoOcupado == 0) {
            return "O Escorredor esta vazio, possui " + this.getEspacoOcupado() + " de lotacao";
        }
        if (espacoOcupado == MAX) {
            return "O Escorredor esta cheio, possui " + this.getEspacoOcupado() + " de lotacao";
        }
        if (isRetirandoPratos) {
            return "RETIRANDO PRATO DO ESCORREDOR";
        } else {
            return "COLOCANDO PRATO NO ESCORREDOR";
        }
    }

    public boolean temPrato() {
        return this.getQuantidadePratos() > 0;
    }

    public boolean temEspacoLivre() {
        return espacoOcupado < MAX;
    }

    public int getEspacoOcupado() {
        return espacoOcupado;
    }

    public int getQuantidadePratos() {
        return quantidadePratos;
    }

    public void setQuantidadePratos(int quantidadePratos) {
        this.quantidadePratos = quantidadePratos;
    }

}
