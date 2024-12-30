package org.example.coffeeshopee.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.coffeeshopee.model.entity.enums.InventoryTransactionStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "inventoryTransactionEntity")
@Table(name = "inventory_transaction_tbl")
public class InventoryTransaction {
    @Id
    @SequenceGenerator(name = "inventoryTransactionSeq", sequenceName = "inventory_trainsaction_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventoryTransactionSeq")
    @Column(name = "inventory_transaction_id")
    private Long id;

    @Column(name = "inventory_transaction_status")
    @Enumerated(EnumType.ORDINAL)
    private InventoryTransactionStatus inventoryTransactionStatus;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @Column(name = "inventory_transaction_date_time")
    private LocalDateTime transactionDateTime;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id")
    private Inventory inventory;


}
