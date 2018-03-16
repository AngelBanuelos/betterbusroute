/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.ws.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author angel_banuelos
 */
@ApplicationPath("rs")
public class RESTApplications extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(org.glassfish.javaee.javascript.backend.todo.JsonMoxyConfigurationContextResolver.class);
        resources.add(masterdegree.ada.finalproject.ws.rest.AlertRS.class);
        resources.add(masterdegree.ada.finalproject.ws.rest.HelloWordREST.class);
        resources.add(masterdegree.ada.finalproject.ws.rest.HistoryRS.class);
        resources.add(masterdegree.ada.finalproject.ws.rest.RouteRS.class);
        resources.add(masterdegree.ada.finalproject.ws.rest.RouterRS.class);
        resources.add(masterdegree.ada.finalproject.ws.rest.SavedPlaceRS.class);
        resources.add(masterdegree.ada.finalproject.ws.rest.UserRS.class);
    }

}
