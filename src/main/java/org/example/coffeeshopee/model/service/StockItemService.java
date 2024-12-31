package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.StockItem;
import org.example.coffeeshopee.controller.interceptor.annotation.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class StockItemService implements Service<StockItem, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(StockItem stockItem) {
        entityManager.persist(stockItem);
        log.info("StockItem saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(StockItem stockItem) {
        if (stockItem.getId() != null) {
            entityManager.merge(stockItem);
            log.info("StockItem edited");
        }else {
            log.error("StockItem id is null");
        }

    }

    @Transactional
    @Loggable
    @Override
    public StockItem remove(Long id) {
        StockItem stockItem = entityManager.find(StockItem.class, id);
        if (stockItem != null) {
            entityManager.remove(stockItem);
            log.info("StockItem removed");
            return stockItem;
        }else {
            log.error("StockItem id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public StockItem findById(Long id) {
        StockItem stockItem = entityManager.find(StockItem.class, id);
        if (stockItem != null) {
            log.info("StockItem found");
            return stockItem;
        }else {
            log.error("StockItem id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<StockItem> findAll() {
        Query query = entityManager.createQuery("select s from stockItemEntity s", StockItem.class);
        if (query.getResultList().size() > 0) {
            log.info("StockItem found");
            return (List<StockItem>) query.getResultList();
        }else{
            log.error("StockItems not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public StockItem findByItemName(String name) {
        Query query = entityManager.createQuery("select s from stockItemEntity s where s.itemName = :name", StockItem.class);
        query.setParameter("name", name);
        if (query.getResultList().size() > 0) {
            log.info("StockItem found");
            return (StockItem) query.getResultList().get(0);
        }else{
            log.error("StockItems not found");
            return null;
        }
    }

}

