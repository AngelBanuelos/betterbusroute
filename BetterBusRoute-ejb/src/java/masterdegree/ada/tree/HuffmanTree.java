/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.tree;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import masterdegree.ada.search.Searching;
import masterdegree.ada.tree.graphic.GraphicableNode;
import masterdegree.ada.tree.graphic.GraphicableTree;

/**
 * Arbol de Huffman recibe una cadena de texto o una cadena con sus valores de
 * frecuencia y crea el arbol de huffman
 *
 * @author Angel.Sahagun
 */
public class HuffmanTree implements GraphicableTree, Serializable {

    private HuffmanNode root = null;
    private String alphabetToCode;
    private HashMap<String, String> huffmanCodeTable;
    private HashMap<String, BitSet> huffmanCodeTableBits = new HashMap();
    private HashMap<BitSet, String> huffmanDecodeTableBits = new HashMap();

    public HashMap<String, BitSet> getHuffmanCodeTableBits() {
        return huffmanCodeTableBits;
    }

    public String getAlphabetToCode() {
        return alphabetToCode;
    }

    public HuffmanTree() {
    }

    public HashMap<String, String> getHuffmanCodeTable() {
        return huffmanCodeTable;
    }

    /**
     *
     * @param alphabetToCode Alfabeto para la creacion del arbol.
     */
    public HuffmanTree(String alphabetToCode) {
        this.alphabetToCode = alphabetToCode;
        createTree(alphabetToCode);
    }

    /**
     *
     * @param alphabetToCode Alfabeto para la creacion del arbol
     * @param occurenses Frecuencia con la que se repiten letras del alfabeto.
     */
    public HuffmanTree(String alphabetToCode, int[] occurenses) {
        this.alphabetToCode = alphabetToCode;
        createTree(alphabetToCode, occurenses);
    }

    private void createTree(String alphabetToCode, int[] occurenses) {
        char[] array = alphabetToCode.toCharArray();
        createTreeStack(array, occurenses);
    }

    /**
     * Este metodo convierte el alfabeto String en un arreglo de caracteres. A
     * su vez asigna frecuencias de mayor a menor.
     *
     * @param alphabetToCode Alfabeto para la creacion del arbol
     */
    private void createTree(String alphabetToCode) {
        char[] array = alphabetToCode.toCharArray();
        int[] occurrenses = new int[array.length];
        int j = 0;
        for (int i = array.length; i > 0; i--) {
            occurrenses[j] = i;
            j++;
        }
        createTreeStack(array, occurrenses);
    }

    @Override
    public String toString() {
        return alphabetToCode;
    }

    /**
     * Metodo recursivo que imprime en cosola el arbol de huffman.
     *
     * @param root
     * @param treeToString
     * @return
     */
    public String printHuffmanTree(HuffmanNode root, String treeToString) {
        if (root == null) {
            return treeToString;
        }
        treeToString += root.getWord() + (root.getBinaryCode() != null ? " \t bit: " + root.getBinaryCode() : "") + "\n";
        treeToString = printHuffmanTree(root.getChildLeft(), treeToString + " ");
        return treeToString = printHuffmanTree(root.getChildRight(), treeToString + " ");
    }

    /**
     * Metodo recursivo que imprime en consola el codigo de huffman the un arbol
     * dado.
     *
     * @param root
     * @param treeToString
     * @return
     */
    private String printHuffmanCode(HuffmanNode root, String treeToString) {
        if (root == null) {
            return treeToString;
        }
        if (root.isLeaf()) {
            treeToString += root.getWord() + (root.getBinaryCode() != null ? "\t" + root.getBinaryCode() : "") + " hash: " + root.hashCode() + "\n";
        }
        treeToString = printHuffmanCode(root.getChildLeft(), treeToString);
        return treeToString = printHuffmanCode(root.getChildRight(), treeToString);
    }

    /**
     * Metodo que llama a metodo recursivo para la impresion del codigo de
     * huffman.
     */
    public void printHuffmanCode() {
        System.out.println("HuffmanCode for [" + alphabetToCode + "]");
        System.out.println("letter\tbinaryCode");
        System.out.println(printHuffmanCode(root, ""));
    }

    /**
     * Metodo que genera el numero de bits para cada caracter.
     *
     * @param root
     * @param bit
     */
    private void generateBinaryCode(HuffmanNode root, String bit) {
        if (root == null) {
            return;
        }
        bit += root.getBit();
        if (root.isLeaf()) {
            root.setBit(Short.parseShort(bit, 2));
            root.setBinaryCode(bit);
            huffmanCodeTable.put(root.getWord(), root.getBinaryCode());
            setHuffmanBitSet(root.getWord(), root.getBinaryCode());
        }
        generateBinaryCode(root.getChildLeft(), bit);
        generateBinaryCode(root.getChildRight(), bit);
    }

