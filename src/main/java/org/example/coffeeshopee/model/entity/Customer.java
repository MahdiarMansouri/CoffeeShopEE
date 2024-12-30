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


@Entity(name = "customerEntity")
@Table(name = "customer_tbl")
public class Customer extends Base{
    @Id
    @SequenceGenerator(name = "customerSeq", sequenceName = "customer_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeq")
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_first_name", length = 30)
    private String firstName;

    @Column(name = "customer_last_name", length = 30)
    private String lastName;

    @Column(name = "customer_phone", length = 13)
    private String phone;

}
