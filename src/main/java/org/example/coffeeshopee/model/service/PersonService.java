package org.example.coffeeshopee.model.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Person;
import org.example.coffeeshopee.model.utils.Loggable;

import java.util.List;

@RequestScoped
@Slf4j
public class PersonService implements Service<Person, Long>{
    @PersistenceContext(unitName = "mahdiar")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Person person) {
        entityManager.persist(person);
        log.info("Person saved: " + person);
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Person person) {
        if (person.getId() != null) {
            entityManager.merge(person);
            log.info("Person edited: " + person);
        }else {
            log.error("Person id is null");
        }
    }

    @Transactional
    @Loggable
    @Override
    public Person remove(Long id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
            log.info("Person removed: " + person);
            return person;
        }else {
            log.error("Person id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public Person findById(Long id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            log.info("Person found: " + person);
            return person;
        }else {
            log.error("Person id is null");
            return null;
        }
    }

    @Transactional
    @Loggable
    @Override
    public List<Person> findAll() {
        Query query = entityManager.createQuery("select p from personEntity p");
        if (query.getResultList() != null) {
            log.info("Persons found");
            return query.getResultList();
        } else {
            log.error("Persons not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public List<Person> findByFirstName(String firstName) {
        Query query = entityManager.createQuery("select p from personEntity p where p.firstName = :name");
        query.setParameter("name", firstName);
        if (query.getResultList() != null) {
            log.info("Persons found");
            return query.getResultList();
        }else {
            log.error("Persons not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public List<Person> findByLastName(String lastName) {
        Query query = entityManager.createQuery("select p from personEntity p where p.lastName = :name");
        query.setParameter("name", lastName);
        if (query.getResultList() != null) {
            log.info("Persons found");
            return query.getResultList();
        }else{
            log.error("Persons not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public List<Person> findByEmail(String email) {
        Query query = entityManager.createQuery("select p from personEntity p where p.email = :email");
        query.setParameter("email", email);
        if (query.getResultList() != null) {
            log.info("Persons found");
            return query.getResultList();
        }else{
            log.error("Persons not found");
            return null;
        }

    }

    @Transactional
    @Loggable
    public List<Person> findByPhone(String phone) {
        Query query = entityManager.createQuery("select p from personEntity p where p.phone = :phone");
        query.setParameter("phone", phone);
        if (query.getResultList() != null) {
            log.info("Persons found");
            return query.getResultList();
        }else {
            log.error("Persons not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public List<Person> findByAddress(String address) {
        Query query = entityManager.createQuery("select p from personEntity p where p.address = :address");
        query.setParameter("address", address);
        if (query.getResultList() != null) {
            log.info("Persons found");
            return query.getResultList();
        }else{
            log.error("Persons not found");
            return null;
        }
    }

    @Transactional
    @Loggable
    public Person findByNationalCode(String nationalCode) {
        Query query = entityManager.createQuery("select p from personEntity p where p.nationalCode = :nationalCode");
        query.setParameter("nationalCode", nationalCode);
        if (query.getResultList() != null) {
            log.info("Persons found");
            return (Person) query.getSingleResult();
        }else{
            log.error("Persons not found");
            return null;
        }
    }



}
