package org.example.coffeeshopee.model.entity;

import jakarta.persistence.*;
import jakarta.security.enterprise.authentication.mechanism.http.openid.ClaimsDefinition;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity(name = "userEntity")
@Table(name = "user_tbl")
public class User extends Base {
    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", length = 30, unique = true, nullable = false)
    @NotBlank(message = "username is empty!!!")
    private String username;

    @Column(name = "password", length = 30, unique = true, nullable = false)
    @NotBlank(message = "password is empty!!!")
    private String password;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name="person_users", foreignKey = @ForeignKey(name = "person_fk"))
    private Person person;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "role_name"))
    private List<Role> roleList;
}
