/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import masterdegree.ada.finalproject.model.Route;
import masterdegree.ada.graph.GraphConstants;
import masterdegree.ada.graph.Kruskal;

/**
 *
 * @author Angel.Sahagun
 */
public class Graph {

    private double[][] graph;
    private List<Route> routes;
    private Coordinate start;
    private Coordinate end;
    private int ratio;
    PriorityQueue<Helper> paths;

    Graph(List<Route> routes, Coordinate start, Coordinate end, int ratio) {
        this.routes = routes;
        this.start = start;
        this.end = end;
        this.ratio = ratio;
        createGraph();
    }

    public double[][] getGraph() {
        return graph;
    }

    public PriorityQueue<Helper> getPaths() {
        return paths;
    }

    private void createGraph() {
        int i = 0;
        Route current = (routes.size() > 0) ? routes.get(i) : null;
        Route root[] = new Route[routes.size()];
        paths = new PriorityQueue();
        Helper helper = new Helper();
        while (true) {
            helper.start = start;
            helper.end = end;
            helper.intersections.add(current);
            if (current.getParent() == null) {
                root[i] = current; // ruta inicial
                if (i == 2) {
                    System.out.println("Stop");
                }
                i++;
                helper.busses = helper.intersections.size();
                helper.listToMatrix = createNewRoute(helper.intersections);
                helper.distance = getTotalRouteDistance(helper.listToMatrix);
                paths.add(helper);
                if (i < routes.size()) {
                    helper = new Helper();
                    current = routes.get(i);
                } else {
                    break;
                }
            } else {
                current = current.getParent();
            }
        }
    }

    private List<Coordinate> createNewRoute(List<Route> intersections) {
        int n = intersections.size();
        List<Coordinate> tempRoute = new ArrayList();
        for (int i = n - 1; i >= 0; i--) {
            List<Coordinate> currentPath;
            if (n == 1) { // Solo un camion 
                tempRoute = getCoordinatesBetweenV2(start, end, intersections.get(i));
                if (tempRoute == null) {
                    tempRoute = intersections.get(i).getPathDecode1();
                }
            } else if (i == (n - 1)) {//start
                currentPath = getCoordinatesBetweenV2(start, intersections.get(i - 1).getIntersection(), intersections.get(i));
                if (currentPath == null) {
                    currentPath = intersections.get(i).getPathDecode1();
                }
                tempRoute.addAll(currentPath);

            } else if (i == 0) {//end
                currentPath = getCoordinatesBetweenV2(intersections.get(i).getIntersection(), end, intersections.get(i));
                if (currentPath == null) {
                    currentPath = intersections.get(i).getPathDecode1();
                }
                tempRoute.addAll(currentPath);
            } else { // between routes
                currentPath = getCoordinatesBetweenV2(intersections.get(i).getIntersection(), intersections.get(i - 1).getIntersection(), intersections.get(i));
                if (currentPath == null) {
                    currentPath = intersections.get(i).getPathDecode1();
                }
                tempRoute.addAll(currentPath);
                i--;
            }
        }
        return tempRoute;
    }

