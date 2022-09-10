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

public class CadastrodeProduto {
    public static void main(String[] args) {

        Categoria categoria=new Categoria("CELULARES2");
        Produto celular=new Produto("XIAOMI 2","TOP",new BigDecimal("800"), categoria);


        EntityManager em= JPAUtil.getEntityManager();
        ProdutoDao daoproduto=new ProdutoDao(em);
        CategoriaDao daocategoria=new CategoriaDao(em);
        em.getTransaction().begin();
        daocategoria.cadastrar(categoria);
        daoproduto.cadastrar(celular);
        //daocategoria.remover(categoria);
        em.getTransaction().commit();
        em.close();

    }
}
