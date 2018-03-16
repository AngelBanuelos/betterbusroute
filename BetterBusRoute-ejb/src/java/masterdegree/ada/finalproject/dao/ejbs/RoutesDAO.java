/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.dao.ejbs;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import masterdegree.ada.finalproject.controller.HuffmanTreeInstance;
import masterdegree.ada.finalproject.controller.RoutesRuntime;
import masterdegree.ada.finalproject.dao.GenericDAO;
import masterdegree.ada.finalproject.dao.ejbs.ejblocal.RouteDAOLocal;
import masterdegree.ada.finalproject.model.Route;
import masterdegree.ada.tree.HuffmanCode;

/**
 *
 * @author Angel.Sahagun
 */
@Stateless
@LocalBean
public class RoutesDAO extends GenericDAO<Route> implements RouteDAORemote, RouteDAOLocal {

    @EJB
    HuffmanTreeInstance tree;

    @EJB
    RoutesRuntime runtimeRoutes;

    public Route add(String route, String name, String path1, String path2) throws Exception {
        Route routeO = new Route(0, route, name, " ", " ");
        HuffmanCode code = new HuffmanCode(tree.getTree());
        code.code(path1);
        routeO.setPathbyte1(code.getByte());
        code.code(path2);
        routeO.setPathbyte2(code.getByte());
        return add(routeO);
    }

    public List<Route> getAll() {
        String sqlQuery = "SELECT u from Route u ";
        TypedQuery<Route> query
                = em.createQuery(sqlQuery, Route.class);
        List<Route> results = query.getResultList();
        return results;
    }

    public Route findByName(String name) throws NoResultException, NonUniqueResultException {
        Query query = em.createNamedQuery("Route.findByName", Route.class);
        query.setParameter("name", name);
        Route route = (Route) query.getSingleResult();
        Route temp = (route != null ? findById(route.getId()) : null);
        return (temp != null ? temp : route);
    }

    @Override
    public Route findById(long routeID) {
        Route route = runtimeRoutes.getRoute(routeID);
        System.out.println("hashRoute:  findById ----> " + route);
        if (route != null) {
            return route;
        }
        return super.find((int) routeID);
    }

}
