/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.model.tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.finalproject.controller.HuffmanTreeInstance;
import masterdegree.ada.finalproject.controller.ejblocal.Router2Local;
import masterdegree.ada.finalproject.controller.RoutesRuntime;
import masterdegree.ada.finalproject.controller.ejblocal.RouterLocal;
import masterdegree.ada.finalproject.dao.ejbs.ejblocal.AlertDAOLocal;
import masterdegree.ada.finalproject.dao.ejbs.ejblocal.RouteDAOLocal;
import masterdegree.ada.finalproject.dao.ejbs.ejblocal.UserDAOLocal;
import masterdegree.ada.finalproject.model.Alert;
import masterdegree.ada.finalproject.model.Route;
import masterdegree.ada.finalproject.model.UserBbr;
import masterdegree.ada.tree.HuffmanCode;

/**
 *
 * @author angel_banuelos
 */
@Singleton
@LocalBean
public class Runner {

    EJBContext context;

    @EJB
    UserDAOLocal usr;

    @EJB
    AlertDAOLocal alert;

    @EJB
    HuffmanTreeInstance tree;

    @EJB
    RouteDAOLocal routeDAO;

    @EJB
    RouterLocal router;
    

    @PostConstruct
    public void postContruct() {
        System.out.println("Ready to served");
        routes();
        getRoutesNearByMyLocation();
//        printTotalDistance();
//        paint();
    }

    public void ejbTimer() {
        System.out.println("Timeout ");
        getAllUsers();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void getAllUsers() {
        List<UserBbr> l = usr.getAll();
        for (UserBbr l1 : l) {
            System.out.println(l1.getFirstname());
        }
    }

    public void newUser() {
//        UserBbr paul = new UserBbr(0,"Paul", "Bañuelos", "7paul7@gmail.com");
//        paul.setPassword("bebetter7");
//        paul.setPassword("NewPassword");
//        usr.add(paul);
    }

    private void update() {
        UserBbr paul
                = usr.findByEmail("ing.angel.banuelos@gmail.com");

        Alert alertMe = new Alert(0, "Casa", 0.01112f, 24.342933f);
        alertMe.setUserid(paul);
//        alert.add(alertMe);

    }

    //file:///Users/angel_banuelos/NetBeansProjects/MasterDegree/BetterBusRoute/BetterBusRoute-ejb/src/java/masterdegree/ada/finalproject/investigation/bulkFile
    public void insertNewRoutes() throws Exception {
        String[] routes = readRoutes("D:\\TIC\\BestBusRoute\\BetterBusRoute\\BetterBusRoute-ejb\\src\\java\\masterdegree\\ada\\finalproject\\investigation\\bulkFile").split("\n");;
        int i = 0;
        System.out.println("Routes: "+ routes.length);
        HuffmanCode code = new HuffmanCode(tree.getTree());
        while (i < routes.length) {
            Route route = new Route();
            String[] info = routes[i].split("\\|");
            if (info.length == 4) {
                route.setId(0);
                
                route.setName(info[3].trim());
                code.code(info[1]);
                route.setPathbyte1(code.getByte());    
                
                route.setPath1(i+"");
                route.setPath2(i+"");
                
                code.code(info[2]);
                route.setPathbyte2(code.getByte());

                route.setRoute(info[3].trim());
//                routeDAO.add(route);
                System.out.println("route: " + i);
            }
            i++;
        }
    }

    private static String readRoutes(String filename) throws Exception {
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("Exists");
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String textHelper, text = "";
        while ((textHelper = br.readLine()) != null) {
            text += textHelper + "\n";
        }
        br.close();
        return text;
    }

    private void routes() {
        try {
//            insertNewRoutes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getRoutesNearByMyLocation() {
//        Coordinate c = new Coordinate(20.668364d, -103.398823d);
//        List<Route> routes = router.getRoutesNear(c, 500);
//        System.out.println("Routes Nerby " + routes.size());
//        Map<String, List<Route>>  map = router.getRoutesNearByBuses(routes, 4);
    }

    @EJB
    RoutesRuntime r;

    private void printTotalDistance() {
        for (Route route : r.getRoutes()) {
            Route r1 = route;
//            double totaldistance = router.getTotalRouteDistance(r1.getPathDecode1());
//            System.out.println("TotalDistance:  " + r1.getName() + " " + (totaldistance / 1000) + " km");
        }

    }
    
//    private void paint(){
//        
//        Coordinate[] c = rt2.paintRoute();
//        for (Coordinate c1 : c) {
//            System.out.println(c1.latitude);
//        }
//    }

}
