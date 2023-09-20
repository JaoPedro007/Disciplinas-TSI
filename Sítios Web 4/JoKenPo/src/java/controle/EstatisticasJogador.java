/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

/**
 *
 * @author João Pedro
 */
public class EstatisticasJogador {

    private int vitorias;
    private int empates;
    private int derrotas;

    public EstatisticasJogador() {
        vitorias = 0;
        empates = 0;
        derrotas = 0;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void incrementarVitorias() {
        vitorias++;
    }

    public int getEmpates() {
        return empates;
    }

    public void incrementarEmpates() {
        empates++;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void incrementarDerrotas() {
        derrotas++;
    }
<<<<<<< HEAD
   
    public void zerarEstatisticas(){
        derrotas=0;
        vitorias=0;
        empates=0;
    }
    
=======
>>>>>>> 6bbbda572160a6fc7044ac87d5010fe5024c78e4
}
