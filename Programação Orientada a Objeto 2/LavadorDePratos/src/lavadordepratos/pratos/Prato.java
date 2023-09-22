/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos.pratos;

import java.util.Random;

/**
 *
 * @author João Pedro
 */
public class Prato {

    private static int nextNumeroSerie = 1;
    private int numeroSerie;
    private NivelSujeira nivelSujeira;

    public Prato() {
        this.numeroSerie = nextNumeroSerie++;
        this.nivelSujeira = verNivelSujeira();
    }

    private NivelSujeira verNivelSujeira() {
        Random rand = new Random();
        int percentual = rand.nextInt(100) + 1;

        if (percentual <= 30) {
            return NivelSujeira.BAIXO;
        } else if (percentual <= 90) {
            return NivelSujeira.MEDIO;
        } else {
            return NivelSujeira.ENGORDURADO;
        }
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public NivelSujeira getNivelSujeira() {
        return nivelSujeira;
    }
}
