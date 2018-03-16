/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.dao.ejbs.ejblocal;

import java.util.List;
import javax.ejb.Local;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.finalproject.model.History;
import masterdegree.ada.finalproject.model.Route;
import masterdegree.ada.finalproject.model.SavedPlace;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author angel_banuelos
 */
@Local
public interface HistoryDAOLocal {

    public History add(UserBbr userId, Coordinate c);

    public History add(UserBbr userId, Coordinate c, Route routeId);

    public History add(UserBbr userId, Coordinate c, SavedPlace savedPlaceId);

    public History add(UserBbr userId, Coordinate c, SavedPlace savedPlaceId, Route routeId);

    public List<History> getAll(UserBbr userId);

}
