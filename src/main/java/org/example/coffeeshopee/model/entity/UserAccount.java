package org.example.coffeeshopee.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "userAccountEntity")
@Table(name = "user_account_tbl")
public class UserAccount extends Base{
    @Id
    @SequenceGenerator(name = "userAccountSeq", sequenceName = "user_account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userAccountSeq")
    @Column(name = "user_account_id")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "user_work_duration")
    private Duration workDuration;

    @Column(name = "user_work_date")
    private LocalDate workDate;

    @Column(name = "user_leave_duration")
    private Duration leaveDuration;

    @Column(name = "user_leave_date")
    private LocalDate leaveDate;

    @Transient
    private int Salary;


//    public int getSalary() {
//        return workDate*100000;
//    }


}
