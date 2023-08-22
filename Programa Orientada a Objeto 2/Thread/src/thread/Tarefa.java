/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package thread;

import java.util.Random;

/**
 *
 * @author aluno
 */
public class Tarefa implements Runnable{
    
    @Override
    public void run() {
        System.out.println("Hello Thread " + Thread.currentThread().getName());
        Random r = new Random();
        long tempo = 1000 + r.nextInt(2001);
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Encerrando Thread" + Thread.currentThread().getName());
        }
    
}
