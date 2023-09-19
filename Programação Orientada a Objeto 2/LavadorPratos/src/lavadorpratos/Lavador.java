/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavadorpratos;

/**
 *
 * @author João Pedro
 */
public class Lavador implements Runnable {

    private static final int TEMPO_LAVAGEM_BAIXO = 3;
    private static final int TEMPO_LAVAGEM_MEDIO = 5;
    private static final int TEMPO_LAVAGEM_ENGORDURADO = 10;

    private Escorredor escorredor;
    private volatile boolean done;

    public Lavador(Escorredor escorredor) {
        this.escorredor = escorredor;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public void run() {
        while (!done) {
            boolean espacoDisponivel = escorredor.colocarPrato(PratosSujosFactory.obterPratoSujo());

            if (espacoDisponivel) {
                try {
                    int tempoLavagem = 0;
                    Prato prato = escorredor.tirarPrato();

                    switch (prato.getNivelSujeira()) {
                        case BAIXO:
                            tempoLavagem = TEMPO_LAVAGEM_BAIXO;
                            break;
                        case MEDIO:
                            tempoLavagem = TEMPO_LAVAGEM_MEDIO;
                            break;
                        case ENGORDURADO:
                            tempoLavagem = TEMPO_LAVAGEM_ENGORDURADO;
                            break;
                    }

                    System.out.println("Lavando prato número " + prato.getNumeroSerie() + " por " + tempoLavagem + "ms");
                    Thread.sleep(tempoLavagem);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
