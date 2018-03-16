/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdegree.ada.finalproject.controller;

import java.io.Serializable;


/**
 *
 * @author angel_banuelos
 */
public class PathVO implements Serializable {
    
    private int busses;
    private double distance;
    private RoutePathVO[] routes;
    private Coordinate[] path;
    private Coordinate[] points;

    public int getBusses() {
        return busses;
    }

    public void setBusses(int busses) {
        this.busses = busses;
    }

    public RoutePathVO[] getRoutes() {
        return routes;
    }

    public void setRoutes(RoutePathVO[] routes) {
        this.routes = routes;
    }

    public Coordinate[] getPath() {
        return path;
    }

    public void setPath(Coordinate[] path) {
        this.path = path;
    }

    public Coordinate[] getPoints() {
        return points;
    }

    public void setPoints(Coordinate[] points) {
        this.points = points;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
}
