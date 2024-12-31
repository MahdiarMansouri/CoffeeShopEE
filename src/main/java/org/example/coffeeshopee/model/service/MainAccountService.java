package org.example.coffeeshopee.model.service;


import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.MainAccount;
import org.example.coffeeshopee.model.utils.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class MainAccountService implements Service<MainAccount, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(MainAccount mainAccount) {
        entityManager.persist(mainAccount);
        log.info("MainAccount saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(MainAccount mainAccount) {
        if (mainAccount.getId() != null) {
            entityManager.merge(mainAccount);
            log.info("MainAccount edited");
        }else {
            log.error("MainAccount id is null");
        }

    }

    @Transactional
    @Loggable
    @Override
    public MainAccount remove(Long id) {
        MainAccount mainAccount = entityManager.find(MainAccount.class, id);
        if (mainAccount != null) {
            entityManager.remove(mainAccount);
            log.info("MainAccount removed");
            return mainAccount;
        }else {
            log.error("MainAccount id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public MainAccount findById(Long id) {
        MainAccount mainAccount = entityManager.find(MainAccount.class, id);
        if (mainAccount != null) {
            log.info("MainAccount found");
            return mainAccount;
        }else {
            log.error("MainAccount id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<MainAccount> findAll() {
        Query query = entityManager.createQuery("select m from mainAccountEntity m", MainAccount.class);
        if (query.getResultList().size() > 0) {
            log.info("MainAccount found");
            return (List<MainAccount>) query.getResultList();
        }else{
            log.error("MainAccounts not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public MainAccount findByFirstName(String name) {
        Query query = entityManager.createQuery("select m from mainAccountEntity m where m.firstName = :name", MainAccount.class);
        query.setParameter("name", name);
        if (query.getResultList().size() > 0) {
            log.info("MainAccount found");
            return (MainAccount) query.getResultList().get(0);
        }else{
            log.error("MainAccounts not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public MainAccount findByLastName(String name) {
        Query query = entityManager.createQuery("select m from mainAccountEntity m where m.lastName = :name", MainAccount.class);
        query.setParameter("name", name);
        if (query.getResultList().size() > 0) {
            log.info("MainAccount found");
            return (MainAccount) query.getResultList().get(0);
        }else{
            log.error("MainAccounts not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public MainAccount findByPhone(String phoneNumber) {
        Query query = entityManager.createQuery("select m from mainAccountEntity m where m.phone = :phoneNumber", MainAccount.class);
        query.setParameter("phoneNumber", phoneNumber);
        if (query.getResultList().size() > 0) {
            log.info("MainAccount found");
            return (MainAccount) query.getResultList().get(0);
        }else{
            log.error("MainAccounts not found");
            return null;
        }
    }
}
