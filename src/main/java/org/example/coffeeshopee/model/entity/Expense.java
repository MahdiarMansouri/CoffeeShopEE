package org.example.coffeeshopee.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity(name = "expenseEntity")
@Table(name = "expense_tbl")
public class Expense extends Base{
    @Id
    @SequenceGenerator(name = "expenseSeq", sequenceName = "expense_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expenseSeq")
    @Column(name = "expense_id")
    private Long id;

    @Column(name = "expense_description", length = 200)
    private String description;

    @Column(name = "expense_amount")
    private int amount;
}
