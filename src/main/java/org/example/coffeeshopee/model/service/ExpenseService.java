package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Expense;
import org.example.coffeeshopee.model.utils.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class ExpenseService implements Service<Expense, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Expense expense) {
        entityManager.persist(expense);
        log.info("Expense saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Expense expense) {
        if (expense.getId() != null) {
            entityManager.merge(expense);
            log.info("Expense edited");
        }else {
            log.error("Expense id is null");
        }

    }

    @Transactional
    @Loggable
    @Override
    public Expense remove(Long id) {
        Expense expense = entityManager.find(Expense.class, id);
        if (expense != null) {
            entityManager.remove(expense);
            log.info("Expense removed");
            return expense;
        }else {
            log.error("Expense id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Expense findById(Long id) {
        Expense expense = entityManager.find(Expense.class, id);
        if (expense != null) {
            log.info("Expense found");
            return expense;
        }else {
            log.error("Expense id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Expense> findAll() {
        Query query = entityManager.createQuery("select e from expenseEntity e", Expense.class);
        if (query.getResultList().size() > 0) {
            log.info("Expense found");
            return (List<Expense>) query.getResultList();
        }else{
            log.error("Expenses not found");
            return null;
        }
    }

}
