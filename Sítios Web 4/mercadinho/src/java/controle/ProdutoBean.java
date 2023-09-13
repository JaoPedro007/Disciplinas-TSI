package controle;

import dados.CodigoDuplicadoException;
import dados.Produto;
import dados.ProdutoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "prodBean")
@SessionScoped
public class ProdutoBean implements Serializable {

    Produto produto;
    boolean editando = false;
    
    public ProdutoBean() {
        produto = new Produto();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public boolean getEditando() {
        return editando;
    }
    
    public String confirmar() {
        try {
            if (editando) {
                ProdutoDAO.alterar( produto );
                editando = false;
                ProdutoDAO.desbloquear( produto );
            } else {
                ProdutoDAO.inserir( produto );
            }
            produto = new Produto();
        } catch (CodigoDuplicadoException cde) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Código já existe!"));
        }
        return null;
    }
    
    public List<Produto> getLista() {
        return ProdutoDAO.listar();
    }
    
    public String editar(Produto prod) {
        if ( ProdutoDAO.bloquear(prod) ) {
            produto = prod;
            editando = true;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                    "Produto bloqueado por outro usuario"));
        }
        return null;
    }
    
    public void cancelar() {
        editando = false;
        ProdutoDAO.desbloquear( produto );
        produto = new Produto();
    }
    
}
