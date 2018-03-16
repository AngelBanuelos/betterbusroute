/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.tree.graphic;

import java.io.Serializable;

/**
 * Interface para la clase que construye el arbol.
 *
 * @author Angel.Sahagun
 */
public interface GraphicableTree extends Serializable {

    @Override
    public String toString();

    public GraphicableNode getNode();

}
