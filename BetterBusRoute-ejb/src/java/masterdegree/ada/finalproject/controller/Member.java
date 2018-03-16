/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import masterdegree.ada.finalproject.dao.ejbs.SavedPlaceDAO;
import masterdegree.ada.finalproject.dao.ejbs.UserDAO;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author Angel.Sahagun
 */
@Stateful
public class Member {

    @EJB
    UserDAO usr;

    @EJB
    SavedPlaceDAO place;

    private UserBbr user;

    @PostConstruct
    public void init() {

    }

    public void identify(String userName, String password) throws Exception {
        user = usr.findByEmail(userName);
        if (user == null) {
            throw new Exception("User is not authenticated.");
        } else if (!user.getPassword().equals(password)) {
            throw new Exception("User or password invalid.");
        }
    }

    @Remove
    public void logoff() {

    }

    public void addPlace(Coordinate c, String name, int desireRatio) throws Exception {
        if (user != null) {
            place.add(name, (float) c.getLat(), (float) c.getLng(), desireRatio, user);
        } else {
            throw new Exception("User is not authenticated");
        }
    }
}
