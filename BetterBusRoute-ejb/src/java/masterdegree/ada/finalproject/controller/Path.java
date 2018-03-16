/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import masterdegree.ada.tree.HuffmanDecode;

/**
 *
 * @author Angel.Sahagun
 */
@Stateless
public class Path {

    @EJB
    HuffmanTreeInstance tree;
    ArrayList<Coordinate> pathList;

    public ArrayList<Coordinate> createPath(byte[] codedPath) {
        this.pathList = new ArrayList();
        HuffmanDecode decode = new HuffmanDecode(tree.getTree());
        String[] path = decode.decode(decode.bytesToBitSet(codedPath)).split(",");
        for (int i = 0; i < path.length; i = i + 2) {
            if ((i + 1) < path.length) {
                Coordinate point = new Coordinate(Double.parseDouble(path[i]), Double.parseDouble(path[i + 1]));
                pathList.add(point);
            }
        }
        return pathList;
    }

}
