<%-- 
    Document   : readProperties
    Created on : Nov 29, 2015, 7:56:38 PM
    Author     : angel_banuelos
--%>

<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String userLocale = request.getParameter("lan");
    String file = "es";
    if("en".equals(userLocale.trim())){
        file = "en";
    }
    ResourceBundle resource = ResourceBundle.getBundle(file);

    String BbrMain_title = resource.getString("BbrMain.title");
    String BbrMain_origin = resource.getString("BbrMain.origin");
    String BbrMain_destination = resource.getString("BbrMain.destination");
    String BbrMain_budget = resource.getString("BbrMain.budget");
    String BbrMain_search = resource.getString("BbrMain.search");
    String BbrMain_clear = resource.getString("BbrMain.clear");
    
    String BbrNewAccount_title = resource.getString("BbrNewAccount.title");
    String BbrNewAccount_firstName = resource.getString("BbrNewAccount.firstName");
    String BbrNewAccount_lastName = resource.getString("BbrNewAccount.lastName");
    String BbrNewAccount_email = resource.getString("BbrNewAccount.email");
    String BbrNewAccount_password = resource.getString("BbrNewAccount.password");
    String BbrNewAccount_addUser = resource.getString("BbrNewAccount.addUser");
    
    String BbrNewPlace_title = resource.getString("BbrNewPlace.title");
    String BbrNewPlace_name = resource.getString("BbrNewPlace.name");
    String BbrNewPlace_location = resource.getString("BbrNewPlace.location");
    String BbrNewPlace_ratio = resource.getString("BbrNewPlace.ratio");
    String BbrNewPlace_user = resource.getString("BbrNewPlace.user");
    String BbrNewPlace_addPlace = resource.getString("BbrNewPlace.addPlace");
    
    String BbrNewRoute_title = resource.getString("BbrNewRoute.title");
    String BbrNewRoute_name = resource.getString("BbrNewRoute.name");
    String BbrNewRoute_path1 = resource.getString("BbrNewRoute.path1");
    String BbrNewRoute_path2 = resource.getString("BbrNewRoute.path2");
    String BbrNewRoute_addRoute = resource.getString("BbrNewRoute.addRoute");

%>