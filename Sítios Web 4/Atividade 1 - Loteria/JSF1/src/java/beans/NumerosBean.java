package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author aluno
 */
@Named(value = "numBean")
@SessionScoped
public class NumerosBean implements Serializable {

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public Integer getFim() {
        return fim;
    }

    public void setFim(Integer fim) {
        this.fim = fim;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LinkedList<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(LinkedList<Integer> numeros) {
        this.numeros = numeros;
    }
    
    public String gerar(){
        Random rand = new Random(System.currentTimeMillis());
        numeros.clear();
        for(int i=0;i<quantidade;){
            Integer val = rand.nextInt(fim-inicio)+inicio;
            if (!numeros.contains(val)){
                numeros.add(val);
                i++;
            }
        }
        return null;
    }
    private int inicio = 0;
    private Integer fim;
    private int quantidade;
    private LinkedList<Integer> numeros = new LinkedList<>();
    public NumerosBean() {
    }
    
}
