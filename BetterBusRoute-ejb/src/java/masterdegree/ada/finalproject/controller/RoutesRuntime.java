/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;

import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import masterdegree.ada.finalproject.dao.ejbs.RoutesDAO;
import masterdegree.ada.finalproject.model.Route;

/**
 *
 * @author angel_banuelos
 */
@Singleton
@Startup
@DependsOn("HuffmanTreeInstance")
public class RoutesRuntime {

    @EJB
    private RoutesDAO route;

    @Resource
    private TimerService tmrService;

    @EJB
    private Path path;

    private List<Route> routes;
    
    private HashMap<Integer, Route> hashRoute;

    @PostConstruct
    public void init() {
        updateRoutes();
        startTimer();
        System.out.println("Routes on runtime " + routes.size());
        System.out.println("hashRoute on runtime " + hashRoute.size());
    }

    public List<Route> getRoutes() {
        return routes;
    }

    private void decodeRoutes() {
        hashRoute = new HashMap();
        for (Route route1 : routes) {
            route1.setPathDecode1(path.createPath(route1.getPathbyte1()));
            route1.setPathDecode2(path.createPath(route1.getPathbyte2()));
            hashRoute.put(route1.getIndex(), route1);
        }
    }

    public void updateRoutes() {
        routes = route.getAll();
        decodeRoutes();
    }

    @Timeout
    public void timeout(Timer t) {
        updateRoutes();
    }

    @PreDestroy
    public void safeClose() {
        for (Timer t : tmrService.getAllTimers()) {
            t.cancel();
        }
    }

    private void startTimer() {
        //Execute every 5 minutes
        long duration = (1000) * 60 * 5;
        tmrService.createTimer(duration, "Timer RuntimeRoutes");
    }
    
    public Route getRoute(long id){
        if(hashRoute != null && !hashRoute.isEmpty()){
            System.out.println("hashRoute:  Route ----> " + id);
            return hashRoute.get((int)id);
        }
        return null;
    }

}
