/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadorpratos;

/**
 *
 * @author João Pedro
 */
public class Prato {

    private static int proximoNumeroSerie = 1;

    private int numeroSerie;
    private NivelSujeira nivelSujeira;

    public Prato() {
        this.numeroSerie = proximoNumeroSerie++;
        this.nivelSujeira = NivelSujeira.obterNivelSujeira();
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public NivelSujeira getNivelSujeira() {
        return nivelSujeira;
    }
}
