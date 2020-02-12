package com.capstone.sheepsheadbackend.repository;

import java.util.Collection;

public interface CommonRepository<T> {
    public T save(T domain);
    public Iterable<T> save(Collection<T> domains);
    public void delete(T domain);
    public T findByid(String id);
    public Iterable<T> findAll();
}
