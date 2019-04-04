package swimple.dao;

import swimple.models.ApplicationRecord;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public abstract class CrudRepository<T extends ApplicationRecord> {

    @PersistenceContext
    EntityManager em;

    private Class<T> entityClass;

    public final void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> findAll() {
        return em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }

    public Optional<T> findById(int id) {
        return Optional.of(em.find(entityClass, id));
    }

    public void save(T entity) {
        if(entity.isNew()) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }
}
