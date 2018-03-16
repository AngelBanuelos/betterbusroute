/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller.ejblocal;

import javax.ejb.Local;
import masterdegree.ada.finalproject.controller.Coordinate;

/**
 *
 * @author Angel.Sahagun
 */
@Local
public interface Router2Local {
 
    public Coordinate[] paintRoute();
    
}
