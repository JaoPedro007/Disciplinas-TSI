
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named("jogoBean")
@SessionScoped
public class JogoBean implements Serializable {

    private static final List<String> OPCOES = Arrays.asList("Pedra", "Papel", "Tesoura");

    @Inject
    private JogadorDAO jogadorDao;

    private int quantidadeJogos;
    private int quantidadeVitoriasJogador;
    private int quantidadeVitoriasComputador;
    private int quantidadeEmpates;

    private String escolhaJogador;
    private String resultado;
    private String nomeJogador;
    private String imagemJogador;
    private String imagemComputador;
    private String escolhaComputador;

    @PostConstruct
    public void init() {
        
    }

    public String jogar() {
        escolhaComputador = obterEscolhaComputador();
        atualizarEstatisticas();
        escolherImagemJogador();
        escolherImagemComputador();

        redirect("resultado.xhtml");
        return null;

    }

    public String jogarNovamente() {
        return "jogar.xhtml";

    }

    public String entrar() {
        return "jogar.xhtml";
    }

    public void escolherImagemJogador() {
        if ("Pedra".equalsIgnoreCase(escolhaJogador)) {
            imagemJogador = "pedra_jogador";
        } else if ("Papel".equalsIgnoreCase(escolhaJogador)) {
            imagemJogador = "papel_jogador";
        } else if ("Tesoura".equalsIgnoreCase(escolhaJogador)) {
            imagemJogador = "tesoura_jogador";
        } else {
            imagemJogador = "";
        }
    }

    public void escolherImagemComputador() {
        if ("Pedra".equalsIgnoreCase(escolhaComputador)) {
            imagemComputador = "pedra_computador";
        } else if ("Papel".equalsIgnoreCase(escolhaComputador)) {
            imagemComputador = "papel_computador";
        } else if ("Tesoura".equalsIgnoreCase(escolhaComputador)) {
            imagemComputador = "tesoura_computador";
        } else {
            imagemComputador = "";
        }
    }

    private String obterEscolhaComputador() {
        Random random = new Random();
        int indiceEscolha = random.nextInt(OPCOES.size());
        return OPCOES.get(indiceEscolha);
    }

    private String determinarResultado(String escolhaJogador, String escolhaComputador) {
        if (escolhaJogador.equals(escolhaComputador)) {
            quantidadeEmpates++;
            return "Empate!";
        } else if ((escolhaJogador.equals("Pedra") && escolhaComputador.equals("Tesoura"))
                || (escolhaJogador.equals("Papel") && escolhaComputador.equals("Pedra"))
                || (escolhaJogador.equals("Tesoura") && escolhaComputador.equals("Papel"))) {
            quantidadeVitoriasJogador++;
            return "Você venceu!";
        } else {
            quantidadeVitoriasComputador++;
            return "Você perdeu!";
        }
    }

    private void atualizarEstatisticas() {
        quantidadeJogos++;
        jogadorDao.adicionarJogador(nomeJogador);
        resultado = determinarResultado(escolhaJogador, escolhaComputador);
        if (resultado.equals("Você venceu!")) {
            jogadorDao.adicionarVitoria(nomeJogador);
        } else if (resultado.equals("Você perdeu!")) {
            jogadorDao.adicionarDerrota(nomeJogador);
        } else if (resultado.equals("Empate!")) {
            jogadorDao.adicionarEmpate(nomeJogador);
        }
    }
    public void zerarEstatisticas(){
        jogadorDao.zerarEstatisticas(nomeJogador);
        quantidadeEmpates=0;
        quantidadeJogos=0;
        quantidadeVitoriasComputador=0;
        quantidadeVitoriasJogador=0;
    }

    public String verEstatisticaGeral(){
        return "estatisticas.xhtml";
    }
    
    public String voltarResultado(){
        return "resultado.xhtml";
    }

    public void redirect(String page) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.redirect(page);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getOpcoes() {
        return OPCOES;
    }

    public List<String> getJogadores() {
        return jogadorDao.getJogadores();
    }

    public int getQuantidadeJogos() {
        return quantidadeJogos;
    }

    public int getQuantidadeVitoriasJogador() {
        return quantidadeVitoriasJogador;
    }

    public int getQuantidadeVitoriasComputador() {
        return quantidadeVitoriasComputador;
    }

    public int getQuantidadeEmpates() {
        return quantidadeEmpates;
    }

    public String getEscolhaJogador() {
        return escolhaJogador;
    }

    public void setEscolhaJogador(String escolhaJogador) {
        this.escolhaJogador = escolhaJogador;
    }

    public String getResultado() {
        return resultado;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
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

    public String getEscolhaComputador() {
        return escolhaComputador;
    }

    public void setEscolhaComputador(String escolhaComputador) {
        this.escolhaComputador = escolhaComputador;
    }

}
