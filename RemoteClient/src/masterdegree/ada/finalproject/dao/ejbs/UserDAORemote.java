/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.dao.ejbs;

import java.util.List;
import javax.ejb.Remote;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author angel_banuelos
 */
@Remote
public interface UserDAORemote {

    public UserBbr add(Integer id, String firstName, String lastName, String email, String password);

    public List<UserBbr> getAll();

    public UserBbr findByEmail(String email) throws NoResultException, NonUniqueResultException;

}
