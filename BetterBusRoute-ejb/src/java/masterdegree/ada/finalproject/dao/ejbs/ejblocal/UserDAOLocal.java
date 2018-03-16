/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.dao.ejbs.ejblocal;

import java.util.List;
import javax.ejb.Local;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author angel_banuelos
 */
@Local
public interface UserDAOLocal {

    public UserBbr add(Integer id, String firstName, String lastName, String email, String password);

    public List<UserBbr> getAll();

    public UserBbr findByEmail(String email) throws NoResultException, NonUniqueResultException;
}
