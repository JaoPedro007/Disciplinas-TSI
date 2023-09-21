/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadordepratos;

/**
 *
 * @author João Pedro
 */
class BufferFIFO {

    private int espacoOcupado = 0;
    private Prato[] lista;

    public Prato verificarPratos() {
        if (espacoOcupado == 0) {
            return null;
        }
        int i=5;
        Prato prato = lista[i];

        espacoOcupado--;
        return prato;
    }

    public int getEspacoOcupado() {
        return espacoOcupado;
    }

    public void setEspacoOcupado(int espacoOcupado) {
        this.espacoOcupado = espacoOcupado;
    }

}
