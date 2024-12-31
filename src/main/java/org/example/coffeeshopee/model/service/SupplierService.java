package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Supplier;
import org.example.coffeeshopee.controller.interceptor.annotation.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class SupplierService implements Service<Supplier, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Supplier supplier) {
        entityManager.persist(supplier);
        log.info("Supplier saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Supplier supplier) {
        if (supplier.getId() != null) {
            entityManager.merge(supplier);
            log.info("Supplier edited");
        }else {
            log.error("Supplier id is null");
        }

    }

    @Transactional
    @Loggable
    @Override
    public Supplier remove(Long id) {
        Supplier supplier = entityManager.find(Supplier.class, id);
        if (supplier != null) {
            entityManager.remove(supplier);
            log.info("Supplier removed");
            return supplier;
        }else {
            log.error("Supplier id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Supplier findById(Long id) {
        Supplier supplier = entityManager.find(Supplier.class, id);
        if (supplier != null) {
            log.info("Supplier found");
            return supplier;
        }else {
            log.error("Supplier id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Supplier> findAll() {
        Query query = entityManager.createQuery("select s from supplierEntity s", Supplier.class);
        if (query.getResultList().size() > 0) {
            log.info("Supplier found");
            return (List<Supplier>) query.getResultList();
        }else{
            log.error("Suppliers not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Supplier findByFirstName(String name) {
        Query query = entityManager.createQuery("select s from supplierEntity s where s.person.firstName = :name", Supplier.class);
        query.setParameter("name", name);
        if (query.getResultList().size() > 0) {
            log.info("Supplier found");
            return (Supplier) query.getResultList().get(0);
        }else{
            log.error("Suppliers not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Supplier findByLastName(String name) {
        Query query = entityManager.createQuery("select s from supplierEntity s where s.person.lastName = :name", Supplier.class);
        query.setParameter("name", name);
        if (query.getResultList().size() > 0) {
            log.info("Supplier found");
            return (Supplier) query.getResultList().get(0);
        }else{
            log.error("Suppliers not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Supplier findByPhone(String phoneNumber) {
        Query query = entityManager.createQuery("select s from supplierEntity s where s.person.phone = :phoneNumber", Supplier.class);
        query.setParameter("phoneNumber", phoneNumber);
        if (query.getResultList().size() > 0) {
            log.info("Supplier found");
            return (Supplier) query.getResultList().get(0);
        }else{
            log.error("Suppliers not found");
            return null;
        }
    }
}