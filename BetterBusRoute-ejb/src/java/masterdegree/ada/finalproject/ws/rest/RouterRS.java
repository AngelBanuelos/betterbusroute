/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.ws.rest;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.finalproject.controller.PathVO;
import masterdegree.ada.finalproject.controller.RouteVO;
import masterdegree.ada.finalproject.controller.RouterRemote;
import masterdegree.ada.finalproject.dao.ejbs.RouteDAORemote;
import masterdegree.ada.finalproject.model.Route;

/**
 *
 * @author Angel.Sahagun
 */
@Path("/router")
public class RouterRS {

    @Path("/paintbyid/{id}/{path}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Coordinate[] paintById(@PathParam("id") int id, @PathParam("path") int pathNumber) {
        Coordinate[] coords = null;
        try {
            InitialContext ctx = new InitialContext();
            RouterRemote router = (RouterRemote) ctx.lookup(RSConstants.ROUTER_LU);
            coords = router.paintRoute(id, pathNumber);
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            return coords;
        }
    }

    @Path("/paintbyname/{name}/{path}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Coordinate[] paintByName(@PathParam("name") int name, @PathParam("path") int pathNumber) {
        Coordinate[] coords = null;
        try {
            InitialContext ctx = new InitialContext();
            RouterRemote router = (RouterRemote) ctx.lookup(RSConstants.ROUTER_LU);
            coords = router.paintRoute(name, pathNumber);
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            return coords;
        }
    }

    @Path("/paintNearbyRoutes/{oLatitude}/{oLongitude}/{dLatitude}/{dLongitude}/{ratio}/{budget}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RouteVO[] paintNearbyRoutes(@PathParam("oLatitude") float oLatitude,
            @PathParam("oLongitude") float oLongitude, @PathParam("dLatitude") float dLatitude,
            @PathParam("dLongitude") float dLongitude, @PathParam("ratio") int ratio,
            @PathParam("budget") int budget) {

        Coordinate origin = new Coordinate(oLatitude, oLongitude);
        Coordinate destination = new Coordinate(dLatitude, dLongitude);

        RouteVO[] routeVO = null;
        try {
            InitialContext ctx = new InitialContext();
            RouterRemote router = (RouterRemote) ctx.lookup(RSConstants.ROUTER_LU);
            routeVO = router.paintNearbyRoutes(origin, origin, ratio, budget);

        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            return routeVO;
        }
    }

    @Path("/paintFasterBusRoute/{oLatitude}/{oLongitude}/{dLatitude}/{dLongitude}/{ratio}/{routeID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PathVO[] paintFasterBusRoute(@PathParam("oLatitude") float oLatitude,
            @PathParam("oLongitude") float oLongitude, @PathParam("dLatitude") float dLatitude,
            @PathParam("dLongitude") float dLongitude, @PathParam("ratio") int ratio,
            @PathParam("routeID") long routeID) {
        Coordinate origin = new Coordinate(oLatitude, oLongitude);
        Coordinate destination = new Coordinate(dLatitude, dLongitude);

        PathVO[] routeVO = null;
        try {
            InitialContext ctx = new InitialContext();
            RouterRemote router = (RouterRemote) ctx.lookup(RSConstants.ROUTER_LU);

            RouteDAORemote routeD = (RouteDAORemote) ctx.lookup(RSConstants.ROUTE_LU);
            Route route = routeD.findById(routeID);
            routeVO = router.paintFasterBusRoute(origin, route, ratio, destination);

        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            return routeVO;
        }
    }

    @Path("/existsRoute/{oLatitude}/{oLongitude}/{dLatitude}/{dLongitude}/{ratio}/{routeID}")
    @GET
    public boolean existsRoute(@PathParam("oLatitude") float oLatitude,
            @PathParam("oLongitude") float oLongitude, @PathParam("dLatitude") float dLatitude,
            @PathParam("dLongitude") float dLongitude, @PathParam("ratio") int ratio,
            @PathParam("routeID") long routeID) {
        Coordinate origin = new Coordinate(oLatitude, oLongitude);
        Coordinate destination = new Coordinate(dLatitude, dLongitude);

        boolean exists = false;
        try {
            InitialContext ctx = new InitialContext();
            RouterRemote router = (RouterRemote) ctx.lookup(RSConstants.ROUTER_LU);

            RouteDAORemote routeD = (RouteDAORemote) ctx.lookup(RSConstants.ROUTE_LU);
            Route route = routeD.findById(routeID);
            exists = router.existsRoute(origin, route, ratio, destination);

        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            return exists;
        }
    }
    
    @Path("/paintFasterBusRouteSerial/{oLatitude}/{oLongitude}/{dLatitude}/{dLongitude}/{ratio}/{routeID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PathVO[] paintFasterBusRouteSerial(@PathParam("oLatitude") float oLatitude,
            @PathParam("oLongitude") float oLongitude, @PathParam("dLatitude") float dLatitude,
            @PathParam("dLongitude") float dLongitude, @PathParam("ratio") int ratio,
            @PathParam("routeID") long routeID) {
        Coordinate origin = new Coordinate(oLatitude, oLongitude);
        Coordinate destination = new Coordinate(dLatitude, dLongitude);

        PathVO[] routeVO = null;
        try {
            InitialContext ctx = new InitialContext();
            RouterRemote router = (RouterRemote) ctx.lookup(RSConstants.ROUTER_LU);

            RouteDAORemote routeD = (RouteDAORemote) ctx.lookup(RSConstants.ROUTE_LU);
            Route route = routeD.findById(routeID);
            routeVO = router.paintFasterBusRouteSerial(origin, route, ratio, destination);

        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            return routeVO;
        }
    }

}
