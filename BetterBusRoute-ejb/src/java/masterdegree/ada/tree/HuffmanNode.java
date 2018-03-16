/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.tree;

import java.util.LinkedList;
import masterdegree.ada.search.Searching;
import masterdegree.ada.tree.graphic.GraphicableNode;

/**
 * Clase nodo para el arbol de Huffman
 *
 * @author Angel.Sahagun
 */
public class HuffmanNode implements GraphicableNode, Searching {

    private String word; // Complete word
    private int occurrenses; // occurrenses 
    private int index; //temporal index for searching in a LinkedList
    private short bit; // 0 or 1 
    private String binaryCode; // 10101
    private byte binaryCodeB;
    private char letter = '\u0000'; // if is leaf assign letter.
    private HuffmanNode childLeft;
    private HuffmanNode childRight;
    private LinkedList<GraphicableNode> childrenGraphic;

    public byte getBinaryCodeB() {
        return binaryCodeB;
    }

    public void setBinaryCodeB(byte binaryCodeB) {
        this.binaryCodeB = binaryCodeB;
    }

    public String getBinaryCode() {
        return binaryCode;
    }

    public void setBinaryCode(String binaryCode) {
        this.binaryCode = binaryCode.substring(1);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getOccurrenses() {
        return occurrenses;
    }

    public void setOccurrenses(int occurrenses) {
        this.occurrenses = occurrenses;
    }

    public short getBit() {
        return bit;
    }

    public void setBit(short bit) {
        this.bit = bit;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    /**
     * Si no se a agregado una letra significa que no es hoja
     *
     * @return
     */
    public boolean isLeaf() {
        if (letter != '\u0000') {
            return true;
        }
        return false;
    }

    public HuffmanNode getChildLeft() {
        return childLeft;
    }

    /**
     * Agrega el hijo izquierdo al nodo actual.
     *
     * @param childLeft
     */
    public void setChildLeft(HuffmanNode childLeft) {
        if (childrenGraphic == null) {
            childrenGraphic = new LinkedList();
            childrenGraphic.add(childLeft);
        } else {
            childrenGraphic.add(childLeft);
        }
        this.childLeft = childLeft;
    }

    public HuffmanNode getChildRight() {
        return childRight;
    }

    /**
     * Agrega nodo al hijo de la derecha.
     *
     * @param childRight
     */
    public void setChildRight(HuffmanNode childRight) {
        if (childrenGraphic == null) {
            childrenGraphic = new LinkedList();
            childrenGraphic.add(childRight);
        } else {
            childrenGraphic.add(childRight);
        }
        this.childRight = childRight;
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(binaryCode, 2);

    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getValue() {//comparable value
        return occurrenses;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean isTop() {
        return false;
    }

    @Override
    public LinkedList<GraphicableNode> getChildren() {
        return childrenGraphic;
    }

}