    /**
     * Funciona parcialmente. No Usar.
     *
     * @deprecated
     * @param alphabetToCode
     */
    private void createTreeStackOld(char[] alphabetToCode) {
        LinkedList<HuffmanNode> list = new LinkedList<>();
        for (int i = alphabetToCode.length - 1; i >= 0; i = i - 2) {
            HuffmanNode chR = new HuffmanNode();
            chR.setLetter(alphabetToCode[i]);
            list.add(chR);
            chR.setWord("" + alphabetToCode[i]);
            HuffmanNode chL = new HuffmanNode();
            if (i > 0) {
                chL.setLetter(alphabetToCode[i - 1]);
                chL.setWord("" + alphabetToCode[i - 1]);
                list.add(chL);
            }
        }
        int i = 0, swap = list.size() / 2;
        boolean canI = !(swap % 2 == 0);
        while (!list.isEmpty() & list.size() > 1) {
            HuffmanNode parent = new HuffmanNode();
            if (i == swap && swap > 3) {
                if (swap % 2 != 0 && canI) {
                    HuffmanNode right = list.pop();
                    HuffmanNode left = list.pollLast();
                    right.setBit((short) 0b1);
                    left.setBit((short) 0b0);
                    parent.setChildLeft(left);
                    parent.setChildRight(right);
                    parent.setWord((right == null ? "" : right.getWord()) + left.getWord());
                }
                i = 0;
                swap = list.size() / 2;
                canI = !(swap % 2 == 0);
            }
            if (parent.getChildLeft() == null) {
                HuffmanNode right = list.pop();
                HuffmanNode left = list.pop();
                right.setBit((short) 0b1);
                left.setBit((short) 0b0);
                parent.setChildLeft(left);
                parent.setChildRight(right);
                parent.setWord(left.getWord() + (right == null ? "" : right.getWord()));
            }
            if (swap == 3 && i == 3) {
                list.addFirst(parent);
            } else {
                list.add(parent);
            }
            i++;
        }
        root = list.pop();
        createHuffmanCode();
    }

    /**
     * Metodo que genera el codigo de Huffman.
     */
    private void createHuffmanCode() {
        huffmanCodeTable = new HashMap<>();
        generateBinaryCode(root, "");
    }

    /**
     * Metodo que usa la busqueda por interpolacion para encontrar el indice
     * donde sera colocado el nuevo padre (suma de las frecuencias)
     *
     * @param parent Nodo a buscar la posicion
     * @param list Lista donde buscar que debe estar ordenada.
     * @return
     */
    private int getIndex(HuffmanNode parent, LinkedList<HuffmanNode> list) {
        if (list.isEmpty()) { // Si la lista vacia regresa el indice 0 que seria para el caso padre.
            return 0;
        }
        if (parent.getOccurrenses() > list.getLast().getOccurrenses()) {// Si el numero de frecuencias del ultimo nodo es menor alq ue se busca, regresa la longitud de la lista
            return list.size();
        }
        Searching[] searching = new Searching[list.size()];
        list.toArray(searching);
        return insertionIndexOf(searching, 0, searching.length / 2, searching.length, parent.getValue());
//        for (int i = 0; i < list.size(); i++) {
//            if (i == 0 && parent.getOccurrenses() == list.get(i).getOccurrenses()) {
//                return i + 1;
//            } else if (parent.getOccurrenses() <= list.get(i).getOccurrenses()) {
//                return i;
//            }
//        }
//        return -1;
    }

    private int insertionIndexOf(Searching[] array, int start, int half, int end, int value) {
        if (start == half) {
            return half + 1;
        }
        if (value > array[end - 1].getValue()) {
            return end;
        }
        if (value < array[start].getValue()) {
            return 0;
        }
        if (array[half].getValue() == value) {
            return half;
        }
        if (value < array[half].getValue()) {
            end = half;
            half /= 2;
            return insertionIndexOf(array, start, half, end, value);
        } else {
            start = half;
            half += (end - start) / 2;
            return insertionIndexOf(array, start, half, end, value);
        }
    }

