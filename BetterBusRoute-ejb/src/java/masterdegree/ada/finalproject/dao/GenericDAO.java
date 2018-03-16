/*
 */
package masterdegree.ada.finalproject.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import masterdegree.ada.finalproject.model.DomainEntity;

/**
 * Generic DAO implementation which supports basic operations for classes
 * derived from com.example.traveller.model.DomainEntity. The implementation
 * used only supports classes that are direct children of this class (see {
 *
 * @see #returnedClass} for an explanation).
 * @param <PersistentClass> type of DomainEntity to be supported in a given
 * child class.
 * @author education@oracle.com
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class GenericDAO<PersistentClass extends DomainEntity> {

    @PersistenceContext(unitName = "BestBusRoute-EJBPU")
    protected EntityManager em;

    /**
     * Inserts instance into persistent store.
     *
     * @param newInstance instance to insert into persistent store
     * @return the instance inserted into persistent store
     */
    public PersistentClass add(PersistentClass newInstance) {
        em.persist(newInstance);
        return newInstance;
    }

    /**
     * Updates the persistent store representation of the instance provided. The
     * instance to update must be one of:
     * <ul>
     * <li> a persistent instance in the current persistence context (in which
     * case the call is redundant)
     * <li> a detached instance, with a valid id and updated values
     * <li> a transient instance, with a valid id and updated values
     * </ul>
     * If the instance provided is persistent in the current persistence
     * context, it is returned. Otherwise, a new persistent instance with
     * updated values is returned.
     *
     * @param instance instance to update.
     * @return an updated persistent instance
     */
    public PersistentClass update(PersistentClass instance) {
        PersistentClass result = em.merge(instance);
        return result;
    }

    /**
     * Finds a persistent instance, given its unique id.
     *
     * @param id unique id for instance to return
     * @return persistent instance identified by id provided
     */
    public PersistentClass find(long id) {
        PersistentClass result = em.find(returnedClass(), id);
        return result;
    }

    public PersistentClass find(int id) {
        PersistentClass result = em.find(returnedClass(), id);
        return result;
    }

    /**
     * Finds all persistent instances that match the query provided.
     *
     * @param jpaQL JPA QL query to execute
     * @return list of persistent instances that match the query provided
     */
    public List<PersistentClass> query(String jpaQL) {
        System.out.println("EM: " + em);
        System.out.println("class: " + returnedClass());
        TypedQuery<PersistentClass> query
                = em.createQuery(jpaQL, returnedClass());
        List<PersistentClass> result = query.getResultList();
        return result;
    }

    /**
     * Removes an instance from the persistent store, given its unique it.
     *
     * @param id unique id of persistent instance to remove
     */
    public void remove(long id) {
        em.remove(em.getReference(returnedClass(), id));
    }

    /**
     * Removes the instance provided from the persistent store.
     *
     * @param instance to remove
     */
    public void remove(PersistentClass instance) {
        em.remove(instance);
    }

    /**
     * Returns the parameterized type used when extending directly from this
     * generic class, as an instance of class Class. This return value can be
     * used when such an instance is needed by Java API methods, such as those
     * in javax.persistance.EntityManager. The trick used here will only work
     * when the child class directly extends this one. A complete discussion of
     * this trick can be found here:
     * http://www.artima.com/weblogs/viewpost.jsp?thread=208860
     *
     * @return parameterized type used to instance class, as an instance of
     * class Class.
     */
    @SuppressWarnings("unchecked")
    protected Class<PersistentClass> returnedClass() {
        ParameterizedType parameterizedType
                = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<PersistentClass>) (parameterizedType.getActualTypeArguments()[0]);
    }

    /**
     *
     */
    public static class QueryException extends RuntimeException {

        private static final long serialVersionUID = 0L;
    }

}
