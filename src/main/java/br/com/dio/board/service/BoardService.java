package br.com.dio.board.service;

import br.com.dio.board.model.Board;
import br.com.dio.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // Construtor para injeção de dependência automática do Spring
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }


}
