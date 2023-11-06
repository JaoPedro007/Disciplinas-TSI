/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author João Pedro
 */
public class SalaEspera {
    ArrayBlockingQueue<Cliente> filaEspera = new ArrayBlockingQueue<Cliente>(5);
}
