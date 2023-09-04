package com.neontechblck.listacomprabackend.produto;

import com.neontechblck.listacomprabackend.produto.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;
    @PostMapping
    public ResponseEntity<ProdutoRecord> addProduto(@RequestBody Produto produto){
        try{
            return ResponseEntity.ok(produtoService.addProduto(produto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<ProdutoRecord>> getAllProdutos(){
        try {
            return ResponseEntity.ok(produtoService.getAllProdutos());
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable(value ="id") String id){
        try{
           produtoService.deleteProduto(id);
           return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRecord> updateProduto(@PathVariable String id, @RequestBody Produto produto){
        try{
            return ResponseEntity.ok(produtoService.updateProduto(id, produto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
