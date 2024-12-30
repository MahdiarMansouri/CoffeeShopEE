package org.example.coffeeshopee.model.service;

import java.util.List;

public interface Service <T,I>{
    public void save(T t);
    public void edit(T t);
    public T remove(I id);
    public T findById(I id);
    public List<T> findAll();
}
