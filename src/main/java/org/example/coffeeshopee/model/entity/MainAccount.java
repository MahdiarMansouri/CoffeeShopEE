package org.example.coffeeshopee.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "mainAccountEntity")
@Table(name = "main_account_tbl")
public class MainAccount extends Base{
    @Id
    @SequenceGenerator(name = "mainAccountSeq", sequenceName = "main_account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mainAccountSeq")
    @Column(name = "main_account_id")
    private Long id;

    @Column(name = "total_revenue")
    private Long totalRevenue;

    @Column(name = "total_expenses")
    private Long totalExpenses;


//    public Long calculateProfitLoss() {}
//    public String generateFinancialReport() {}
}
