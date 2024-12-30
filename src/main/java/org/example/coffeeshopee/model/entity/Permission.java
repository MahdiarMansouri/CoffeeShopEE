package org.example.coffeeshopee.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity(name = "permissionEntity")
@Table(name = "permission_tbl")
public class Permission {
    @Id
    @SequenceGenerator(name = "permissionSeq", sequenceName = "permission_seq", allocationSize = 1  )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissionSeq")
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "permission_name", length = 30)
    @NotBlank(message = "permission name is empty!!!")
    private String permissionName;

}
