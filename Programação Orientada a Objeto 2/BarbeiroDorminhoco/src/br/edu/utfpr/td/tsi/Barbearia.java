/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi;



/**
 *
 * @author João Pedro
 */
public class Barbearia {

    static SalaEspera salaEspera = new SalaEspera();
    static Barbearia barbearia = new Barbearia();

    static Recepcionista recepcionista = new Recepcionista(salaEspera, barbearia);
    static Barbeiro barbeiro = new Barbeiro(salaEspera, barbearia);

    static Cliente cliente = new Cliente();

    static boolean aberta = true;

    static Thread thRecepcionista = new Thread(recepcionista);
    static Thread thBarbeiro = new Thread(barbeiro);
    static Thread thCliente = new Thread(cliente);

    public static void main(String[] args) throws InterruptedException {
        thRecepcionista.setName("Recepcionista");
        thBarbeiro.setName("Barbeiro");
        thCliente.setName("Cliente");
        abrir();

    }

    private static void abrir() throws InterruptedException {
        aberta = true;
        thBarbeiro.start();
        thCliente.start();
        thRecepcionista.start();
        Thread.sleep(60000);
        fechar();
    }

    private static void fechar() throws InterruptedException {
        aberta = false;
        thBarbeiro.join();
        thCliente.join();
        thRecepcionista.join();

    }

    public static boolean isAberta() {
        return aberta;
    }

    public static void setAberta(boolean aberta) {
        Barbearia.aberta = aberta;
    }
}
