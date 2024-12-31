package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Payment;
import org.example.coffeeshopee.model.utils.Loggable;

import java.util.List;

@ApplicationScoped
@Slf4j
@Loggable
public class PaymentService implements Service<Payment, Long>{
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Payment payment) {
        entityManager.persist(payment);
        log.info("Payment saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Payment payment) {
        if (payment.getId() != null) {
            entityManager.merge(payment);
            log.info("Payment edited");
        } else {
            log.error("Payment id is null");
        }
    }

    @Transactional
    @Loggable
    @Override
    public Payment remove(Long id) {
        Payment payment = entityManager.find(Payment.class, id);
        if (payment != null) {
            entityManager.remove(payment);
            log.info("Payment removed");
            return payment;
        } else {
            log.error("Payment not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Payment findById(Long id) {
        Payment payment = entityManager.find(Payment.class, id);
        if (payment != null) {
            log.info("Payment found: " + payment);
            return payment;
        } else {
            log.error("Payment id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Payment> findAll() {
        Query query = entityManager.createQuery("select p from paymentEntity p");
        if (query.getResultList() != null) {
            log.info("Payments found");
            return query.getResultList();
        } else {
            log.error("Payments not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Payment findByCode(String paymentCode) {
        Query query = entityManager.createQuery("select p from paymentEntity p where p.code = :paymentCode");
        query.setParameter("paymentCode", paymentCode);
        if (query.getResultList() != null) {
            log.info("Payments found");
            return (Payment) query.getSingleResult();
        }else{
            log.error("Payments not found");
            return null;
        }
    }
}
