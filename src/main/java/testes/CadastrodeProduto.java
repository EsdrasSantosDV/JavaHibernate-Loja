package testes;

import Util.JPAUtil;
import dao.ProdutoDao;
import model.Categoria;
import model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastrodeProduto {
    public static void main(String[] args) {
        Produto celular=new Produto("XIAOMI","TOP",new BigDecimal("800"), Categoria.CELULARES);


        EntityManager em= JPAUtil.getEntityManager();
        ProdutoDao dao=new ProdutoDao(em);

        em.getTransaction().begin();
        dao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();

    }
}
