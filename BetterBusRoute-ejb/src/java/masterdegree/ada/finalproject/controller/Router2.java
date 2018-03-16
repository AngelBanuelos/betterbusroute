/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;

import masterdegree.ada.finalproject.controller.ejblocal.Router2Local;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import masterdegree.ada.finalproject.model.Route;

/**
 *
 * @author Angel.Sahagun
 */
@Stateless
public class Router2 implements Router2Remote, Router2Local {

    @EJB
    RoutesRuntime r;

    @Override
    public Coordinate[] paintRoute() {
        ArrayList<Coordinate> path = r.getRoutes().get(0).getPathDecode1();
        Coordinate[] path1 = new Coordinate[path.size()];
        path.toArray(path1);
        return path1;
    }

    @Override
    public Coordinate[] paintRoute(int id) {
        return  paintRoute();
    }

    @Override
    public Coordinate[] paintRoute(String routeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Route[] paintNearbyRoutes(Coordinate origin, Coordinate destination, int ratio, int budget) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
