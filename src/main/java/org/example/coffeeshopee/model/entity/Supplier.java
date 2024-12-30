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

@Entity(name = "supplierEntity")
@Table(name = "supplier_tbl")
public class Supplier extends Base{
    @Id
    @SequenceGenerator(name = "supplierSeq", sequenceName = "supplier_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplierSeq")
    @Column(name = "supplier_id")
    private Long id;

    @Column(name = "supplied_material_name")
    private String suppliedMaterialName;

    @Column(name = "supplied_material_description")
    private String suppliedMaterialDescription;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;


}
