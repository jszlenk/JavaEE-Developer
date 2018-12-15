package pl.codeme.jeeb.e2.bank.backend.db;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import pl.codeme.jeeb.e2.bank.backend.logger.Log;
import pl.codeme.jeeb.e2.bank.backend.model.Model;

public class ManagerDBImpl implements ManagerDB {

    @Inject
    @Log
    private Logger log;

    @PersistenceContext(unitName = "bank-backend")
    private EntityManager em;

    @PostConstruct
    private void init() {
        log.info("Init EM: " + em);
    }

    public <T extends Model> T findById(Integer id, Class<T> clazz) {
        return em.find(clazz, id);
    }

    public <T extends Model> T findByField(String fieldName, Object value, Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(clazz);

        Root<T> model = q.from(clazz);
        q.select(model).where(cb.equal(model.get(fieldName), value));

        try {
            return em.createQuery(q).getSingleResult();
        } catch (NoResultException e) {
            log.warn("findByField: no result for " + clazz.getName(), e);

            return null;
        }
    }

    public <T extends Model> T persist(T model) {
        model = em.merge(model);
        em.flush();
        log.info("Peresist " + model.getClass().getName() + " ( id: " + model.getId() + " )");

        return model;
    }

    public <T extends Model> void remove(T model) {
        em.remove(em.contains(model) ? model : em.merge(model));
        log.info("Remove " + model.getClass().getName() + " ( id: " + model.getId() + " )");
    }

    public int executeNamed(String name, QueryParameter... params) {
        Query q = em.createNamedQuery(name);
        for (QueryParameter param : params) {
            q.setParameter(param.getName(), param.getValue());
        }

        return q.executeUpdate();
    }

    public <T> T executeNamedSingle(String name, Class<T> model, QueryParameter... params) {
        try {
            TypedQuery<T> q = em.createNamedQuery(name, model);
            for (QueryParameter param : params) {
                q.setParameter(param.getName(), param.getValue());
            }

            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public <T> List<T> executeNamed(String name, Class<T> model, QueryParameter... params) {
        try {
            TypedQuery<T> q = em.createNamedQuery(name, model);
            for (QueryParameter param : params) {
                q.setParameter(param.getName(), param.getValue());
            }

            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return em.toString() + " Open: " + em.isOpen();
    }
}
