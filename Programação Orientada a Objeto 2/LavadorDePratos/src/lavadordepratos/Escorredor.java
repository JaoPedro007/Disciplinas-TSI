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
        } else {
            try {
                if (quantidadePratos > 0) {
                    quantidadePratos--;
                    espacoOcupado--;
                    System.out.println(verificarSituacaoCapacidade(true));
                } if(espacoOcupado==0) {
                    System.out.println("O Escorredor esta vazio.");
                }
            } catch (Exception e) {
            }
        }

    }

    public void colocarPrato(Prato prato) throws InterruptedException {
        if (espacoOcupado > MAX) {
            throw new RuntimeException("A capacidade maxima do escorredor foi violada");
        }
        try {
            if (espacoOcupado < MAX) {
                espacoOcupado++;
                quantidadePratos++;
                System.out.println(verificarSituacaoCapacidade(false));
            } if(espacoOcupado==10) {
                System.out.println("O Escorredor esta cheio.");
            }
        } catch (Exception e) {
        }
    }

    private String verificarSituacaoCapacidade(boolean isRetirandoPratos) {
        if (isRetirandoPratos) {
            return "RETIRANDO PRATO DO ESCORREDOR | espacoOcupado: " + espacoOcupado;
        } else {
            return "COLOCANDO PRATO NO ESCORREDOR | espacoOcupado: " + espacoOcupado;
        }

    }

    public boolean temPrato() {
        return this.getQuantidadePratos() > 0;
    }

    public int getEspacoOcupado() {
        return espacoOcupado;
    }

    public boolean temEspacoLivre() {
        return espacoOcupado < MAX;
    }

    public int getQuantidadePratos() {
        return quantidadePratos;
    }

    public void setQuantidadePratos(int quantidadePratos) {
        this.quantidadePratos = quantidadePratos;
    }

}
