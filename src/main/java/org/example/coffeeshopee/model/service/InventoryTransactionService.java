package org.example.coffeeshopee.model.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.InventoryTransaction;
import org.example.coffeeshopee.controller.interceptor.annotation.Loggable;

import java.util.List;

@ApplicationScoped
@Slf4j
public class InventoryTransactionService implements Service<InventoryTransaction, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(InventoryTransaction inventoryTransaction) {
        entityManager.persist(inventoryTransaction);
        log.info("InventoryTransaction saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(InventoryTransaction inventoryTransaction) {
        if (inventoryTransaction.getId() != null) {
            entityManager.merge(inventoryTransaction);
            log.info("InventoryTransaction edited");
        } else {
            log.error("InventoryTransaction id is null");
        }

    }

    @Transactional
    @Loggable
    @Override
    public InventoryTransaction remove(Long id) {
        InventoryTransaction inventoryTransaction = entityManager.find(InventoryTransaction.class, id);
        if (inventoryTransaction != null) {
            entityManager.remove(inventoryTransaction);
            log.info("InventoryTransaction removed");
            return inventoryTransaction;
        } else {
            log.error("InventoryTransaction id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public InventoryTransaction findById(Long id) {
        InventoryTransaction inventoryTransaction = entityManager.find(InventoryTransaction.class, id);
        if (inventoryTransaction != null) {
            log.info("InventoryTransaction found");
            return inventoryTransaction;
        } else {
            log.error("InventoryTransaction id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<InventoryTransaction> findAll() {
        Query query = entityManager.createQuery("select i from inventoryTransactionEntity i", InventoryTransaction.class);
        if (query.getResultList().size() > 0) {
            log.info("InventoryTransaction found");
            return (List<InventoryTransaction>) query.getResultList();
        } else {
            log.error("InventoryTransactions not found");
            return null;
        }
    }

}