    /**
     * Metodo que crea el arbol de Huffman, haciendo uso de una lista, en la
     * lista se van colocando los nodos de huffman de manera ordenada, Se van
     * tomando nodos de dos en dos, creando el arbol binario, a cada nodo se le
     * van agregando sus propidedes existentes.
     *
     * @param alphabetToCode
     * @param occurenses
     */
    private void createTreeStack(char[] alphabetToCode, int[] occurenses) {
        LinkedList<HuffmanNode> list = new LinkedList<>(); // Lista contenedora de Nodos por validar.
        int index = 0;
        for (int i = alphabetToCode.length - 1; i >= 0; i = i - 2) {// for que toma de izquierda a derecha los nodos y los agrega a la lista de manera ordenada inversa.
            HuffmanNode chR = new HuffmanNode();
            chR.setLetter(alphabetToCode[i]);// nodo Hoja, agregar caracter.
            chR.setOccurrenses(occurenses[i]);//Frecuencia del caracter.
            chR.setIndex(index++);// se le agrega index para la busqueda binaria por interpolacion
            list.add(chR);
            chR.setWord("" + alphabetToCode[i]);
            HuffmanNode chL = new HuffmanNode();
            if (i > 0) {
                chL.setLetter(alphabetToCode[i - 1]);
                chL.setWord("" + alphabetToCode[i - 1]);
                chL.setOccurrenses(occurenses[i - 1]);
                chR.setIndex(index++);
                list.add(chL);
            }
        }
        while (!list.isEmpty() & list.size() > 1) {//miestras existan mas de 1 elementos en la lista continua creando padres.
            HuffmanNode parent = new HuffmanNode(); //padre contenedor dl nuevo hijo
            HuffmanNode left = list.pop();// Usando FIFO toma el primer elemento de la lista
            HuffmanNode right = list.pop();// Toma el Segundo elemento de la lista
            right.setBit((short) 0b1);//Nodos derechos agregales 1 bit
            left.setBit((short) 0b0);// Nodos izquiedos agregales 0 bit.
            parent.setChildLeft(left);
            parent.setChildRight(right);
            parent.setWord(left.getWord() + (right == null ? "" : right.getWord()));// crea el padre y concatena las palabras de nodo izq y nodo derc
            parent.setOccurrenses(left.getOccurrenses() + right.getOccurrenses());
            int indexL = getIndex(parent, list);//inserta el elemento padre en su posicion corespondiente para continuar con la lista ordenada
            if (indexL != -1) {
                list.add(indexL, parent);// agrega el nodo en la posicion adecuada.
            } else {
                list.add(parent);
            }
        }
        root = list.pop();// el Ultimo nodo de la lista ya contine todos los nodos. Por lo tanto es el nodo TOP
        createHuffmanCode();// Crear el codigo de huffman
    }

    /**
     * Metodo recursivo para busqueda de nodos hoja con codigo determidado.
     *
     * @param code
     * @return
     */
    public HuffmanNode search(String code) {
        return search(root, code, 0);
    }

    /**
     * Metodo recursivo para busqueda de nodos hoja con codigo determidado.
     * busca en los nodos de la izquierda si el valor es 0 y busca por la
     * derecha si el valor es 1. Tomando de bit por bit hasta llegar a un nodo
     * hoja.
     *
     * @param root // nodo padre a buscar.
     * @param code // codigo a buscar
     * @param index Contador para siguiente elemento.
     * @return
     */
    private HuffmanNode search(HuffmanNode root, String code, int index) {
        if (root == null || root.isLeaf()) {
            return root;
        }
        if (index > code.length() - 1) {
            return null; //don't found it 
        }
        if (code.charAt(index) == '0') {
            return search(root.getChildLeft(), code, index + 1);
        } else {
            return search(root.getChildRight(), code, index + 1);
        }
    }

    /**
     * Regresa el nodo TOP del arbol
     *
     * @return
     */
    @Override
    public GraphicableNode getNode() {
        return root;
    }

    /**
     * Para la lectura de archivos Serializados.
     *
     * @param huffmanNode
     */
    public void setRoot(HuffmanNode huffmanNode) {
        this.root = huffmanNode;
        huffmanCodeTable = new HashMap();
        huffmanCodeTable(root);
    }

    /**
     * Metodo recursivo que genera el codigo de huffman y lo guarda en una tabla
     * Hash. para su rapida codificacion.
     *
     * @param root
     */
    private void huffmanCodeTable(HuffmanNode root) {
        if (root == null) {
            return;
        }
        if (root.isLeaf()) {
            huffmanCodeTable.put(root.getWord(), root.getBinaryCode());
        }
        huffmanCodeTable(root.getChildLeft());
        huffmanCodeTable(root.getChildRight());
    }

    private void setHuffmanBitSet(String word, String binaryCode) {
        BitSet bitSet = new BitSet(binaryCode.length());
        for (int i = 0; i < binaryCode.length(); i++) {
            bitSet.set(i, (binaryCode.charAt(i) == '0' ? false : true));

        }
        huffmanCodeTableBits.put(word, bitSet);
        huffmanDecodeTableBits.put(bitSet, word);
    }

    public HashMap<BitSet, String> getHuffmanDecodeTableBits() {
        return huffmanDecodeTableBits;
    }

}
