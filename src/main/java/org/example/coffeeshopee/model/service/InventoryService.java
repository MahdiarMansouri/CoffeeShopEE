package org.example.coffeeshopee.model.service;


import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Inventory;
import org.example.coffeeshopee.controller.interceptor.annotation.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class InventoryService implements Service<Inventory, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Inventory inventory) {
        entityManager.persist(inventory);
        log.info("Inventory saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Inventory inventory) {
        if (inventory.getId() != null) {
            entityManager.merge(inventory);
            log.info("Inventory edited");
        }else {
            log.error("Inventory id is null");
        }

    }

    @Transactional
    @Loggable
    @Override
    public Inventory remove(Long id) {
        Inventory inventory = entityManager.find(Inventory.class, id);
        if (inventory != null) {
            entityManager.remove(inventory);
            log.info("Inventory removed");
            return inventory;
        }else {
            log.error("Inventory id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Inventory findById(Long id) {
        Inventory inventory = entityManager.find(Inventory.class, id);
        if (inventory != null) {
            log.info("Inventory found");
            return inventory;
        }else {
            log.error("Inventory id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Inventory> findAll() {
        Query query = entityManager.createQuery("select i from inventoryEntity i", Inventory.class);
        if (query.getResultList().size() > 0) {
            log.info("Inventory found");
            return (List<Inventory>) query.getResultList();
        }else{
            log.error("Inventorys not found");
            return null;
        }
    }


    @Transactional
    @Loggable
    public Inventory findByPhone(String phoneNumber) {
        Query query = entityManager.createQuery("select i from inventoryEntity i where i.phone = :phoneNumber", Inventory.class);
        query.setParameter("phoneNumber", phoneNumber);
        if (query.getResultList().size() > 0) {
            log.info("Inventory found");
            return (Inventory) query.getResultList().get(0);
        }else{
            log.error("Inventorys not found");
            return null;
        }
    }
}