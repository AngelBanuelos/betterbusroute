/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.dao.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import masterdegree.ada.finalproject.dao.GenericDAO;
import masterdegree.ada.finalproject.dao.ejbs.ejblocal.SavedPlaceDAOLocal;
import masterdegree.ada.finalproject.model.SavedPlace;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author Angel.Sahagun
 */
@Stateless
@LocalBean
public class SavedPlaceDAO extends GenericDAO<SavedPlace> implements SavedPlaceDAORemote, SavedPlaceDAOLocal {

    public SavedPlace add(String name, float latitude, float longitude, int ratio, UserBbr userid) {
        return add(new SavedPlace(name, latitude, longitude, ratio, userid));
    }

    public List<SavedPlace> getAll(UserBbr userId) {
        Query query = em.createNamedQuery("SavedPlace.findByUserId", SavedPlace.class);
        query.setParameter("id", userId);
        List<SavedPlace> results = query.getResultList();
        return results;
    }

    public SavedPlace findByName(String name, UserBbr userId) throws NoResultException, NonUniqueResultException {
        Query query = em.createNamedQuery("SavedPlace.findByName", SavedPlace.class);
        query.setParameter("name", name);
        query.setParameter("userId", userId.getId());
        SavedPlace place = (SavedPlace) query.getSingleResult();
        return place;
    }
    
    public SavedPlace findById(long savePlaceId){
        return super.find(savePlaceId);
    }
}
