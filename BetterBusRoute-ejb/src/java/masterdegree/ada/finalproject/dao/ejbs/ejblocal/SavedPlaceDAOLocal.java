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
import masterdegree.ada.finalproject.model.SavedPlace;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author angel_banuelos
 */
@Local
public interface SavedPlaceDAOLocal {

    public SavedPlace add(String name, float latitude, float longitude, int ratio, UserBbr userid);

    public List<SavedPlace> getAll(UserBbr userId);

    public SavedPlace findByName(String name, UserBbr userId) throws NoResultException, NonUniqueResultException;

    public SavedPlace findById(long savePlaceId);
    
}
