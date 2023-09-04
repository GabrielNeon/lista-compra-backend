package com.neontechblck.listacomprabackend.produto.entity;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("produto")
public class Produto {
    @Id
    private String id;
    private String nomeProduto;
    private Double quantidade;
    private Double preco;
    private Date dataValidade;


    public Produto of(Produto produto){
        Produto prod = new Produto();
        if(Strings.isNotEmpty(this.id)){
            prod.setId(this.id);
        }
        prod.setNomeProduto(produto.getNomeProduto());
        prod.setQuantidade(produto.getQuantidade());
        prod.setPreco(produto.getPreco());
        prod.setDataValidade(produto.getDataValidade());
        return prod;

    }

}
