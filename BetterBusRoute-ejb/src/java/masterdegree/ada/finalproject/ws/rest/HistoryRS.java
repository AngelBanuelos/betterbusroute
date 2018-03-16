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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.finalproject.dao.ejbs.HistoryDAORemote;
import masterdegree.ada.finalproject.dao.ejbs.RouteDAORemote;
import masterdegree.ada.finalproject.dao.ejbs.SavedPlaceDAORemote;
import masterdegree.ada.finalproject.dao.ejbs.UserDAORemote;
import masterdegree.ada.finalproject.model.Route;
import masterdegree.ada.finalproject.model.SavedPlace;
import masterdegree.ada.finalproject.model.UserBbr;
import masterdegree.ada.finalproject.model.vo.HistoryVO;

/**
 *
 * @author Angel.Sahagun
 */
@Path("/history")
public class HistoryRS {

    @Path("/addbyuseronly")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addByUser(HistoryVO historyVO) {
        String message = "";
        try {
            Context ctx = new InitialContext();
            HistoryDAORemote historyDAO = (HistoryDAORemote) ctx.lookup(RSConstants.HISTORY_LU);
            UserDAORemote usrD = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr usr = usrD.findByEmail(historyVO.getEmail());
            if (usr != null) {
                historyDAO.add(usr, new Coordinate(historyVO.getLatitude(), historyVO.getLongitude()));
                message = "history added";
            }

        } catch (NamingException e) {
            message = "Error while adding place";
            e.printStackTrace();
        }
        return message;
    }

    @Path("/addbyuserandplace")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addByUserAndPlace(HistoryVO historyVO) {
        String message = "";
        try {
            Context ctx = new InitialContext();
            HistoryDAORemote historyDAO = (HistoryDAORemote) ctx.lookup(RSConstants.HISTORY_LU);
            UserDAORemote usrD = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr usr = usrD.findByEmail(historyVO.getEmail());
            SavedPlaceDAORemote placeD = (SavedPlaceDAORemote) ctx.lookup(RSConstants.SAVED_PLACE_LU);
            SavedPlace place = placeD.findById(historyVO.getSavePlaceID());
            if (usr != null) {
                historyDAO.add(usr, new Coordinate(historyVO.getLatitude(), historyVO.getLongitude()), place);
                message = "history added";
            }

        } catch (NamingException e) {
            message = "Error while adding place";
            e.printStackTrace();
        }
        return message;
    }

    @Path("/addcompletehistory")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addCompleteHistory(HistoryVO historyVO) {
        String message = "";
        try {
            Context ctx = new InitialContext();
            HistoryDAORemote historyDAO = (HistoryDAORemote) ctx.lookup(RSConstants.HISTORY_LU);
            UserDAORemote usrD = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr usr = usrD.findByEmail(historyVO.getEmail());
            SavedPlaceDAORemote placeD = (SavedPlaceDAORemote) ctx.lookup(RSConstants.SAVED_PLACE_LU);
            SavedPlace place = placeD.findById(historyVO.getSavePlaceID());
            RouteDAORemote routeD = (RouteDAORemote) ctx.lookup(RSConstants.ROUTE_LU);
            Route route = routeD.findById(historyVO.getRouteID());
            if (usr != null) {
                historyDAO.add(usr, new Coordinate(historyVO.getLatitude(), historyVO.getLongitude()), place, route);
                message = "history added";
            }

        } catch (NamingException e) {
            message = "Error while adding place";
            e.printStackTrace();
        }
        return message;
    }

}
