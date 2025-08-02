package br.com.dio.board.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private boolean blocked;

    private String blockReason;

    private int blocksAmount;

    // Cada card pertence a uma coluna
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_column_id")
    private BoardColumn boardColumn;
}
