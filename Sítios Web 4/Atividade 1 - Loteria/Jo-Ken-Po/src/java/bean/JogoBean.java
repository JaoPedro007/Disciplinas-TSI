package bean;



import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "jogoBean")
@SessionScoped
public class JogoBean implements Serializable {

    private char opcaoJogador;
    private String resposta = "Selecione sua opção!";

    public JogoBean() {
    }

    public char getOpcaoJogador() {
        return opcaoJogador;
    }

    public void setOpcaoJogador(char opcaoJogador) {
        this.opcaoJogador = opcaoJogador;
    }

    public String getResposta() {
        return resposta;
    }    

    public String jogar() {
        resposta = switch (opcaoJogador) {
            case 'P' -> "Você selecionou pedra.";
            case 'T' -> "Você selecionou tesoura.";
            case 'A' -> "Você selecionou papel.";
            default -> "Selecione sua opção!";
        };
        return null;
    }

}
