/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadorpratos;

/**
 *
 * @author João Pedro
 */
public class PratosSujosFactory {

    private static final int PRIMEIRO_PRATO = 1;

    public static Prato obterPratoSujo() {
        return new Prato();
    }
}
