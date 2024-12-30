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

@Entity(name = "accountingEntity")
@Table(name = "accounting_tbl")
public class Accounting extends Base{
    @Id
    @SequenceGenerator(name = "accountingSeq", sequenceName = "accounting_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountingSeq")
    @Column(name = "accounting_id")
    private Long id;

    @Column(name = "total_revenue")
    private Long totalRevenue;

    @Column(name = "total_expenses")
    private Long totalExpenses;


//    public Long calculateProfitLoss() {}
//    public String generateFinancialReport() {}
}
