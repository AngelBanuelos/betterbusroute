/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;

import masterdegree.ada.finalproject.controller.ejblocal.RouterLocal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import masterdegree.ada.finalproject.dao.ejbs.RoutesDAO;
import masterdegree.ada.finalproject.model.Route;
import masterdegree.ada.graph.GraphConstants;
import masterdegree.ada.graph.Kruskal;

/**
 *
 * @author Angel.Sahagun
 */
@Stateless
public class Router implements RouterRemote, RouterLocal {

    @EJB
    RoutesRuntime runtimeRoutes;

    @EJB
    RoutesDAO routeDAO;

    /**
     *
     * @param c
     * @return
     */
    public Graph getGraph(Coordinate c) {
        return null;
    }

    /**
     *
     * @param routesNearby
     * @return
     */
    public Coordinate[] getIntersections(List<Route> routesNearby) {
        return null;
    }

    /**
     * var R = 6371000; // metres var φ1 = lat1.toRadians(); var φ2 =
     * lat2.toRadians(); var Δφ = (lat2-lat1).toRadians(); var Δλ =
     * (lon2-lon1).toRadians();
     *
     * var a = Math.sin(Δφ/2) * Math.sin(Δφ/2) + Math.cos(φ1) * Math.cos(φ2) *
     * Math.sin(Δλ/2) * Math.sin(Δλ/2); var c = 2 * Math.atan2(Math.sqrt(a),
     * Math.sqrt(1-a));
     *
     * var d = R * c;
     *
     * @param a
     * @param b
     * @return
     */
    public double getDistance(Coordinate a, Coordinate b) {

        double aLatitude = a.getLat();
        double bLatitude = b.getLat();

        double R = 6371000;
        double dLat = Math.toRadians((bLatitude - aLatitude));
        double dLong = Math.toRadians((b.getLng() - a.getLng()));

        aLatitude = Math.toRadians(aLatitude);
        bLatitude = Math.toRadians(bLatitude);

        double A = haversin(dLat) + Math.cos(aLatitude) * Math.cos(bLatitude) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(A), Math.sqrt(1 - A));
        return R * c;
    }

    public double haversin(double val) {
        return (Math.sin(val / 2) * Math.sin(val / 2));
    }

    public double getTotalDistancePath1ByRoute(Route route) {
        if (route == null || route.getPathDecode1() == null) {
            return 0.0;
        }
        double total = 0;
        for (int i = 0; i < route.getPathDecode1().size(); i = i + 2) {
            if ((i + 1) < route.getPathDecode1().size()) {
                total += getDistance(route.getPathDecode1().get(i), route.getPathDecode1().get(i + 1));
            }
        }
        return total;
    }

    public double getTotalDistancePath2ByRoute(Route route) {
        double total = 0;
        for (int i = 0; i < route.getPathDecode2().size(); i = i + 2) {
            if ((i + 1) < route.getPathDecode2().size()) {
                total += getDistance(route.getPathDecode2().get(i), route.getPathDecode2().get(i + 1));
            }
        }
        return total;
    }

    /**
     * Using backtracking
     *
     * @param c
     * @param ratio
     * @return
     */
    public List<Route> getRoutesNearBy(Coordinate c, int ratio) {
        List<Route> nearbyRoutes = new ArrayList();
        Set<Route> uniques = new HashSet();
        final List<Route> routes = runtimeRoutes.getRoutes();
        for (Route route : routes) {
            Route current = route;
            if (current.getIntersection() != null) {
                current = new Route();
                current.setId(route.getIndex());
                current.setName(route.getName());
                current.setRoute(route.getRoute());
                current.setPathDecode1(route.getPathDecode1());
                current.setPathDecode2(route.getPathDecode2());
            }
            for (Coordinate c2 : current.getPathDecode2()) {
                if (current.getIntersection() != null) {
                    break;
                }
                if (getDistance(c, c2) < (ratio + 1)) {
                    current.setIntersection(c);
                    uniques.add(current);
                    break;
                }
            }
            for (Coordinate c2 : current.getPathDecode1()) {
                if (current.getIntersection() != null) {
                    break;
                }
                if (getDistance(c, c2) < (ratio + 1)) {
                    current.setIntersection(c);
                    uniques.add(current);
                    break;
                }
            }
        }
        nearbyRoutes.addAll(uniques);
        return nearbyRoutes;
    }

    /**
     * Using backtracking
     *
     * @param c
     * @param ratio
     * @return
     */
    public List<Route> getRoutesNearByParallel(final Coordinate c, final int ratio) {
        final List<Route> nearbyRoutes = new ArrayList();
        final Set<Route> uniques = new HashSet();
        final List<Route> routes = runtimeRoutes.getRoutes();
        Parallel.For(routes, new Parallel.Operation<Route>() {
            @Override
            public void perform(Route route) {
                Route current = route;
                if (current.getIntersection() != null) {
                    current = new Route();
                    current.setId(route.getIndex());
                    current.setName(route.getName());
                    current.setRoute(route.getRoute());
                    current.setPathDecode1(route.getPathDecode1());
                    current.setPathDecode2(route.getPathDecode2());
                }
                for (Coordinate c2 : current.getPathDecode2()) {
                    if (current.getIntersection() != null) {
                        break;
                    }
                    if (getDistance(c, c2) < (ratio + 1)) {
                        current.setIntersection(c);
                        uniques.add(current);
                        break;
                    }
                }
                for (Coordinate c2 : current.getPathDecode1()) {
                    if (current.getIntersection() != null) {
                        break;
                    }
                    if (getDistance(c, c2) < (ratio + 1)) {
                        current.setIntersection(c);
                        uniques.add(current);
                        break;
                    }
                }
            }
        });

        nearbyRoutes.addAll(uniques);
        return nearbyRoutes;
    }

    public Coordinate[] paintPath1(@PathParam("name") String name) {
        Route route = routeDAO.findByName(name);
        Coordinate[] cords = (Coordinate[]) route.getPathDecode1().toArray();
        return cords;
    }

    public double getTotalRouteDistance(ArrayList<Coordinate> list) {
        double distance = 0;
        double[][] matrix = new double[list.size()][list.size()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0.0) {
                    continue;
                }
                if (i == j) {
                    matrix[i][j] = GraphConstants.INF;
                } else {
                    matrix[i][j] = getDistance(list.get(i), list.get(j));
                    matrix[j][i] = GraphConstants.INF;
                }
            }
        }
        Kruskal k = new Kruskal();
        List<Kruskal.Edge> d = k.kruskal(matrix, list.size() - 1);
        for (Kruskal.Edge edge : d) {
            distance += edge.weight;
        }
        return distance;
    }

    public Map<String, List<Route>> getRoutesNearByBuses(List<Route> routes, int times) {
        HashSet<Route> differentR = new HashSet();
        HashMap<String, List<Route>> rRoutes = new HashMap();
        Stack<Route> st = new Stack<>();
        for (Route route : routes) {
            st.push(route);
            differentR.add(route);
        }

        while (!st.isEmpty()) {
            Route r = st.pop();
            List<Route> l = null;

            for (Coordinate c : r.getPathDecode1()) {
                l = getRoutesNearBy(c, 100);
            }

            for (Route route : l) {
                differentR.add(route);
            }

            rRoutes.put(r.getName(), l);
        }
        return rRoutes;
    }

    @Override
    public Coordinate[] paintRoute(int id, int pathNumber) throws Exception {
        ArrayList<Coordinate> path = null;
        if (pathNumber == 1) {
            path = runtimeRoutes.getRoutes().get(id).getPathDecode1();
        } else if (pathNumber == 2) {
            path = runtimeRoutes.getRoutes().get(id).getPathDecode2();
        } else {
            throw new Exception("There is not path number avalible");
        }
        Coordinate[] path1 = new Coordinate[path.size()];
        path.toArray(path1);
        return path1;
    }

    @Override
    public Coordinate[] paintRoute(String routeName, int pathNumber) throws Exception {
        ArrayList<Coordinate> path = null;
        if (pathNumber == 1) {
            path = runtimeRoutes.getRoutes().get(0).getPathDecode1();
        } else if (pathNumber == 2) {
            path = runtimeRoutes.getRoutes().get(0).getPathDecode2();
        } else {
            throw new Exception("There is not path number avalible");
        }
        Coordinate[] path1 = new Coordinate[path.size()];
        path.toArray(path1);
        return path1;
    }

    @Override
    public RouteVO[] paintNearbyRoutes(Coordinate origin, Coordinate destination, int ratio, int budget) throws Exception {
        Set<Route> uniques = new HashSet();
        ArrayList<Route> routes = new ArrayList();
        routes = (ArrayList<Route>) getRoutesNearBy(origin, ratio);
        for (Route route : routes) {
            uniques.add(route);
        }
        for (Route route : getRoutesNearBy(destination, ratio)) {
            uniques.add(route);
        }
        if (routes.isEmpty()) {
            throw new Exception("There aren't nerby routes");
        }
        RouteVO[] routeVO = routeVOMapper(uniques);
        return routeVO;
    }

    @Override
    public PathVO[] paintCheperBusRoute(Coordinate origin, Route initialSelectedRoute, int ratio, Coordinate destination) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @EJB
    private Path path;

    @Override
    public PathVO[] paintFasterBusRoute(Coordinate origin, Route initialSelectedRoute, int ratio, Coordinate destination) {
        try {

            long start = System.currentTimeMillis();
            long startI = start;
            if (initialSelectedRoute.getPathDecode1() == null
                    || initialSelectedRoute.getPathDecode2() == null) {
                System.out.println(" initialSelectedRoute is null :( ");
                initialSelectedRoute.setPathDecode1(path.createPath(initialSelectedRoute.getPathbyte1()));
                initialSelectedRoute.setPathDecode2(path.createPath(initialSelectedRoute.getPathbyte2()));
            }
            long end = System.currentTimeMillis();
            System.out.println("createPath Time:  " + (end - start));
            PathVO[] pathVO = null;
            start = System.currentTimeMillis();
            List<Route> destinationRoutes = getRoutesNearByParallel(destination, ratio);
            end = System.currentTimeMillis();
            System.out.println("destinationRoutes Time:  " + (end - start));

            if (destinationRoutes != null && !destinationRoutes.isEmpty()) {
                start = System.currentTimeMillis();
                List<Route> totalRouteOfRoutes = getNearByRoute(initialSelectedRoute);
                end = System.currentTimeMillis();
                System.out.println("totalRouteOfRoutes Time:  " + (end - start));

                start = System.currentTimeMillis();
                Graph g = createGaph(totalRouteOfRoutes, destinationRoutes, origin, destination, ratio);
                end = System.currentTimeMillis();
                System.out.println("createGaph Time:  " + (end - start));

                PriorityQueue<Graph.Helper> l = (g != null ? g.getPaths() : null);
                if (l != null && !l.isEmpty()) {
                    start = System.currentTimeMillis();
                    pathVO = PathVOMaper(l);
                    end = System.currentTimeMillis();
                    System.out.println("PathVOMaper Time:  " + (end - start));

                }
            }
            long endI = System.currentTimeMillis();
            System.out.println("Total Time:  " + (endI - startI));
            return pathVO;
        } finally {
            clearParent();
        }
    }

    private RouteVO[] routeVOMapper(Set<Route> routes) {
        RouteVO[] routesVO = new RouteVO[routes.size()];
        int i = 0;
        for (Route route : routes) {
            routesVO[i] = new RouteVO();
            if (route.getPathDecode1() != null) {
                Coordinate[] path1 = new Coordinate[route.getPathDecode1().size()];
                route.getPathDecode1().toArray(path1);
                routesVO[i].setPath1(path1);
                routesVO[i].setTotalDistancePath1(getTotalDistancePath1ByRoute(route));
            }
            if (route.getPathDecode2() != null) {
                Coordinate[] path2 = new Coordinate[route.getPathDecode2().size()];
                route.getPathDecode2().toArray(path2);
                routesVO[i].setPath2(path2);
                routesVO[i].setTotalDistancePath2(getTotalDistancePath2ByRoute(route));
            }
            routesVO[i].setRouteName(route.getName());
            long id = route.getIndex();
            routesVO[i].setRouteID(id);
            i++;
        }
        return routesVO;
    }

    private List<Route> getNearByRoute(Route initialSelectedRoute) {
        List<Route> nearbyRoutes = new ArrayList();
        Set<Route> uniques = new HashSet();
        uniques.add(initialSelectedRoute);
        Stack<Route> stack = new Stack();
        Collections.synchronizedList(stack);
        stack.add(initialSelectedRoute);
        List<Route> process = new ArrayList();
        int i = 0, j = 0;
        int swapCount = stack.size();
        long start1 = System.currentTimeMillis();
        long end1 = 0;
        while (!stack.isEmpty() && i < 2) {
            j++;
            final Route current = stack.pop();
            process.add(current);
            ArrayList<Coordinate> path1 = current.getPathDecode1();
            ArrayList<Coordinate> path2 = current.getPathDecode2();
            List<Route> nearbyRotes = new ArrayList();
            for (int k = 0; k < path1.size(); k = k + 20) {//10
                nearbyRotes.addAll(getRoutesNearByParallel(path1.get(k), 100));
            }
            for (int k = 0; k < path2.size(); k = k + 20) {//10
                nearbyRotes.addAll(getRoutesNearByParallel(path2.get(k), 100));
            }
            for (Route route : nearbyRotes) {
                if (!route.equals(current)) {
                    route.setParent(current);
                }
                uniques.add(route);//poner el padre
            }
            nearbyRotes.clear();
            for (Route route : uniques) {
                if (Collections.binarySearch(process, route) != -1) {
                    if (Collections.binarySearch(stack, route) == -1) {
                        if (!route.equals(current)) {
                            stack.push(route);
                        }
                    }
                }
            }
            if (swapCount == j) {
                i++;
                j = 0;
                swapCount = stack.size();
            }
        }
        end1 = System.currentTimeMillis();
        System.out.println("getNearByRouteParallel " + (end1 - start1) + " Uniques: " + uniques.size());
        nearbyRoutes.addAll(uniques);
        return nearbyRoutes;
    }

    private List<Route> getNearByRouteSerial(Route initialSelectedRoute) {
        List<Route> nearbyRoutes = new ArrayList();
        Set<Route> uniques = new HashSet();
        uniques.add(initialSelectedRoute);
        Stack<Route> stack = new Stack();
        stack.add(initialSelectedRoute);
        List<Route> process = new ArrayList();
        int i = 0, j = 0;
        int swapCount = stack.size();
        while (!stack.isEmpty() && i < 3) {
            j++;
            Route current = stack.pop();
            process.add(current);
            ArrayList<Coordinate> path1 = current.getPathDecode1();
            ArrayList<Coordinate> path2 = current.getPathDecode2();
            List<Route> nearbyRotes = null;
            for (int k = 0; k < path1.size(); k = k + 20) {//10
                if (nearbyRotes == null) {
                    nearbyRotes = getRoutesNearBy(path1.get(k), 100);
                } else {
                    nearbyRotes.addAll(getRoutesNearBy(path1.get(k), 100));
                }
            }
            for (int k = 0; k < path2.size(); k = k + 20) {//10
                if (nearbyRotes == null) {
                    nearbyRotes = getRoutesNearBy(path2.get(k), 100);
                } else {
                    nearbyRotes.addAll(getRoutesNearBy(path2.get(k), 100));
                }
            }
            for (Route route : nearbyRotes) {
                if (!route.equals(current)) {
                    route.setParent(current);
                }
                uniques.add(route);//poner el padre
            }
            nearbyRotes.clear();
            for (Route route : uniques) {
                if (Collections.binarySearch(process, route) != -1) {
                    if (Collections.binarySearch(stack, route) == -1) {
                        if (!route.equals(current)) {
                            stack.push(route);
                        }
                    }
                }
            }
            if (swapCount == j) {
                i++;
                j = 0;
                swapCount = stack.size();
            }
        }
        nearbyRoutes.addAll(uniques);
        return nearbyRoutes;
    }

    private List<Route> getNearByRouteParallel(Route initialSelectedRoute) {
        List<Route> nearbyRoutes = new ArrayList();
        Set<Route> uniques = new HashSet();
        uniques.add(initialSelectedRoute);
        Stack<Route> stack = new Stack();
        stack.add(initialSelectedRoute);
        List<Route> process = new ArrayList();
        int i = 0, j = 0;
        int swapCount = stack.size();
        long start1 = System.currentTimeMillis();
        long end1 = 0;
        while (!stack.isEmpty() && i < 2) {
            j++;
            Route current = stack.pop();
            process.add(current);
            final List<Coordinate> path1 = current.getPathDecode1();
            final List<Coordinate> path2 = current.getPathDecode2();
            final List<Route> nearbyRotes = new ArrayList();

            Parallel.For(path1, new Parallel.Operation<Coordinate>() {
                public void perform(Coordinate param) {
                    nearbyRotes.addAll(getRoutesNearBy(param, 100));
                }
            });

            Parallel.For(path2, new Parallel.Operation<Coordinate>() {
                public void perform(Coordinate param) {
                    nearbyRotes.addAll(getRoutesNearBy(param, 100));
                }
            });
            for (Route route : nearbyRotes) {
                if (route == null) {
                    continue;
                }
                if (!route.equals(current)) {
                    route.setParent(current);
                }
                uniques.add(route);//poner el padre
            }
            nearbyRotes.clear();
            for (Route route : uniques) {
                if (route == null) {
                    continue;
                }
                if (Collections.binarySearch(process, route) != -1) { // ya fue procesada?
                    if (Collections.binarySearch(stack, route) == -1) { // Actualmente esta en la lista?
                        stack.push(route);
                    }
                }
            }
            if (swapCount == j) {
                i++;
                j = 0;
                swapCount = stack.size();
            }
        }
        end1 = System.currentTimeMillis();
        System.out.println("getNearByRoute time: " + (end1 - start1) + "Uniques: " + uniques.size());
        nearbyRoutes.addAll(uniques);
        return nearbyRoutes;
    }

    private Graph createGaph(List<Route> totalRouteOfRoutes, List<Route> destinationRoutes, Coordinate origin, Coordinate destination, int ratio) {
        Graph graph = null;
        List<Route> communRoutes = getCommons(totalRouteOfRoutes, destinationRoutes);
        if (!communRoutes.isEmpty()) {
            graph = new Graph(communRoutes, origin, destination, ratio);
        }

        return graph;
    }

    private boolean existsRoute(Graph g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private RouteVO[] calcFasterBusRoute(Graph g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existsRoute(Coordinate origin, Route initialSelectedRoute, int ratio, Coordinate destination) {
        try {
            initialSelectedRoute.setPathDecode1(path.createPath(initialSelectedRoute.getPathbyte1()));
            initialSelectedRoute.setPathDecode2(path.createPath(initialSelectedRoute.getPathbyte2()));
            RouteVO[] routePath = null;

            List<Route> destinationRoutes = getRoutesNearBy(destination, ratio);
            if (destinationRoutes == null || destinationRoutes.isEmpty()) {
                return false;
            }
            List<Route> totalRouteOfRoutes = getNearByRoute(initialSelectedRoute);

            List<Route> l = getCommons(totalRouteOfRoutes, destinationRoutes);

            if (l.size() != 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            clearParent();
        }
    }

    private List<Route> getCommons(List<Route> totalRouteOfRoutes, List<Route> destinationRoutes) {

        Set<Route> uniques = new HashSet();
        List<Route> communRoutes = new ArrayList();

        for (Route route : totalRouteOfRoutes) {
            for (Route route1 : destinationRoutes) {
                if (route.equals(route1)) {
                    uniques.add(route);
                }
            }
        }
        communRoutes.addAll(uniques);

        return communRoutes;
    }

    private void clearParent() {
        for (Route route : runtimeRoutes.getRoutes()) {
            if (route.getParent() != null) {
                route.setParent(null);
                route.setIntersection(null);
            }
        }
    }

    private PathVO[] PathVOMaper(PriorityQueue<Graph.Helper> l) {
        PathVO[] path = new PathVO[l.size()];
        int i = 0;
        while (!l.isEmpty()) {
            if(i == 2){
                System.out.println("Stop ");
            }    
            Graph.Helper helper = l.poll();
            path[i] = new PathVO();
            path[i].setBusses(helper.busses);
            Coordinate[] coo = new Coordinate[helper.listToMatrix.size()];
            helper.listToMatrix.toArray(coo);
            path[i].setPath(coo);
            path[i].setDistance(helper.distance);
            Coordinate points[] = new Coordinate[helper.intersections.size() + 2];
            points[0] = helper.start;
            points[1] = helper.start;
            points[points.length - 1] = helper.end;
            int k = 2;
            for (int j = (helper.intersections.size() - 2); j >= 0 && j != -1; j--) {
                points[k] = helper.intersections.get(j).getIntersection();
                k++;
            }
            RoutePathVO[] routes = new RoutePathVO[helper.intersections.size()];
            for (int j = (helper.intersections.size() - 1); j >= 0; j--) {
                routes[j] = new RoutePathVO();
                routes[j].setName(helper.intersections.get(j).getName());
                routes[j].setId(helper.intersections.get(j).getIndex());
                routes[j].setPoint(helper.intersections.get(j).getIntersection());
            }
            path[i].setRoutes(routes);
            path[i].setPoints(points);
            i++;
        }
        return path;
    }

    @Override
    public PathVO[] paintFasterBusRouteSerial(Coordinate origin, Route initialSelectedRoute, int ratio, Coordinate destination) {
        try {

            long start = System.currentTimeMillis();
            long startI = start;
            if (initialSelectedRoute.getPathDecode1() == null
                    || initialSelectedRoute.getPathDecode2() == null) {
                System.out.println(" initialSelectedRoute is null :( ");
                initialSelectedRoute.setPathDecode1(path.createPath(initialSelectedRoute.getPathbyte1()));
                initialSelectedRoute.setPathDecode2(path.createPath(initialSelectedRoute.getPathbyte2()));
            }
            long end = System.currentTimeMillis();
            System.out.println("createPath Time:  " + (end - start));
            PathVO[] pathVO = null;
            start = System.currentTimeMillis();
            List<Route> destinationRoutes = getRoutesNearBy(destination, ratio);
            end = System.currentTimeMillis();
            System.out.println("destinationRoutes Time:  " + (end - start));

            if (destinationRoutes != null && !destinationRoutes.isEmpty()) {
                start = System.currentTimeMillis();
                List<Route> totalRouteOfRoutes = getNearByRouteSerial(initialSelectedRoute);
                end = System.currentTimeMillis();
                System.out.println("totalRouteOfRoutes Time:  " + (end - start));

                start = System.currentTimeMillis();
                Graph g = createGaph(totalRouteOfRoutes, destinationRoutes, origin, destination, ratio);
                end = System.currentTimeMillis();
                System.out.println("createGaph Time:  " + (end - start));

                PriorityQueue<Graph.Helper> l = (g != null ? g.getPaths() : null);
                if (l != null && !l.isEmpty()) {
                    start = System.currentTimeMillis();
                    pathVO = PathVOMaper(l);
                    end = System.currentTimeMillis();
                    System.out.println("PathVOMaper Time:  " + (end - start));

                }
            }
            long endI = System.currentTimeMillis();
            System.out.println("Total Time:  " + (endI - startI));
            return pathVO;
        } finally {
            clearParent();
        }
    }

}
