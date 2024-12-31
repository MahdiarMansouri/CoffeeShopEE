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

@Entity(name = "salaryEntity")
@Table(name = "salary_tbl")
public class Salary extends Base {
    @Id
    @SequenceGenerator(name = "salarySeq", sequenceName = "salary_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salarySeq")
    @Column(name = "salary_id")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_account_id", referencedColumnName = "user_account_id")
    private UserAccount userAccount;


    @Column(name = "salary_amount")
    private Long salaryAmount;

//    public int calculateSalary() {
//        return userAccount.getWorkDate() * 10000;
//    }
}
