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
import masterdegree.ada.finalproject.controller.Router2Remote;

/**
 *
 * @author angel_banuelos
 */
@Path("/Hello")
public class HelloWordREST {

    @Path("/gretting/{name}")
    @GET
    public String sayHello(@PathParam("name") String name) {
        System.out.println("Name " + name);
        return "Hello " + name;
    }

    @Path("/paint/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Coordinate[] paintPath(@PathParam("id") int id) throws NamingException {
        InitialContext cxt = new InitialContext();
        Coordinate[] c = null;
        try {
            Router2Remote name = (Router2Remote)cxt.lookup("java:global/BetterBusRoute/BetterBusRoute-ejb/Router2!masterdegree.ada.finalproject.controller.Router2Remote");
            if (name == null) {
                System.out.println("Its Null");
            }
            //Router2Remote name = (Router2Remote)PortableRemoteObject.narrow(c, Router2Remote.class);
            Coordinate[] ce = name.paintRoute(id);
            if(ce != null){
                c = ce;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return c;
    }
}
