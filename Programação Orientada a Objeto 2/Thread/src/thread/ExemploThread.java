/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

/**
 *
 * @author aluno
 */
public class ExemploThread {

    public static void main(String[] args) throws InterruptedException {
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();

        Thread thread1 = new Thread(tarefa1);
        Thread thread2 = new Thread(tarefa2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        
        System.out.println("Fim");

    }

}
