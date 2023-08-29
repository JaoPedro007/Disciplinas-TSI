/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atividade2;

/**
 *
 * @author aluno
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        Tarefa tarefa3 = new Tarefa();
        Tarefa tarefa4 = new Tarefa();
        Tarefa tarefa5 = new Tarefa();

        Thread th1 = new Thread(tarefa1);
        Thread th2 = new Thread(tarefa2);
        Thread th3 = new Thread(tarefa3);
        Thread th4 = new Thread(tarefa4);
        Thread th5 = new Thread(tarefa5);
        
        
        th1.setName("t1");
        th1.start();
        
        th2.setName("t2");
        th2.start();


    }

}
