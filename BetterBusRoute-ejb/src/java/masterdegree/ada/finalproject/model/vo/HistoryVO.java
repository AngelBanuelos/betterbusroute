/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdegree.ada.finalproject.model.vo;

import java.io.Serializable;


/**
 *
 * @author angel_banuelos
 */
public class HistoryVO implements Serializable{
    
    private String email;
    private float latitude;
    private float longitude;
    private long savePlaceID;
    private long routeID;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public long getSavePlaceID() {
        return savePlaceID;
    }

    public void setSavePlaceID(long savePlaceID) {
        this.savePlaceID = savePlaceID;
    }

    public long getRouteID() {
        return routeID;
    }

    public void setRouteID(long routeID) {
        this.routeID = routeID;
    }
    
}