    private List<Coordinate> getCoordinatesBetween(Coordinate start, Coordinate end, Route route) {
        List<Coordinate> cordinatesBetween = null;

        int startInPath = -1;
        int endInPath = -1;
        for (int i = 0; i < route.getPathDecode1().size(); i++) {
            if (startInPath == -1 && getDistance(start, route.getPathDecode1().get(i)) < (ratio + 1)) {
                startInPath = i;
            }
            if (endInPath == -1 && getDistance(end, route.getPathDecode1().get(i)) < (ratio + 1)) {
                endInPath = i;
            }
            if (startInPath != -1 && endInPath != -1) {
                break;
            }
        }
        if (startInPath != -1 && endInPath != -1) {
            int aux = 0;
            if (startInPath > endInPath) {
                aux = endInPath;
                endInPath = startInPath;
                startInPath = aux;
            }
            cordinatesBetween = new ArrayList();
            for (int i = startInPath; i < endInPath; i++) {
                cordinatesBetween.add(route.getPathDecode1().get(i));
            }
            return cordinatesBetween;
        }
        startInPath = -1;
        endInPath = -1;
        for (int i = 0; i < route.getPathDecode2().size(); i++) {
            if (startInPath == -1 && getDistance(start, route.getPathDecode2().get(i)) < (ratio + 1)) {
                startInPath = i;
            }
            if (endInPath == -1 && getDistance(end, route.getPathDecode2().get(i)) < (ratio + 1)) {
                endInPath = i;
            }
            if (startInPath != -1 && endInPath != -1) {
                break;
            }
        }
        if (startInPath != -1 && endInPath != -1) {
            int aux = 0;
            if (startInPath > endInPath) {
                aux = endInPath;
                endInPath = startInPath;
                startInPath = aux;
            }
            cordinatesBetween = new ArrayList();
            for (int i = startInPath; i < endInPath; i++) {// cuando start es mayor que end hacer procedimiento
                cordinatesBetween.add(route.getPathDecode2().get(i));
            }
            return cordinatesBetween;
        }

        return cordinatesBetween;
    }

    private int[] getPointsBetween(Coordinate start,
            Coordinate end, ArrayList<Coordinate> coords) {
        int[] points = new int[2];
        int startInPath = -1;
        int endInPath = -1;
        int i = 0;
        for (Coordinate coord : coords) {
            if (startInPath == -1 && getDistance(start, coord) < (ratio + 1)) {
                startInPath = i;
            }
            if (endInPath == -1 && getDistance(end, coord) < (ratio + 1)) {
                endInPath = i;
            }
            if (startInPath != -1 && endInPath != -1) {
                break;
            }
            i++;
        }
        points[0] = startInPath;
        points[1] = endInPath;
        return points;
    }

    /**
     *
     * @param start
     * @param end
     * @param route
     * @return
     */
    private List<Coordinate> getCoordinatesBetweenV2(Coordinate start, Coordinate end, Route route) {
        List<Coordinate> cordinatesBetween = null;

        int[] points = null;
        points = getPointsBetween(start, end, route.getPathDecode1());
        if (points[0] != -1 && points[1] != -1) {
            int startAux = points[0];
            int endAux = points[1];
            if (points[0] > points[1]) {
                cordinatesBetween = new ArrayList();
                for (int i = startAux; i > endAux; i--) {
                    cordinatesBetween.add(route.getPathDecode1().get(i));
                }
            } else {
                cordinatesBetween = new ArrayList();
                for (int i = startAux; i < endAux; i++) {
                    cordinatesBetween.add(route.getPathDecode1().get(i));
                }

            }
            return cordinatesBetween;
        }
        points = getPointsBetween(start, end, route.getPathDecode2());
        if (points[0] != -1 && points[1] != -1) {
            int startAux = points[0];
            int endAux = points[1];
            if (points[0] > points[1]) {
                cordinatesBetween = new ArrayList();
                for (int i = startAux; i > endAux; i--) {// cuando start es mayor que end hacer procedimiento
                    cordinatesBetween.add(route.getPathDecode2().get(i));
                }
            } else {
                cordinatesBetween = new ArrayList();
                for (int i = startAux; i < endAux; i++) {// cuando start es mayor que end hacer procedimiento
                    cordinatesBetween.add(route.getPathDecode2().get(i));
                }
            }
            return cordinatesBetween;
        }

        return cordinatesBetween;
    }

    class Helper implements Comparable<Helper> {

        int busses = 0;
        List<Route> intersections = new ArrayList();
        double distance = 0.0;
        List<Coordinate> listToMatrix;
        Coordinate start;
        Coordinate end;

        @Override
        public int compareTo(Helper o) {
            return Double.compare(this.distance, o.distance);
        }

    }

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

    public double getTotalRouteDistance(List<Coordinate> list) {
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

}
