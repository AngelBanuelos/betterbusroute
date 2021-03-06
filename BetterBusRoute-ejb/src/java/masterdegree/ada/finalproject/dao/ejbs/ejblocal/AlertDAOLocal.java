/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.dao.ejbs.ejblocal;

import java.util.List;
import javax.ejb.Local;
import masterdegree.ada.finalproject.controller.Coordinate;
import masterdegree.ada.finalproject.model.Alert;
import masterdegree.ada.finalproject.model.UserBbr;

/**
 *
 * @author angel_banuelos
 */
@Local
public interface AlertDAOLocal {

    public Alert add(String name, Coordinate c, UserBbr userID, int ratio);

    public List<Alert> getAll();

    public void deleteAlert(int alertID);

    public void clearAlerts(UserBbr userId);

}
