package model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name="quantidade")
    private int quantiade;


    //ESTAMOS MAPEANDO DESSE LADO E DO OUTRO
    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    public ItemPedido(int quantiade, Pedido pedido, Produto produto) {
        this.quantiade = quantiade;
        this.pedido = pedido;
        this.precoUnitario=produto.getPreco();
        this.produto = produto;
    }

    public ItemPedido() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantiade() {
        return quantiade;
    }

    public void setQuantiade(int quantiade) {
        this.quantiade = quantiade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
