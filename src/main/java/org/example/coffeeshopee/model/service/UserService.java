package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Person;
import org.example.coffeeshopee.model.entity.Role;
import org.example.coffeeshopee.model.entity.User;
import org.example.coffeeshopee.model.utils.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class UserService implements Service<User, Long>{
    @Inject
    private RoleService roleService;

    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(User user) {
        entityManager.persist(user);
        log.info("User saved: " + user);
    }

    @Transactional
    @Loggable
    @Override
    public void edit(User user) {
        if (user.getId() != null) {
            entityManager.merge(user);
            log.info("User edited: " + user);
        } else {
            log.error("User edited is null");
        }
    }

    @Transactional
    @Loggable
    @Override
    public User remove(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            log.info("User deleted: " + user);
            return user;
        } else {
            log.error("User deleted is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public User findById(Long id) {
        if (id != null) {
            log.info("User found: " + id);
            return entityManager.find(User.class, id);
        }else {
            log.error("User id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<User> findAll() {
        Query query = entityManager.createQuery("SELECT u FROM userEntity u", User.class);
        if (query.getResultList() != null) {
            log.info("Users found ");
            return query.getResultList();
        } else {
            log.error("Users found is null");
            return null;
        }
    }

    public User findByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u FROM userEntity u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        if (query.getResultList() != null) {
            log.info("Users found ");
            return (User) query.getSingleResult();
        } else {
            log.error("Users found is null");
            return null;
        }
    }

    public Person findPersonByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u FROM userEntity u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        if (query.getResultList() != null) {
            log.info("Users found ");
            User user = (User) query.getSingleResult();
            return user.getPerson();
        } else {
            log.error("Users found is null");
            return null;
        }
    }

    public User findByUsernameAndPassword(String username, String password) {
        Query query = entityManager.createQuery("select u from userEntity u where u.username = :username and u.password = : password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        if (query.getResultList() != null) {
            log.info("Users found ");
            return (User) query.getSingleResult();
        }else {
            log.error("Users found is null");
            return null;
        }
    }

    public List<User> findByRoleName(String roleName) {
        Query query = entityManager.createQuery("select u from userEntity u cross join roleEntity r where r.roleName=:roleName", User.class);
        query.setParameter("roleName", roleName);
        if (query.getResultList() != null) {
            log.info("Users found ");
            return query.getResultList();
        }else{
            log.error("Users found is null");
            return null;
        }

    }
}
