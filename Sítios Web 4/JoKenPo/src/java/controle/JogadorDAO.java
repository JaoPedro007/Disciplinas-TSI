import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("jogadorDao")
@ApplicationScoped
public class JogadorDAO implements Serializable {

    private List<String> jogadores = new ArrayList<>();

    public List<String> getJogadores() {
        return jogadores;
    }

    public void adicionarJogador(String nome) {
        jogadores.add(nome);
    }
}
