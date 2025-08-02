package br.com.dio.board.repository;

import br.com.dio.board.model.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {

    List<BoardColumn> findByBoardId(Long boardId);
}
