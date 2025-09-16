package br.edu.ifsp.ifgram.controller;

import br.edu.ifsp.ifgram.model.Postagem;
import br.edu.ifsp.ifgram.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemRepository repository;

    @GetMapping
    public List<Postagem> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscarPorId(@PathVariable Long id) {
        Optional<Postagem> postagem = repository.findById(id);
        return postagem.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Postagem> criar(@RequestBody Postagem postagem) {
        Postagem salva = repository.save(postagem);
        return ResponseEntity.status(201).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postagem> atualizar(@PathVariable Long id, @RequestBody Postagem novaPostagem) {
        return repository.findById(id)
                .map(postagemExistente -> {
                    postagemExistente.setTitulo(novaPostagem.getTitulo());
                    postagemExistente.setConteudo(novaPostagem.getConteudo());
                    postagemExistente.setDataCriacao(novaPostagem.getDataCriacao());
                    Postagem atualizada = repository.save(postagemExistente);
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Postagem> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(postagem -> {
                    repository.delete(postagem);
                    return ResponseEntity.ok(postagem);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
