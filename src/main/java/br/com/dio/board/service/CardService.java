package br.com.dio.board.service;

import br.com.dio.board.model.Card;
import br.com.dio.board.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> findByBoardColumnId(Long boardColumnId) {
        return cardRepository.findByBoardColumnId(boardColumnId);
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }
}
