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
import masterdegree.ada.finalproject.model.SavedPlace;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author angel_banuelos
 */
@Remote
public interface SavedPlaceDAORemote {
    
    public SavedPlace add(String name, float latitude, float longitude, int ratio, UserBbr userid);
    
    public List<SavedPlace> getAll(UserBbr userId);
    
    public SavedPlace findByName(String name, UserBbr userId) throws NoResultException, NonUniqueResultException;

    public SavedPlace findById(long savePlaceId);
    
}
