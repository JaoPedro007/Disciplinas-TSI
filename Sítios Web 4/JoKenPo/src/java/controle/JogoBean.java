package controle;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;
import javax.annotation.ManagedBean;

@Named(value = "jogoBean")
@ManagedBean
@SessionScoped
public class JogoBean implements Serializable {

    private String opcaoJogador;
    String[] opcoesComputador = {"Pedra", "Papel", "Tesoura"};
    private String opcaoComputador;
    private String respostaComputador = "Não escolheu nada";
    private String resposta = "Selecione sua opção!";
    private String imagemJogador;
    private String imagemComputador;
    private String resultado = "Empate";
    private boolean jogadaRealizada = false;

    public JogoBean() {
    }

    public boolean isJogadaRealizada() {
        return jogadaRealizada;
    }

    public void setJogadaRealizada(boolean jogadaRealizada) {
        this.jogadaRealizada = jogadaRealizada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getImagemJogador() {
        return imagemJogador;
    }

    public void setImagemJogador(String imagemJogador) {
        this.imagemJogador = imagemJogador;
    }

    public String getImagemComputador() {
        return imagemComputador;
    }

    public void setImagemComputador(String imagemComputador) {
        this.imagemComputador = imagemComputador;
    }

    public String getOpcaoJogador() {
        return opcaoJogador;
    }

    public String getRespostaComputador() {
        return respostaComputador;
    }

    public void setRespostaComputador(String respostaComputador) {
        this.respostaComputador = respostaComputador;
    }

    public void setOpcaoJogador(String opcaoJogador) {
        this.opcaoJogador = opcaoJogador;
    }

    public String getResposta() {
        return resposta;
    }

    public String jogar() {
        if(!jogadaRealizada){
        Random rand = new Random();
        int i = rand.nextInt(opcoesComputador.length);
        opcaoComputador = opcoesComputador[i];
        jogadaRealizada = true;        
        }

        switch (opcaoComputador) {
            case "Pedra":
                imagemComputador = "/imgs/pedra_computador.png";
                break;
            case "Papel":
                imagemComputador = "/imgs/papel_computador.png";
                break;
            case "Tesoura":
                imagemComputador = "/imgs/tesoura_computador.png";
                break;
            default:
                resposta = "Não escolheu nada!";
                break;
        }

        switch (opcaoJogador) {
            case "Pedra":
                imagemJogador = "/imgs/pedra_jogador.png";
                break;

            case "Tesoura":
                imagemJogador = "/imgs/tesoura_jogador.png";
                break;

            case "Papel":
                imagemJogador = "/imgs/papel_jogador.png";
                break;

            default:
                resposta = "Não escolheu nada";
                break;
        }

        if (opcaoJogador.equals(opcaoComputador)) {
            resultado = "Empate";
        } else if (opcaoJogador.equals("Pedra") && opcaoComputador.equals("Tesoura")) {
            resultado = "Você venceu";
        } else if (opcaoJogador.equals("Papel") && opcaoComputador.equals("Pedra")) {
            resultado = "Você venceu";
        } else if (opcaoJogador.equals("Tesoura") && opcaoComputador.equals("Papel")) {
            resultado = "Você venceu";
        } else {
            resultado = "Computador venceu";
        }

        return "resultado.xhtml";

    }

    public String jogarNovamente() {
        jogadaRealizada=false;
        return "index.xhtml";
    }

}
