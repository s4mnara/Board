package br.com.dio.board.repository;

import br.com.dio.board.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    // Buscar cards por coluna do board
    List<Card> findByBoardColumnId(Long boardColumnId);
}
