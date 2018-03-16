/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;

import java.io.Serializable;

/**
 *
 * @author Angel.Sahagun
 */
public class Coordinate implements Serializable {

    private double lat;
    private double lng;

    public Coordinate() {
    }

    public Coordinate(double latitude, double longitude) {
        this.lat = latitude;
        this.lng = longitude;
    }

    public void setLat(double latitude) {
        this.lat = latitude;
    }

    public void setLng(double longitude) {
        this.lng = longitude;
    }

    
    public double getLat() {
        return lat;
    }
    
    public double getLng() {
        return lng;
    }
}
