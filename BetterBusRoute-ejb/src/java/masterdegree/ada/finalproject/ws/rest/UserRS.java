/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.ws.rest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import masterdegree.ada.finalproject.dao.ejbs.UserDAORemote;
import masterdegree.ada.finalproject.model.Alert;
import masterdegree.ada.finalproject.model.SavedPlace;
import masterdegree.ada.finalproject.model.UserBbr;
import masterdegree.ada.finalproject.model.vo.UserVO;

/**
 *
 * @author Angel.Sahagun
 */
@Path("/user")
public class UserRS {

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String newAccout(UserVO userVO) {
        String message = "";
        try {
            Context ctx = new InitialContext();
            UserDAORemote usr = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr newUsr = usr.add(0, userVO.getFirstName(), userVO.getLastName(), userVO.getEmail(), userVO.getPassword());
            message = "Welcome " + newUsr.getFirstname() + ", " + newUsr.getLastname() + "!";
        } catch (NamingException e) {
            message = "Error while adding user";
            e.printStackTrace();
        }
        return message;
    }

    @POST
    @Path("/authenticate")
    public boolean authenticate(@FormParam("email") String email,
            @FormParam("password") String psw) {
        boolean isUser = false;
        try {
            Context ctx = new InitialContext();
            UserDAORemote usrD = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr usr = usrD.findByEmail(email);
            if (usr != null && usr.getPassword().equals(psw)) {
                isUser = true;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return isUser;
    }

    @GET
    @Path("/savedplaces/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public SavedPlace[] getSavedPlaces(@PathParam("email") String email) {
        SavedPlace[] places = null;
        try {
            Context ctx = new InitialContext();
            UserDAORemote usrD = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr usr = usrD.findByEmail(email);
            if (usr != null && usr.getSavedPlaceCollection() != null) {
                usr.getSavedPlaceCollection().toArray(places);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return places;
    }

    @GET
    @Path("/alerts/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alert[] getAlerts(@PathParam("email") String email) {
        Alert[] alerts = null;
        try {
            Context ctx = new InitialContext();
            UserDAORemote usrD = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr usr = usrD.findByEmail(email);
            if (usr != null && usr.getAlertCollection() != null) {
                usr.getAlertCollection().toArray(alerts);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return alerts;
    }

}
