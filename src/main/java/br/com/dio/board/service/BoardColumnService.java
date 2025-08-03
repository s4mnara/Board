package br.com.dio.board.service;

import br.com.dio.board.model.Board;
import br.com.dio.board.model.BoardColumn;
import br.com.dio.board.repository.BoardColumnRepository;
import br.com.dio.board.repository.BoardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BoardColumnService {

    private final BoardColumnRepository boardColumnRepository;
    private final BoardRepository boardRepository; // <-- Adiciona o repositório

    public BoardColumnService(BoardColumnRepository boardColumnRepository, BoardRepository boardRepository) {
        this.boardColumnRepository = boardColumnRepository;
        this.boardRepository = boardRepository;
    }

    public List<BoardColumn> findAll() {
        return boardColumnRepository.findAll();
    }

    public BoardColumn save(BoardColumn boardColumn) {
        return boardColumnRepository.save(boardColumn);
    }

    public BoardColumn createColumnForBoard(Long boardId, BoardColumn column) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board não encontrado"));
        column.setBoard(board);
        return boardColumnRepository.save(column);
    }

    public Optional<BoardColumn> findById(Long id) {
        return boardColumnRepository.findById(id);
    }

    public List<BoardColumn> findByBoardId(Long boardId) {
        return boardColumnRepository.findByBoardId(boardId);
    }

    public void deleteById(Long id) {
        boardColumnRepository.deleteById(id);
    }


    
}
