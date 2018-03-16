/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.ws.rest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.finalproject.dao.ejbs.AlertDAORemote;
import masterdegree.ada.finalproject.dao.ejbs.UserDAORemote;
import masterdegree.ada.finalproject.model.Alert;
import masterdegree.ada.finalproject.model.UserBbr;
import masterdegree.ada.finalproject.model.vo.AlertVO;

/**
 *
 * @author Angel.Sahagun
 */
@Path("/alert")
public class AlertRS {

    @Path("/add")
    @POST
    public String addAlert(AlertVO alertVO) {
        String message = "";
        Coordinate alertMeLocation = new Coordinate(alertVO.getLatitude(), alertVO.getLongitude());
        try {
            Context ctx = new InitialContext();
            AlertDAORemote alertDAO = (AlertDAORemote) ctx.lookup(RSConstants.ALERT_LU);
            UserDAORemote usrD = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr usr = usrD.findByEmail(alertVO.getEmail());
            if (usr != null) {
                Alert newAlert = alertDAO.add(alertVO.getAlertName(), alertMeLocation, usr, alertVO.getRatio());
                message = "Alert: " + newAlert.getName() + " added for user: " + newAlert.getUserid().getFirstname() + " ";
            }

        } catch (NamingException e) {
            message = "Error while adding place";
            e.printStackTrace();
        }
        return message;
    }

}
