package org.example.coffeeshopee.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "inventoryEntity")
@Table(name = "inventory_tbl")
public class Inventory extends Base {
    @Id
    @SequenceGenerator(name = "inventorySeq", sequenceName = "inventory_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventorySeq")
    @Column(name = "inventory_id")
    private Long id;

    @Column(name = "inventory_title")
    private String title;

    @Column(name = "inventory_description")
    private String description;

    @Column(name = "inventory_address")
    private String address;

    @Column(name = "inventory_phone")
    private String phone;
}
