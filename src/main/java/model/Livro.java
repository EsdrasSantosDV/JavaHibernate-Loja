package model;

import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
public class Livro extends Produto{

    private String autor;
    private Integer numerodePaginas;


    public Livro(String nome, String descricao, BigDecimal preco, Categoria categoria, String autor, Integer numerodePaginas) {
        super(nome, descricao, preco, categoria);
        this.autor = autor;
        this.numerodePaginas = numerodePaginas;
    }
    public Livro() {

    }
    public Livro(String autor, Integer numerodePaginas) {
        this.autor = autor;
        this.numerodePaginas = numerodePaginas;
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumerodePaginas() {
        return numerodePaginas;
    }

    public void setNumerodePaginas(Integer numerodePaginas) {
        this.numerodePaginas = numerodePaginas;
    }
}
