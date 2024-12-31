package org.example.coffeeshopee.model.service;


import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Order;
import org.example.coffeeshopee.model.utils.Loggable;

import java.util.List;

@RequestScoped
@Slf4j

public class OrderService implements Service<Order, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Order order) {
        entityManager.persist(order);
        log.info("Order saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Order order) {
        if (order.getId() != null) {
            entityManager.merge(order);
            log.info("Order edited");
        } else {
            log.error("Order id is null");
        }
    }

    @Transactional
    @Loggable
    @Override
    public Order remove(Long id) {
        Order order = entityManager.find(Order.class, id);
        if (order != null) {
            entityManager.remove(order);
            log.info("Order removed");
            return order;
        } else {
            log.error("Order not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Order findById(Long id) {
        Order order = entityManager.find(Order.class, id);
        if (order != null) {
            log.info("Order found: " + order);
            return order;
        } else {
            log.error("Order id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Order> findAll() {
        Query query = entityManager.createQuery("select o from orderEntity o");
        if (query.getResultList() != null) {
            log.info("Orders found");
            return query.getResultList();
        } else {
            log.error("Orders not found");
            return null;
        }
    }



    @Transactional
    @Loggable
    public Order findBySerial(String orderSerial) {
        Query query = entityManager.createQuery("select o from orderEntity o where o.serial = :orderSerial");
        query.setParameter("orderSerial", orderSerial);
        if (query.getResultList() != null) {
            log.info("Orders found");
            return (Order) query.getSingleResult();
        }else{
            log.error("Orders not found");
            return null;
        }
    }
}
