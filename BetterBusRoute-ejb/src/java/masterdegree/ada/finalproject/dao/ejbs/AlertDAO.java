/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.dao.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.finalproject.dao.GenericDAO;
import masterdegree.ada.finalproject.dao.ejbs.ejblocal.AlertDAOLocal;
import masterdegree.ada.finalproject.model.Alert;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author angel_banuelos
 */
@Stateless
public class AlertDAO extends GenericDAO<Alert> implements AlertDAORemote, AlertDAOLocal{

    public Alert add(String name, Coordinate c, UserBbr userID, int ratio) {
        Alert alert = new Alert(0, name, (float) c.getLat(), (float) c.getLng());
        alert.setUserid(userID);
        alert.setRatio(ratio);
        return add(alert);
    }

    public List<Alert> getAll() {
        Query query = em.createNamedQuery("Alert.findAll", Alert.class);
        List<Alert> results = query.getResultList();
        return results;
    }

    @Override
    public void deleteAlert(int alertID) {
        super.remove(alertID);
    }

    @Override
    public void clearAlerts(UserBbr userId) {
        for (Alert alert : userId.getAlertCollection()) {
            super.remove(alert);
        }
    }
    
}
