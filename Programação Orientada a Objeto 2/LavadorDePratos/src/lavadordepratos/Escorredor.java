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
    private int espacoOcupado = 0;

    public void pegarPrato() throws InterruptedException {
        if (espacoOcupado > MAX) {
//            System.err.print("A capacidade maxima do escorredor foi violada\n");
            System.exit(1);
        } else {
            try {
                if (espacoOcupado > 0) {

                    espacoOcupado--;
//                    System.out.println(verificarSituacao(true));
                }
                if (espacoOcupado == 0) {
//                    System.out.println("O Escorredor esta vazio. | espacoOcupado: " + espacoOcupado);
                }
            } catch (RuntimeException e) {
            }
        }

    }

    public void colocarPrato(Prato prato) throws InterruptedException {
        if (espacoOcupado > MAX) {
//            System.err.print("A capacidade maxima do escorredor foi violada\n");
            System.exit(1);
        }
        try {
            if (espacoOcupado < MAX) {
                espacoOcupado++;
//                System.out.println(verificarSituacao(false));
            }

            if (espacoOcupado >= 10) {
//                System.out.println("O Escorredor esta cheio. | espacoOcupado: " + espacoOcupado);
            }
        } catch (Exception e) {
        }
    }

    private String verificarSituacao(boolean isRetirandoPratos) {
        if (isRetirandoPratos) {
            return "RETIRANDO PRATO DO ESCORREDOR | espacoOcupado: " + espacoOcupado;
        } else {
            return "COLOCANDO PRATO NO ESCORREDOR | espacoOcupado: " + espacoOcupado;
        }

    }

    public boolean temPrato() {
        return this.getEspacoOcupado() > 0;
    }

    public int getEspacoOcupado() {
        return espacoOcupado;
    }

    public boolean temEspacoLivre() {
        return espacoOcupado < MAX;
    }

}
