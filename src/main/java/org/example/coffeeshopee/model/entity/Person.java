package org.example.coffeeshopee.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.coffeeshopee.model.entity.enums.Gender;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity(name = "personEntity")
@Table(name = "person_tbl")
public class Person extends Base {

    @Id
    @SequenceGenerator(name= "personSeq", sequenceName = "person_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    @Column(name = "person_id")
    private Long id;

    @Column(name = "person_firstName", length = 30)
    private String firstName;

    @Column(name = "person_lastName", length = 30)
    private String lastName;

    @Column(name="person_nationalCode", length = 10)
    private String nationalCode;

    @Column(name="person_birthDate")
    private LocalDate birthDate;

    @Column(name="person_email", length = 100)
    private String email;

    @Column(name = "person_phone", length = 13)
    private String phone;

    @Column(name = "person_address", length = 200)
    private String address;

    @Column(name = "person_gender", length = 6)
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name="person_users", foreignKey = @ForeignKey(name = "user_fk"))
    private User user;
}
