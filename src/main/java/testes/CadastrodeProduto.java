package testes;

import Util.JPAUtil;
import dao.CategoriaDao;
import dao.ProdutoDao;
import model.Categoria;
import model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastrodeProduto {
    public static void main(String[] args) {

        cadastrarProduto();
        Long id=1l;
        EntityManager em= JPAUtil.getEntityManager();
        ProdutoDao daoproduto=new ProdutoDao(em);
        Produto p=daoproduto.buscarPorId(id);
        daoproduto.buscarPorParametrosPorCriteria("XIAOMI",null,null);
        System.out.println(p.getPreco());

        List<Produto> todos=daoproduto.buscarporNomedaCategoria("CELULARES2");
        BigDecimal preco=daoproduto.buscarPrecodoProdutocomNome("XIAOMI 2");
        todos.forEach(p2-> System.out.println(p.getNome()));
        System.out.println(preco);
    }

    private static void cadastrarProduto() {
        Categoria categoria=new Categoria("CELULARES2");
        Produto celular=new Produto("XIAOMI 2","TOP",new BigDecimal("800"), categoria);


        EntityManager em= JPAUtil.getEntityManager();
        ProdutoDao daoproduto=new ProdutoDao(em);
        CategoriaDao daocategoria=new CategoriaDao(em);
        em.getTransaction().begin();
        daocategoria.cadastrar(categoria);
        daoproduto.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
