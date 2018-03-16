/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.ws.rest;

/**
 *
 * @author angel_banuelos
 */
public class RSConstants {

    /* Remote Paths */
    private static final String LOOKUP_NAME = "java:global/BetterBusRoute/BetterBusRoute-ejb/";
    private static final String LOOKUP_CLASS_CONTROLLER = "!masterdegree.ada.finalproject.controller.";
    private static final String LOOKUP_CLASS_DAO = "!masterdegree.ada.finalproject.dao.ejbs.";

    /* Lookup Paths for remote interface */
    public static final String ROUTER_LU = LOOKUP_NAME + "Router" + LOOKUP_CLASS_CONTROLLER+ "RouterRemote";
    public static final String USER_LU = LOOKUP_NAME + "UserDAO" + LOOKUP_CLASS_DAO + "UserDAORemote";
    public static final String SAVED_PLACE_LU = LOOKUP_NAME + "SavedPlaceDAO" + LOOKUP_CLASS_DAO + "SavedPlaceDAORemote";
    public static final String ROUTE_LU = LOOKUP_NAME + "RoutesDAO" + LOOKUP_CLASS_DAO + "RouteDAORemote";
    public static final String HISTORY_LU = LOOKUP_NAME + "HistoryDAO" + LOOKUP_CLASS_DAO + "HistoryDAORemote";
    public static final String ALERT_LU = LOOKUP_NAME + "AlertDAO" + LOOKUP_CLASS_DAO + "AlertDAORemote";

}
