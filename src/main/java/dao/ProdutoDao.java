package dao;

import model.Categoria;
import model.Produto;

import javax.persistence.EntityManager;

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


}
