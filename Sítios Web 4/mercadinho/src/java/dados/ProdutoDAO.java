package dados;

import java.util.LinkedList;
import java.util.List;


public class ProdutoDAO {
    private static List<Produto> produtos = new LinkedList<>();
    private static List<Produto> bloqueados = new LinkedList<>();
    
    public static void inserir(Produto p) throws CodigoDuplicadoException {
        if (!produtos.contains(p)) {
            produtos.add( p );
        } else {
            throw new CodigoDuplicadoException();
        }
    }
    
    public static void remover(Produto p) {
        produtos.remove( p );
    }
    
    public static void alterar(Produto p) {
        int pos = produtos.indexOf(p);
        if (pos >= 0)  {
            produtos.set(pos, p); 
        }
    }
    
    public static List<Produto> listar() {
        return produtos;
    }
    
    public static boolean bloquear(Produto p) {
        if ( bloqueados.contains(p) ) {
            return false;
        } 
        Produto prod = produtos.get( produtos.indexOf(p) );
        bloqueados.add( prod );        
        return true;        
    }
    
    public static boolean desbloquear( Produto p ) {
        return bloqueados.remove( p );
    }
}
