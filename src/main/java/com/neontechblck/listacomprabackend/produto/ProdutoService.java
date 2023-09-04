package com.neontechblck.listacomprabackend.produto;

import com.neontechblck.listacomprabackend.produto.entity.Produto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoRecord addProduto(Produto produto) {
        try {
            log.debug("inserindo produto na lista: {}", produto.getNomeProduto());
            return getProdutoRecord(produto);
        } catch ( Exception ex){
            log.error("Erro ao inserir produto ", ex);
            throw new RuntimeException(ex);
        }
    }

    private ProdutoRecord getProdutoRecord(Produto produto) {
        return getRecord(produtoRepository.save(produto));
    }

    public List<ProdutoRecord> getAllProdutos(){
        try{
            log.info("Buscando Lista Completa");
            List<ProdutoRecord> produtoRecordList = new ArrayList<>();
            produtoRepository.findAll().forEach(produto ->  produtoRecordList.add(getRecord(produto)));
            return produtoRecordList;
        } catch ( Exception ex){
            log.error("Erro ao buscar produtos ", ex);
            throw new RuntimeException(ex);
        }
    }
    public void deleteProduto(String id){
        try {
            log.debug("deletando produto na lista: {}", id);
            produtoRepository.deleteById(id);
        }catch (Exception ex  ){
            throw new RuntimeException(ex);
        }
    }

    public ProdutoRecord updateProduto(@PathVariable String id, @RequestBody Produto produto){
        try{
            Produto produtoPerstido = produtoRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
            return getProdutoRecord(produtoPerstido.of(produto));
        }catch (Exception ex  ){
            throw new RuntimeException(ex);
        }
    }

    private static ProdutoRecord getRecord(Produto produto){
        return new ProdutoRecord(produto.getId(),
                produto.getNomeProduto(),
                produto.getQuantidade(),
                produto.getPreco(),
                converterData(produto.getDataValidade()));
    }

    private static String converterData(Date data){
        SimpleDateFormat outPutDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String outPutDateString = outPutDateFormat.format(data);
        return outPutDateString;
    }
}

