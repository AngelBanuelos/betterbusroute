/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;
import masterdegree.ada.tree.HuffmanNode;
import masterdegree.ada.tree.HuffmanTree;

/**
 * Clase que hace uso del Codigo de Huffman para decodificar un mensaje.
 *
 * @author Angel.Sahagun
 */
public class HuffmanDecode {

    private HuffmanTree tree;
    private String decode;
    private BitSet decodeBit;

    public HuffmanDecode(HuffmanTree tree) {
        this.tree = tree;
    }

    /**
     *
     * @param message Mensaje a decodificar.
     * @return Mnsaje decodificado.
     */
    public String decode(String message) {
        if (tree == null) { // Crea primero el arbol si no existe.
            throw new NullPointerException("Create the tree");
        }
        decode = "";
        int i = 0;
        do {
            int j = i + 1;
            String binaryCode = message.substring(i, j);//ve tomando de caracter en caracter.
            HuffmanNode decodeB = null;
            while ((decodeB = tree.search(binaryCode)) == null && !message.isEmpty()) {// busca de el arbol la letra correspondiente.
                binaryCode = message.substring(i, ++j);// si no existe toma un caracter mas.
            }
            message = message.substring(j, message.length());// Elimina los bits consumidos.
            decode += decodeB;
        } while (message.length() > 0);

        return decode;
    }

    public void saveDecodeToFile(String fileName) throws IOException {
        if (tree == null) {
            throw new NullPointerException("The tree is null, create huffman tree");
        }
        File dir = new File(HuffmanConstants.SAVEDIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        fileName = fileName.substring(0, (fileName.indexOf(".") == -1 ? fileName.length() : fileName.indexOf(".")));
        String saveTo = HuffmanConstants.SAVEDIRECTORY + File.separator + fileName + "_decode";
        File file = new File(saveTo + ".txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(decode);
        bw.close();
    }

    /**
     *
     * @param message Mensaje a decodificar.
     * @return Mnsaje decodificado.
     */
    public String decode(BitSet message) {
        if (tree == null) { // Crea primero el arbol si no existe.
            throw new NullPointerException("Create the tree");
        }
        decode = "";
        int i = 0;
        do {
            int j = i + 1;
            BitSet binaryCode = message.get(i, j);//ve tomando de caracter en caracter.
            String binaryString = (binaryCode.get(i) ? "1" : "0");
            HuffmanNode decodeB = null;
            while ((decodeB = tree.search(binaryString)) == null && !message.isEmpty()) {// busca de el arbol la letra correspondiente.
                binaryString = "";
                binaryCode = message.get(i, ++j);// si no existe toma un caracter mas.
                int k = i;
                while (k < j) {
                    binaryString += (binaryCode.get(k) ? "1" : "0");
                    k++;
                }
            }
            if (j > message.length()) {
                j = message.length();
            }
            message = message.get(j, message.length());// Elimina los bits consumidos.
            decode += decodeB;
        } while (message.length() > 0);

        return decode;
    }

    public BitSet bytesToBitSet(byte[] bytes) {
        BitSet bits = BitSet.valueOf(bytes);
        return bits;
    }

}
