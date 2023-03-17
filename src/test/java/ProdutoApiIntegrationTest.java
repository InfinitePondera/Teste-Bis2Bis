import com.example.testebis2bis.domain.ProdutoDAO;
import com.example.testebis2bis.entity.Produto;
import com.example.testebis2bis.service.ProdutoService;
import org.junit.Test;


import static org.junit.Assert.*;
import java.util.List;

public class ProdutoApiIntegrationTest {

    private ProdutoService produtoService;

    public ProdutoService initService() {
        ProdutoDAO pDAO = new ProdutoDAO();
        ProdutoService pService = new ProdutoService();
        pService.setProdutoDAO(pDAO);

        return pService;
    }

    @Test
    public void getProdutos() throws Exception {
        produtoService = initService();
        List<Produto> produtosTest = produtoService.listarProdutos();
        assertTrue(!produtosTest.isEmpty());
        assertTrue(produtosTest.stream().anyMatch(p -> p.getNome().equals("Gubee E-Commerce")));
    }

    @Test
    public void getProdutosFiltrados() throws Exception {
        produtoService = initService();
        List<Produto> produtosTest = produtoService.filterProduto("Java", "Mobile");
        assertTrue(!produtosTest.isEmpty());
        assertTrue(produtosTest.stream().anyMatch(p -> p.getNome().equals("Gubee Portal Mobile")));
    }

    @Test
    public void getProdutosFiltrados404() throws Exception {
        produtoService = initService();
        List<Produto> produtosTest = produtoService.filterProduto("Assembly, C", "IBM-PC");
        assertTrue(produtosTest.isEmpty());
    }
}
