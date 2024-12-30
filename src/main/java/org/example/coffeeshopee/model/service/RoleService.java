package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Role;
import org.example.coffeeshopee.model.utils.Loggable;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
@Slf4j
public class RoleService implements Service<Role, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;


    @Transactional
    @Loggable
    @Override
    public void save(Role role) {
        entityManager.persist(role);
        log.info("Role saved: " + role);
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Role role) {
        if (role.getId() != null) {
            entityManager.merge(role);
            log.info("Role edited: " + role);
        } else {
            log.error("Role edited is null");
        }
    }

    @Transactional
    @Loggable
    @Override
    public Role remove(Long id) {
        Role role = entityManager.find(Role.class, id);
        if (role != null) {
            entityManager.remove(role);
            log.info("Role removed: " + role);
            return role;
        } else {
            log.error("Role removed is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Role findById(Long id) {
        if (id != null) {
            log.info("Role found: " + id);
            return entityManager.find(Role.class, id);
        } else {
            log.error("Role id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Role> findAll() {
        Query query = entityManager.createQuery("SELECT r FROM roleEntity r", Role.class);
        if (query.getResultList().size() > 0) {
            log.info("Roles found");
            return query.getResultList();
        } else {
            log.error("No roles found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Role findByRoleName(String roleName) {
        Query query = entityManager.createQuery("SELECT r FROM roleEntity r WHERE r.roleName = :roleName", Role.class);
        if (query.getResultList().size() > 0) {
            log.info("Roles found");
            return (Role) query.getSingleResult();
        } else{
            log.error("No roles found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public List<Role> findByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u.roleList FROM userEntity u WHERE u.username = :username", Role.class);
        query.setParameter("username", username);
        if (query.getResultList().size() > 0) {
            log.info("Roles found");
            return query.getResultList();
        } else{
            log.error("No roles found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public List<Role> findByPermission(String permissionName) {
        Query query = entityManager.createQuery("select r from roleEntity r cross join permissionEntity pp where pp.permissionName = : permissionName", Role.class);
        query.setParameter("permissionName", permissionName);
        if (query.getResultList().size() > 0) {
            log.info("Roles found");
            return query.getResultList();
        } else{
            log.error("No roles found");
            return null;
        }
    }
}
