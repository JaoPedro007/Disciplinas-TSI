/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atividade2;

import java.time.Instant;
import java.util.Random;

/**
 *
 * @author aluno
 */
public class Tarefa implements Runnable {

    private int num;
    private Instant finish;
    private int position;
    private Random random;

    @Override
    public void run() {

        if ("t1".equals(Thread.currentThread().getName())) {

            for (position = 0; position <= 100; position++) {
                if (position % 3 == 0) {
                    System.out.println("A T1 está na posição " + position);
                }
            }
        }
        
        if ("t2".equals(Thread.currentThread().getName())) {

            for (position = 0; position > 100 && position <= 200; position++) {
                if (position % 4 == 0) {
                    System.out.println("A T2 está na posição " + position);
                }
            }
        }

    }

}
