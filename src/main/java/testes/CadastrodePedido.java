package testes;

import Util.JPAUtil;
import Vo.RelatoriodeVendasVo;
import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import model.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastrodePedido {
    public static void main(String[] args) {
        PopularBanco();
        EntityManager em= JPAUtil.getEntityManager();
        ProdutoDao daoproduto=new ProdutoDao(em);
        ClienteDao daocliente=new ClienteDao(em);
        Produto produto=daoproduto.buscarPorId(1l);
        Cliente cliente=daocliente.buscarPorId(1l);


        em.getTransaction().begin();
        Pedido pedido=new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10,pedido,produto));

        PedidoDao daopedido=new PedidoDao(em);
        daopedido.cadastrar(pedido);
        em.getTransaction().commit();
        Pedido pedido2=daopedido.buscarPedidoporCLiente(1l);


        BigDecimal totalVendido=daopedido.valorTotalVendido();
        System.out.println("Valor total:"+totalVendido);
        List<RelatoriodeVendasVo> relatorio=daopedido.relatorioDeVendas();
        relatorio.forEach(System.out::println);
        em.close();
        System.out.println(pedido2.getCliente().getNome());
    }

    private static void PopularBanco() {
        Categoria categoria=new Categoria("CELULARES2");
        Produto celular=new Produto("XIAOMI 2","TOP",new BigDecimal("800"), categoria);
        Cliente cliente =new Cliente("Rodrigo","123456");

        EntityManager em= JPAUtil.getEntityManager();
        ProdutoDao daoproduto=new ProdutoDao(em);
        CategoriaDao daocategoria=new CategoriaDao(em);
        ClienteDao daocliente=new ClienteDao(em);
        em.getTransaction().begin();
        daocategoria.cadastrar(categoria);
        daoproduto.cadastrar(celular);
        daocliente.cadastrar(cliente);


        em.getTransaction().commit();
        em.close();
    }

}
