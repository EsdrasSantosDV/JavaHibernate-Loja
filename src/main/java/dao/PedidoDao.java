package dao;

import Vo.RelatoriodeVendasVo;
import model.Pedido;
import model.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {
    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido Pedido)
    {
        this.em.persist(Pedido);
    }
    public void atualizar(Pedido Pedido){

        this.em.merge(Pedido);
    }

    public void remover(Pedido Pedido)
    {
        //VOLTAR PRO MANAGED
        Pedido=em.merge(Pedido);
        this.em.remove(Pedido);
    }

    public Pedido buscarPorId(Long id)
    {
        //RETORNA O Pedido
        return em.find(Pedido.class,id);
    }

    public List<Pedido> buscarTodos()
    {
        String jpql="SELECT p FROM Pedido p";
        return em.createQuery(jpql, Pedido.class).getResultList();
    }

    public BigDecimal valorTotalVendido()
    {
        String jpql="SELECT SUM(p.valorTotal) FROM Pedido p";
        return  em.createQuery(jpql,BigDecimal.class)
                .getSingleResult();
    }

//    public List<RelatoriodeVendasVo> relatoriodeVendas()
//    {
//        //TEM QUE MANDAR O PACKAGE NA QUERRY NO NEW
//        String jpql="SELECT new Vo.RelatoriodeVendasVo(" +
//                "produto.nome, " +
//                "SUM(item.quantidade)," +
//                "MAX(pedido.data)) " +
//                "FROM Pedido pedido " +
//                "JOIN  pedido.itens item " +
//                "JOIN item.produto produto " +
//                "GROUP BY produto.nome " +
//                "ORDER BY item.quantidade DESC ";
//        return em.createQuery(jpql,RelatoriodeVendasVo.class).getResultList();
//    }

    public List<RelatoriodeVendasVo> relatorioDeVendas() {
        String jpql = "SELECT new Vo.RelatoriodeVendasVo("
                + "produto.nome, "
                + "SUM(item.quantidade), "
                + "MAX(pedido.data)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.itens item "
                + "JOIN item.produto produto "
                + "GROUP BY produto.nome "
                + "ORDER BY item.quantidade DESC";
        return em.createQuery(jpql, RelatoriodeVendasVo.class)
                .getResultList();
    }
}
