/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.investigation;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import javax.ejb.EJB;
import masterdegree.ada.finalproject.controller.RuntimeConstants;
import masterdegree.ada.finalproject.model.Route;
import masterdegree.ada.tree.HuffmanCode;
import masterdegree.ada.tree.HuffmanTree;

/**
 *
 * @author angel_banuelos
 */
public class EncodeRoutesHuffman {

    public static void main(String[] args) throws Exception {
        String[] routes
                = readRoutes("/Users/angel_banuelos/NetBeansProjects/MasterDegree/BetterBusRoute/BetterBusRoute-ejb/src/java/masterdegree/ada/finalproject/investigation/bulkFile").split("\n");
        int i = 0;

        System.out.println("Routes: " + routes.length);
        HuffmanTree tree = new HuffmanTree(RuntimeConstants.alphabet, RuntimeConstants.occurrences);
        tree.printHuffmanCode();
        Route[] route = new Route[routes.length];
        

        while (i < routes.length) {
            String[] info = routes[i].split("\\|");
            System.out.println(info.length);
            if (info.length == 4) {
                route[i] = new Route();
                HuffmanCode code = new HuffmanCode(tree);
                route[i].setName(info[3]);
                code.code(info[1]);
                System.out.println(code.getByteArrayInputStream().toString());
                route[i].setPath1("");
                System.out.println("" + route[i].getName() + "|"
                        + route[i].getPath1() + "|");

            }
            System.out.println("route: " + i);
            i++;
        }
    }

    private static String readRoutes(String filename) throws Exception {
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("Exists");
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String textHelper, text = "";
        while ((textHelper = br.readLine()) != null) {
            text += textHelper + "\n";
        }
        br.close();
        return text;
    }

}
