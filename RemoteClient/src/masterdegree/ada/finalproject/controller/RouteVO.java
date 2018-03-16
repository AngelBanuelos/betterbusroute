/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;


/**
 *
 * @author angel_banuelos
 */
public class RouteVO {

    private String routeName;
    private long routeID;
    private double totalDistancePath1;
    private double totalDistancePath2;
    
    private Coordinate[] path1;
    
    private Coordinate[] path2;

    public long getRouteID() {
        return routeID;
    }

    public void setRouteID(long routeID) {
        this.routeID = routeID;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public double getTotalDistancePath1() {
        return totalDistancePath1;
    }

    public void setTotalDistancePath1(double totalDistancePath1) {
        this.totalDistancePath1 = totalDistancePath1;
    }

    public double getTotalDistancePath2() {
        return totalDistancePath2;
    }

    public void setTotalDistancePath2(double totalDistancePath2) {
        this.totalDistancePath2 = totalDistancePath2;
    }

    public Coordinate[] getPath1() {
        return path1;
    }

    public void setPath1(Coordinate[] path1) {
        this.path1 = path1;
    }

    public Coordinate[] getPath2() {
        return path2;
    }

    public void setPath2(Coordinate[] path2) {
        this.path2 = path2;
    }

}
