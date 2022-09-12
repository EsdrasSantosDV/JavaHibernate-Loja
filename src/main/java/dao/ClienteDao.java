package dao;

import model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente Cliente)
    {
        this.em.persist(Cliente);
    }
    public void atualizar(Cliente Cliente){

        this.em.merge(Cliente);
    }

    public void remover(Cliente Cliente)
    {
        //VOLTAR PRO MANAGED
        Cliente=em.merge(Cliente);
        this.em.remove(Cliente);
    }

    public Cliente buscarPorId(Long id)
    {
        //RETORNA O Cliente
        return em.find(Cliente.class,id);
    }

    public List<Cliente> buscarTodos()
    {
        String jpql="SELECT p FROM Cliente p";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }
}
