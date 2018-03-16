/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;

import javax.ejb.Remote;
import masterdegree.ada.finalproject.model.Route;

/**
 *
 * @author Angel.Sahagun
 */
@Remote
public interface Router2Remote {

    public Coordinate[] paintRoute(int id);

    public Coordinate[] paintRoute(String routeName);

    public Route[] paintNearbyRoutes(Coordinate origin, Coordinate destination, int ratio, int budget);

}
