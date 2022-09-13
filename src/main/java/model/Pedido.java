package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="valor_total")
    private BigDecimal valorTotal=BigDecimal.ZERO;

    @Column(name="data")
    private LocalDate data=LocalDate.now();

    //COLOCAR O FETCH PRA SER LAZY PORQUE POR PADRÃO E EAGER NO TO ONE
    //BASCIAMENTE E O SEGUINTE,QUANDO E EAGER ELE FAZ A CONSULTA PUXANDO O REALCIONAMENTO
    //LAZY SO VAI PUXAR O RELACIONAMENTO CASO NECESSARIO
    @ManyToOne(fetch=FetchType.LAZY)
    private Cliente cliente;

    //ESSE MAPPEDBY QUE DIZER QUE JA ESTAMOS MAPEANDO PELO OUTRO LADO QUE SERIA DO ITEM
    //ESSE CASCADE EFEITO CASCADA TUDO QUE ACONTECER NO PEDIDO ACONTECE NO ITEM PEDIDO
    @OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
    private List<ItemPedido> itens=new ArrayList<>();

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido() {

    }

    //METODO UTILITARIO PRA ADICIONAR O ITEM NO PEDIDO E SETAR OS DOIS LADOS DO RELACIONAMENTO
    public void adicionarItem(ItemPedido item)
    {
        item.setPedido(this);

        this.itens.add(item);
        //AUMENTANDO O VALOR TOTAL
        this.valorTotal=this.valorTotal.add(item.getValor());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }



    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
