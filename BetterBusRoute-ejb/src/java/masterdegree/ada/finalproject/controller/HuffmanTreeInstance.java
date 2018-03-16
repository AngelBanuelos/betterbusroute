/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.controller;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import masterdegree.ada.tree.HuffmanTree;

/**
 *
 * @author Angel.Sahagun
 */
@Singleton
@LocalBean
@Startup
public class HuffmanTreeInstance {
    
    private HuffmanTree tree;
    
    @PostConstruct
    public void init(){
        tree = new HuffmanTree(RuntimeConstants.alphabet, RuntimeConstants.occurrences);
        tree.printHuffmanCode();
    }
    
    
    public HuffmanTree getTree(){
        return tree;
    }
    
}
