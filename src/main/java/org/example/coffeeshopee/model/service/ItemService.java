package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Customer;
import org.example.coffeeshopee.model.entity.Item;
import org.example.coffeeshopee.model.entity.Item;
import org.example.coffeeshopee.model.utils.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class ItemService implements Service<Item, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Item item) {
        entityManager.persist(item);
        log.info("Item saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Item item) {
        if (item.getId() != null) {
            entityManager.merge(item);
            log.info("Item edited");
        } else {
            log.error("Item id is null");
        }
    }

    @Transactional
    @Loggable
    @Override
    public Item remove(Long id) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            entityManager.remove(item);
            log.info("Item removed");
            return item;
        } else {
            log.error("Item not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Item findById(Long id) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            log.info("Item found: " + item);
            return item;
        } else {
            log.error("Item id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Item> findAll() {
        Query query = entityManager.createQuery("select i from itemEntity i");
        if (query.getResultList() != null) {
            log.info("items found");
            return query.getResultList();
        } else {
            log.error("items not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Item findByName(String itemName) {
        Query query = entityManager.createQuery("select i from itemEntity i where i.name = :itemName");
        query.setParameter("itemName", itemName);
        if (query.getResultList() != null) {
            log.info("items found");
            return (Item) query.getSingleResult();
        } else {
            log.error("items not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Item findByCode(String itemCode) {
        Query query = entityManager.createQuery("select i from itemEntity i where i.code = :itemCode");
        query.setParameter("itemCode", itemCode);
        if (query.getResultList() != null) {
            log.info("items found");
            return (Item) query.getSingleResult();
        }else{
            log.error("items not found");
            return null;
        }
    }
}
