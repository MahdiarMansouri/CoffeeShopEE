package org.example.coffeeshopee.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@ToString
@Entity(name = "permissionEntity")
@Table(name = "permission_tbl")
public class Permission extends Base {
    @Id
    @SequenceGenerator(name = "permissionSeq", sequenceName = "permission_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissionSeq")
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "permission_name", length = 30)
    @NotBlank(message = "permission name is empty!!!")
    private String permissionName;
}
