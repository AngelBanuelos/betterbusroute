/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller.ejblocal;

import javax.ejb.Local;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.finalproject.controller.PathVO;
import masterdegree.ada.finalproject.controller.RouteVO;
import masterdegree.ada.finalproject.model.Route;

/**
 *
 * @author angel_banuelos
 */
@Local
public interface RouterLocal {

    public Coordinate[] paintRoute(int id, int pathNumber) throws Exception;

    public Coordinate[] paintRoute(String routeName, int pathNumber) throws Exception;

    public RouteVO[] paintNearbyRoutes(Coordinate origin, Coordinate destination, int ratio, int budget) throws Exception;

    public PathVO[] paintCheperBusRoute(Coordinate origin, Route initialSelectedRoute, int ratio, Coordinate destination);

    public PathVO[] paintFasterBusRoute(Coordinate origin, Route initialSelectedRoute, int ratio, Coordinate destination);

    public boolean existsRoute(Coordinate origin, Route route, int ratio, Coordinate destination);
    
    public PathVO[] paintFasterBusRouteSerial(Coordinate origin, Route initialSelectedRoute, int ratio, Coordinate destination);

}
