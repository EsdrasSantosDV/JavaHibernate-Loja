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

    //PRA EVITAR TOMAR LAZY EXCEPTION
    //PQ VC TA PXUANDO OS DADOS DE UM RELACIONAMENTO DO PEDIDO SO QUE NO CASO O ENTITY MANAGER FOI FECHADO
    //PRA EVITAR ISSO FAZEMOS O SEGUINTE

    //JOIN FETCH QUER DIZER JA TU TA BUSCANDO UM LAZY E TRANSFORMANDO ELE NUM EAGER
    public Pedido buscarPedidoporCLiente(Long id)
    {
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id=:id", Pedido.class).setParameter("id",id).getSingleResult();
    }

}
