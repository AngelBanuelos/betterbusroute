/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.dao.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import masterdegree.ada.finalproject.dao.GenericDAO;
import masterdegree.ada.finalproject.dao.ejbs.ejblocal.UserDAOLocal;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author Angel.Sahagun
 */
@Stateless
public class UserDAO extends GenericDAO<UserBbr> implements UserDAORemote, UserDAOLocal {

    public UserBbr add(Integer id, String firstName, String lastName, String email, String password) {
        UserBbr usr = new UserBbr(id, firstName, lastName, email);
        usr.setPassword(password);
        return add(usr);
    }

    public List<UserBbr> getAll() {
        String sqlQuery = "SELECT u from UserBbr u";
        TypedQuery<UserBbr> query
                = em.createQuery(sqlQuery, UserBbr.class);
        List<UserBbr> results = query.getResultList();
        return results;
    }

    public UserBbr findByEmail(String email) throws NoResultException, NonUniqueResultException {
        Query query = em.createNamedQuery("UserBbr.findByEmail", UserBbr.class);
        query.setParameter("email", email);
        UserBbr userbbr = (UserBbr) query.getSingleResult();
        return userbbr;
    }

}
