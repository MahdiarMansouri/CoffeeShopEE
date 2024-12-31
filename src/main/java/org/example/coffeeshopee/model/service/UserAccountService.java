package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.UserAccount;
import org.example.coffeeshopee.controller.interceptor.annotation.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class UserAccountService implements Service<UserAccount, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(UserAccount userAccount) {
        entityManager.persist(userAccount);
        log.info("UserAccount saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(UserAccount userAccount) {
        if (userAccount.getId() != null) {
            entityManager.merge(userAccount);
            log.info("UserAccount edited");
        }else {
            log.error("UserAccount id is null");
        }

    }

    @Transactional
    @Loggable
    @Override
    public UserAccount remove(Long id) {
        UserAccount userAccount = entityManager.find(UserAccount.class, id);
        if (userAccount != null) {
            entityManager.remove(userAccount);
            log.info("UserAccount removed");
            return userAccount;
        }else {
            log.error("UserAccount id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public UserAccount findById(Long id) {
        UserAccount userAccount = entityManager.find(UserAccount.class, id);
        if (userAccount != null) {
            log.info("UserAccount found");
            return userAccount;
        }else {
            log.error("UserAccount id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<UserAccount> findAll() {
        Query query = entityManager.createQuery("select u from userAccountEntity u", UserAccount.class);
        if (query.getResultList().size() > 0) {
            log.info("UserAccount found");
            return (List<UserAccount>) query.getResultList();
        }else{
            log.error("UserAccounts not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public UserAccount findByUsername(String username) {
        Query query = entityManager.createQuery("select u from userAccountEntity u where u.user.username = :username", UserAccount.class);
        query.setParameter("username", username);
        if (query.getResultList().size() > 0) {
            log.info("UserAccount found");
            return (UserAccount) query.getResultList().get(0);
        }else{
            log.error("UserAccounts not found");
            return null;
        }
    }
}
