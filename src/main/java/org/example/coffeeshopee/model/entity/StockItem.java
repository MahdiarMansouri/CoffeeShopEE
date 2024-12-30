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

@Entity(name = "stockItemEntity")
@Table(name = "stock_item_tbl")
public class StockItem extends Base {
    @Id
    @SequenceGenerator(name = "stockItemSeq", sequenceName = "stock_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stockItemSeq")
    @Column(name = "stock_item_id")
    private Long id;

    @Column(name = "stock_item_name")
    private String itemName;

    @Column(name = "stock_item_decription")
    private String itemDescription;

    @Column(name = "stock_item_unit_price")
    private int unitPrice;

    @Column(name = "stock_item_quantity")
    private int quantity;

}
