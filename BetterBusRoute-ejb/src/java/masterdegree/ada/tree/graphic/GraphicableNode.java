/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.tree.graphic;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Interfaces para generacion de arbol grafico
 *
 * @author Angel.Sahagun
 */
public interface GraphicableNode extends Serializable {

    @Override
    public String toString();

    public boolean isLeaf();

    public boolean isTop();

    public LinkedList<GraphicableNode> getChildren();

}
