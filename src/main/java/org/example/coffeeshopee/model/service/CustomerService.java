package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Customer;
import org.example.coffeeshopee.model.utils.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class CustomerService implements Service<Customer, Long> {
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
        log.info("Customer saved");
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Customer customer) {
        if (customer.getId() != null) {
            entityManager.merge(customer);
            log.info("Customer edited");
        }else {
            log.error("Customer id is null");
        }

    }

    @Transactional
    @Loggable
    @Override
    public Customer remove(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
            log.info("Customer removed");
            return customer;
        }else {
            log.error("Customer id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Customer findById(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            log.info("Customer found");
            return customer;
        }else {
            log.error("Customer id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Customer> findAll() {
        Query query = entityManager.createQuery("select c from customerEntity c", Customer.class);
        if (query.getResultList().size() > 0) {
            log.info("Customer found");
            return (List<Customer>) query.getResultList();
            }else{
            log.error("Customers not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Customer findByFirstName(String name) {
        Query query = entityManager.createQuery("select c from customerEntity c where c.firstName = :name", Customer.class);
        query.setParameter("name", name);
        if (query.getResultList().size() > 0) {
            log.info("Customer found");
            return (Customer) query.getResultList().get(0);
        }else{
            log.error("Customers not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Customer findByLastName(String name) {
        Query query = entityManager.createQuery("select c from customerEntity c where c.lastName = :name", Customer.class);
        query.setParameter("name", name);
        if (query.getResultList().size() > 0) {
            log.info("Customer found");
            return (Customer) query.getResultList().get(0);
        }else{
            log.error("Customers not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Customer findByPhone(String phoneNumber) {
        Query query = entityManager.createQuery("select c from customerEntity c where c.phone = :phoneNumber", Customer.class);
        query.setParameter("phoneNumber", phoneNumber);
        if (query.getResultList().size() > 0) {
            log.info("Customer found");
            return (Customer) query.getResultList().get(0);
        }else{
            log.error("Customers not found");
            return null;
        }
    }
}
