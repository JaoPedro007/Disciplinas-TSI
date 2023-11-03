/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class SalaEspera {

    ConcurrentLinkedQueue<Cliente> filaEspera = new ConcurrentLinkedQueue<Cliente>();
    
    public void adicionarCliente(Cliente cliente) {
        try {
            if (filaEspera.size() >= 5) {
                System.err.println("Cliente foi embora porque não tem mais espaço");
            } else {
                filaEspera.add(cliente);
                System.out.printf("Adicionei o cliente. Tamanho da fila é: %s \n", filaEspera.size());
            }
        } catch (Exception e) {
            Logger.getLogger(SalaEspera.class.getName()).log(Level.SEVERE, null, e);

        }
    }

}
