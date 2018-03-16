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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import masterdegree.ada.finalproject.dao.ejbs.RouteDAORemote;
import masterdegree.ada.finalproject.model.Route;
import masterdegree.ada.finalproject.model.vo.RouteVO;

/**
 *
 * @author Angel.Sahagun
 */
@Path("/route")
public class RouteRS {

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addRoute(RouteVO routeVO) {
        String message = "";
        try {
            Context ctx = new InitialContext();
            RouteDAORemote routeDAO = (RouteDAORemote) ctx.lookup(RSConstants.ROUTE_LU);
            routeVO.setPath1(routeVO.getPath1().replaceAll("\\(", "")
                    .replaceAll("\\)", ",").replaceAll(" ", ""));
            routeVO.setPath1(routeVO.getPath1().substring(0, routeVO.getPath1().length()-1));
            
            routeVO.setPath2(routeVO.getPath2().replaceAll("\\(", "").replaceAll("\\)", ",")
                    .replaceAll(" ", ""));
            routeVO.setPath2(routeVO.getPath2().substring(0, routeVO.getPath2().length()-1));
            
            Route newRoute = routeDAO.add(routeVO.getName(), routeVO.getName(), routeVO.getPath1(), routeVO.getPath2());
            message = "Route: " + newRoute.getName() + " added ";
        } catch (NamingException e) {
            message = "Error while adding route";
            e.printStackTrace();
        } catch (Exception e) {
            message = "Error while adding route";
            e.printStackTrace();
        }
        return message;
    }

}
