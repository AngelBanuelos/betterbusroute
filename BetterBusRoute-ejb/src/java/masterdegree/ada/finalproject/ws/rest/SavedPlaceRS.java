/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.ws.rest;

import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import masterdegree.ada.finalproject.dao.ejbs.SavedPlaceDAORemote;
import masterdegree.ada.finalproject.dao.ejbs.UserDAORemote;
import masterdegree.ada.finalproject.model.SavedPlace;
import masterdegree.ada.finalproject.model.UserBbr;
import masterdegree.ada.finalproject.model.vo.SavedPlaceVO;

/**
 *
 * @author Angel.Sahagun
 */
@Path("/places")
public class SavedPlaceRS {
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public String newPlace(SavedPlaceVO savedPlaceVO) {
        String message = "";
        try {
            Context ctx = new InitialContext();
            SavedPlaceDAORemote savedPlaceDAO = (SavedPlaceDAORemote) ctx.lookup(RSConstants.SAVED_PLACE_LU);
            UserDAORemote usrD = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr usr = usrD.findByEmail(savedPlaceVO.getEmail());
            if (usr != null) {
                SavedPlace newPlace = savedPlaceDAO.add(savedPlaceVO.getPlaceName(),
                        savedPlaceVO.getLatitude(), savedPlaceVO.getLongitude(),
                        savedPlaceVO.getRatio(), usr);
                message = "" + newPlace.getName() + " added for user: " + newPlace.getUserid().getFirstname() + " ";
            }
        } catch (NamingException e) {
            message = "Error while adding place";
            e.printStackTrace();
        }
        return message;
    }

    @GET
    @Path("/getall/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public SavedPlaceVO[] getSavedPlaces(@PathParam("email") String email) {
        SavedPlaceVO[] places = null;
        try {
            Context ctx = new InitialContext();
            UserDAORemote usrD = (UserDAORemote) ctx.lookup(RSConstants.USER_LU);
            UserBbr usr = usrD.findByEmail(email);
            SavedPlaceDAORemote savedD = (SavedPlaceDAORemote) ctx.lookup(RSConstants.SAVED_PLACE_LU);
            List<SavedPlace> list = savedD.getAll(usr);
            if (list != null && !list.isEmpty()) {
                places = new SavedPlaceVO[list.size()];
                List<SavedPlaceVO> results2 = new ArrayList();
                for (SavedPlace savedPlace : list) {
                    SavedPlaceVO sv = new SavedPlaceVO();
//                    sv.setEmail(savedPlace.getUserid().getEmail());
                    sv.setLatitude(savedPlace.getLatitude());
                    sv.setLongitude(savedPlace.getLongitude());
                    sv.setRatio(savedPlace.getRatio());
                    sv.setPlaceName(savedPlace.getName());
                    results2.add(sv);
                }
                results2.toArray(places);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }

}
