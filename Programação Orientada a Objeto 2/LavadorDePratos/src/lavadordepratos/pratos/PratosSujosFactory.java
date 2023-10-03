/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos.pratos;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author João Pedro
 */
public class PratosSujosFactory {

    public static Queue<Prato> pratosSujos = new LinkedList<>();

    public static Prato getPratosSujos() {
        if (pratosSujos.isEmpty()) {
            pratosSujos.offer(new Prato());
        }

        return pratosSujos.poll();
    }

}
