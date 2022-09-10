package dao;

import model.Categoria;
import model.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto)
    {
        this.em.persist(produto);
    }
    public void atualizar(Produto produto){
        this.em.merge(produto);
    }

    public void remover(Produto produto)
    {
        //VOLTAR PRO MANAGED
        produto=em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(Long id)
    {
        //RETORNA O PRODUTO
        return em.find(Produto.class,id);
    }

    public List<Produto> buscarTodos()
    {
        String jpql="SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }


    public List<Produto> buscarporNome(String nome)
    {
        String jpql="SELECT p FROM Produto p WHERE p.nome=:nome";

        return em.createQuery(jpql, Produto.class).setParameter("nome",nome).getResultList();
    }

    public BigDecimal buscarPrecodoProdutocomNome(String nome)
    {
        String jpql="SELECT p.preco FROM Produto p WHERE p.nome=:nome";
        //PRA UM UNICO RESULTADO
        return em.createQuery(jpql, BigDecimal.class).setParameter("nome",nome).getSingleResult();
    }

    public List<Produto> buscarporNomedaCategoria(String nome)
    {
        String jpql="SELECT p FROM Produto p WHERE p.categoria.nome=:nome";

        return em.createQuery(jpql, Produto.class).setParameter("nome",nome).getResultList();
    }
}
