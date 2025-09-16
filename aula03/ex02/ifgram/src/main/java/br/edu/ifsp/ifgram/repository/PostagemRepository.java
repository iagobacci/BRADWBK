package br.edu.ifsp.ifgram.repository;

import br.edu.ifsp.ifgram.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
}
