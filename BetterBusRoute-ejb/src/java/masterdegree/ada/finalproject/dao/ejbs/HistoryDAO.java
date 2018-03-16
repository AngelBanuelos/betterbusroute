/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.dao.ejbs;

import java.util.List;
import javax.persistence.Query;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.finalproject.dao.GenericDAO;
import masterdegree.ada.finalproject.dao.ejbs.ejblocal.HistoryDAOLocal;
import masterdegree.ada.finalproject.model.History;
import masterdegree.ada.finalproject.model.Route;
import masterdegree.ada.finalproject.model.SavedPlace;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author angel_banuelos
 */
public class HistoryDAO extends GenericDAO<History> implements HistoryDAORemote, HistoryDAOLocal {

    public History add(UserBbr userId, Coordinate c) {
        return add(new History(0, (float) c.getLat(), (float) c.getLng(), userId));
    }

    public History add(UserBbr userId, Coordinate c, Route routeId) {
        History history = new History(0, (float) c.getLat(), (float) c.getLng(), userId);
        history.setRouteid(routeId);
        return add(history);
    }

    public History add(UserBbr userId, Coordinate c, SavedPlace savedPlaceId) {
        History history = new History(0, (float) c.getLat(), (float) c.getLng(), userId);
        history.setSavedplacedid(savedPlaceId);
        return add(history);
    }

    public History add(UserBbr userId, Coordinate c, SavedPlace savedPlaceId, Route routeId) {
        History history = new History(0, (float) c.getLat(), (float) c.getLng(), userId);
        history.setSavedplacedid(savedPlaceId);
        history.setRouteid(routeId);
        return add(history);
    }

    public List<History> getAll(UserBbr userId) {
        Query query = em.createNamedQuery("History.findByUserId", History.class);
        query.setParameter("id", userId.getId());
        List<History> results = query.getResultList();
        return results;
    }

}
