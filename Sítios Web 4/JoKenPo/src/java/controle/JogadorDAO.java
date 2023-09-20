
import controle.EstatisticasJogador;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("jogadorDao")
@ApplicationScoped
public class JogadorDAO implements Serializable {

    private Map<String, EstatisticasJogador> estatisticas = new HashMap<>();

    public List<String> getJogadores() {
        return new ArrayList<>(estatisticas.keySet());
    }

    public void adicionarJogador(String nome) {
        if (!estatisticas.containsKey(nome)) { // verifica se o nome já existe no mapa
            estatisticas.put(nome, new EstatisticasJogador());
        }
    }

    public void adicionarVitoria(String nome) {
        EstatisticasJogador estatisticasJogador = estatisticas.get(nome);
        if (estatisticasJogador != null) {
            estatisticasJogador.incrementarVitorias();
        }
    }

    public void adicionarEmpate(String nome) {
        EstatisticasJogador estatisticasJogador = estatisticas.get(nome);
        if (estatisticasJogador != null) {
            estatisticasJogador.incrementarEmpates();
        }
    }

    public void adicionarDerrota(String nome) {
        EstatisticasJogador estatisticasJogador = estatisticas.get(nome);
        if (estatisticasJogador != null) {
            estatisticasJogador.incrementarDerrotas();
        }
    }

<<<<<<< HEAD
    public void zerarEstatisticas(String nome) {
        EstatisticasJogador estatisticasJogador = estatisticas.get(nome);
        if (estatisticasJogador != null) {
            estatisticasJogador.zerarEstatisticas();
        }
    }

=======
>>>>>>> 6bbbda572160a6fc7044ac87d5010fe5024c78e4
    public int getVitorias(String nome) {
        EstatisticasJogador estatisticasJogador = estatisticas.get(nome);
        return estatisticasJogador != null ? estatisticasJogador.getVitorias() : 0;
    }

    public int getEmpates(String nome) {
        EstatisticasJogador estatisticasJogador = estatisticas.get(nome);
        return estatisticasJogador != null ? estatisticasJogador.getEmpates() : 0;
    }

    public int getDerrotas(String nome) {
        EstatisticasJogador estatisticasJogador = estatisticas.get(nome);
        return estatisticasJogador != null ? estatisticasJogador.getDerrotas() : 0;
    }

}
