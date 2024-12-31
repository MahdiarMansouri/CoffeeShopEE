package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.OrderItem;
import org.example.coffeeshopee.controller.interceptor.annotation.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class OrderItemService implements Service<OrderItem, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Override
    public void save(OrderItem orderItem) {
        entityManager.persist(orderItem);
        log.info("OrderItem saved");
    }

    @Override
    public void edit(OrderItem orderItem) {
        if (orderItem.getId() != null) {
            entityManager.merge(orderItem);
            log.info("OrderItem updated");
        } else {
            log.error("OrderItem id is null");
        }
    }

    @Transactional
    @Loggable
    @Override
    public OrderItem remove(Long id) {
        OrderItem orderItem = entityManager.find(OrderItem.class, id);
        if (orderItem != null) {
            entityManager.remove(orderItem);
            log.info("OrderItem removed");
            return orderItem;
        } else {
            log.error("OrderItem not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public OrderItem findById(Long id) {
        OrderItem orderItem = entityManager.find(OrderItem.class, id);
        if (orderItem != null) {
            log.info("OrderItem found: " + orderItem);
            return orderItem;
        } else {
            log.error("OrderItem id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<OrderItem> findAll() {
        Query query = entityManager.createQuery("select oi from orderItemEntity  oi");
        if (query.getResultList() != null) {
            log.info("OrderItems found");
            return query.getResultList();
        } else {
            log.error("OrderItems not found");
            return null;
        }
    }
}
