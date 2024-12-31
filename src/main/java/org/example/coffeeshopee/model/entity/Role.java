package org.example.coffeeshopee.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@ToString
@Entity(name = "roleEntity")
@Table(name = "role_tbl")
public class Role extends Base {
    @Id
    @SequenceGenerator(name = "roleSeq", sequenceName = "role_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    @NotBlank(message = "roleName is empty!!!")
    private String roleName;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions")
    private Set<Permission> permissionSet = new HashSet<>();
}
