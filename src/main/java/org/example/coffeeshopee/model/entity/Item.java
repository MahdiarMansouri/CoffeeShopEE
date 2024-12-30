package org.example.coffeeshopee.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.coffeeshopee.model.entity.enums.Category;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity(name = "itemEntity")
@Table(name = "item_tbl")
public class Item extends Base{
    @Id
    @SequenceGenerator(name = "itemSeq", sequenceName = "item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeq")
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name", length = 30)
    private String name;

    @Column(name = "item_description", length = 100)
    private String description;

    @Column(name = "item_code")
    private Long code;

    @Column(name = "item_unit_price")
    private int unitPrice;

    @Column(name = "item_category")
    private Category category;
}
