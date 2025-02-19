package dao;

import entity.Criteria;

import java.util.List;

public interface CRUDOperations<E> {
    List<E> findAll(int page, int size);
    E findById(int id);
    List<E> saveAll(List<E> entityList);
    E update(int id,E entity);
    void delete(int id);
    List<E> findByCriteria(List<Criteria> criteria,int page, int size);
}
