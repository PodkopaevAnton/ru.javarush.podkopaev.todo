package ru.javarush.dao;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.entity.Task;

import java.util.List;

@Repository
@AllArgsConstructor
public class ToDoTaskDao implements TaskDAO {

    private final SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Task> getAll(int offset, int limit) {
        Query<Task> query = sessionFactory.getCurrentSession().createQuery("select t from Task t", Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int getAllCount() {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(t) from Task t", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Task getById(int id) {
        Query<Task> query = sessionFactory.getCurrentSession().createQuery("select t from Task t where t.id = :ID", Task.class);
        query.setParameter("ID", id);
        return query.uniqueResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveOrUpdate(Task task) {
        sessionFactory.getCurrentSession().persist(task);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(Task task) {
        sessionFactory.getCurrentSession().remove(task);
    }

}
