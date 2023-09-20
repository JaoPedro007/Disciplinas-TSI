/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadorpratos;


import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author João Pedro
 */
public enum NivelSujeira {
    BAIXO,
    MEDIO,
    ENGORDURADO;

    private static final NivelSujeira[] VALORES = values();
    private static final int TOTAL_PERCENT = 100;
    private static final int BAIXO_PERCENT = 30;
    private static final int MEDIO_PERCENT = 60;
    private static final int ENGORDURADO_PERCENT = 10;

    public static NivelSujeira obterNivelSujeira() {
        int randomPercent = ThreadLocalRandom.current().nextInt(TOTAL_PERCENT) + 1;

        if (randomPercent <= BAIXO_PERCENT) {
            return BAIXO;
        } else if (randomPercent <= BAIXO_PERCENT + MEDIO_PERCENT) {
            return MEDIO;
        } else {
            return ENGORDURADO;
        }
    }

}
