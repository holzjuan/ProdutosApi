package com.holzjuan.produtosapi.controller;

import com.holzjuan.produtosapi.model.Produto;
import com.holzjuan.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController // Indica que a classe vai receber requisições REST
@RequestMapping("produtos") // Indica a url da controller
public class ProdutoController {

    // Instancia a interface que gerencia as operações no banco de dados
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        System.out.println("Produto Recebido! " + produto);

        // Cria um novo Id
        var id = UUID.randomUUID().toString();

        // Atribui o id ao valor da propriedade da classe recebida como parametro
        produto.setId(id);

        produtoRepository.save(produto);
        return produto;
    }

    // @PathVariable vincula um parâmetro a uma variavel na URL

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.isPresent() ? produto.get() : null;

        // return produtoRepository.findById(id).orElse(null);
    }

    // Exemplo de PathVariable no metodo acima
    // produtos/1234 -> retorna o produto com id igual a 1234


    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") String id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") String id, @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome) {
        return produtoRepository.findByNome(nome);
    }

    // @RequestParam é usado para extrair dados na URL de solicitação
    // Exemplo -> http://localhost:8080/produtos?nome=Pendrive
    // Retorna todos elementos com o nome "Pendrive"

}
