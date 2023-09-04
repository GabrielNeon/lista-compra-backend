package com.neontechblck.listacomprabackend.produto;

public record ProdutoRecord(
        String id,
        String nomeProduto,
        Double quantidade,
        Double preco,
        String dataValidade) {
}
