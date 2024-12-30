package org.example.coffeeshopee.model.service;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Permission;
import org.example.coffeeshopee.model.utils.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class PermissionService implements Service<Permission, Long>{
    @Inject
    private RoleService roleService;

    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Permission permission) {
        entityManager.persist(permission);
        log.info("Permission saved: " + permission);
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Permission permission) {
        if (permission.getId() != null){
            entityManager.merge(permission);
            log.info("Permission edited: " + permission);
        } else {
            log.error("Permission edited is null");
        }
    }

    @Transactional
    @Loggable
    @Override
    public Permission remove(Long id) {
        Permission permission = entityManager.find(Permission.class, id);
        if (permission != null){
            entityManager.remove(permission);
            log.info("Permission removed: " + permission);
            return permission;
        } else {
            log.error("Permission removed is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Permission findById(Long id) {
        if (id != null){
            log.info("Permission found: " + id);
            return entityManager.find(Permission.class, id);
        } else {
            log.error("Permission id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Permission> findAll() {
        Query query = entityManager.createQuery("SELECT pp FROM permissionEntity pp", Permission.class);
        if (query.getResultList().size() > 0){
            log.info("Permissions found");
            return query.getResultList();
        }else{
            log.error("Permissions not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Permission findByName(String permissionName) {
        Query query = entityManager.createQuery("SELECT pp FROM permissionEntity pp WHERE pp.permissionName = :name", Permission.class);
        query.setParameter("name", permissionName);
        if (query.getResultList().size() > 0){
            log.info("Permissions found");
            return (Permission) query.getSingleResult();
        } else {
            log.error("Permissions not found");
            return null;
        }
    }
}
