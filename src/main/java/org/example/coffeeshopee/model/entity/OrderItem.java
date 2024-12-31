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

@Entity(name = "orderItemEntity")
@Table(name = "order_item_tbl")
public class OrderItem extends Base {
    @Id
    @SequenceGenerator(name = "orderItemSeq", sequenceName = "order_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemSeq")
    @Column(name = "order_item_id")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    private Item item;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @Transient
    private int quantity;
    private int totalPrice;

    public int getTotalPrice() {
        return item.getUnitPrice() * quantity;
    }

}
