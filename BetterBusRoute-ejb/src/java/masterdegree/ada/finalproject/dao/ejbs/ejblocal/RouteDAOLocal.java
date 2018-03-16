/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdegree.ada.finalproject.dao.ejbs.ejblocal;

import java.util.List;
import javax.ejb.Local;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import masterdegree.ada.finalproject.model.Route;

/**
 *
 * @author angel_banuelos
 */
@Local
public interface RouteDAOLocal {
    
    public Route add(String route, String name, String path1, String path2) throws Exception;
    
    public List<Route> getAll();
    
    public Route findByName(String name) throws NoResultException, NonUniqueResultException;
    
    public Route findById(long routeID);
    
}
