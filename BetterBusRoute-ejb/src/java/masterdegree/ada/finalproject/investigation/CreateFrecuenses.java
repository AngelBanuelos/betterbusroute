/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.finalproject.investigation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.BitSet;
import masterdegree.ada.sort.InsertionComparation;
import masterdegree.ada.sort.Mergesort;
import masterdegree.ada.sort.Sortable;
import masterdegree.ada.tree.HuffmanTree;
import static masterdegree.ada.tree.graphic.GraphicTreeBuilder.DEFAULTDIRECTORY;
import masterdegree.ada.tree.HuffmanCode;
import masterdegree.ada.tree.HuffmanDecode;

/**
 *
 * @author Angel.Sahagun
 */
public class CreateFrecuenses {

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

    public static void main(String[] args) throws Exception {

        System.out.println("");
        String text = readRoutes("D:\\EJB\\Buses\\NewBulk.txt");
        String textToCode = text;
        ArrayList<String> chrs = new ArrayList<>();
        ArrayList<Integer> occurs = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char chr = text.charAt(0);
            int previous = text.length();
            if (chr == '\n') {
                text = text.replaceAll("" + chr, "");
                chr = '\n';
            } else if (chr == '.' || chr == '|') {
                text = text.replaceAll("\\" + chr, "");
            } else {
                text = text.replaceAll("" + chr, "");
            }
            int actual = text.length();
            int ocurrences = previous - actual;
            chrs.add("" + chr);
            occurs.add(ocurrences);
        }
        AlphabetToCode[] alphabet = new AlphabetToCode[occurs.size()];
        for (int i = 0; i < occurs.size(); i++) {
            alphabet[i] = new AlphabetToCode(chrs.get(i), occurs.get(i));
        }
        Mergesort sort = new Mergesort();
        sort.sort(alphabet);
        String alphabetToCode = "";
        String occurrenses = "{";

        int[] occurrences = new int[alphabet.length];
        for (int i = alphabet.length - 1, j = 0; i >= 0; i--) {
            alphabetToCode += alphabet[i].alphabet;
            occurrences[j] = alphabet[i].occurrences;
            occurrenses += alphabet[i].occurrences + ",";
            System.out.println(alphabet[i].alphabet + "=" + alphabet[i].occurrences);
            j++;
        }
        
        System.out.println(alphabetToCode);
        System.out.println(occurrenses);
        
        HuffmanTree tree = new HuffmanTree(alphabetToCode, occurrences);
        tree.printHuffmanCode();
        HuffmanCode code = new HuffmanCode(tree);

//        String[] routes = textToCode.split("\n");
//        for (int i = 0; i < routes.length; i++) {
//            code.code(routes[i]);
//            code.compressFile("", i + "");
//        }
//        BitSet bitSet = readTreeFromFile("0.txt");
//        HuffmanDecode decode = new HuffmanDecode(tree);
//        System.out.println(decode.decode(bitSet));
    }

    /**
     * Metodo que lee archivos serializados y los carga.
     *
     * @param fileName
     */
    public static BitSet readTreeFromFile(String fileName) {
        BitSet bitSet = null;
        if (fileName == null) {
            return bitSet;
        }
        Object helper = null;
        try {
            File dir = new File(DEFAULTDIRECTORY);
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileInputStream object = new FileInputStream(dir.getAbsolutePath()
                    + File.separator + fileName);
            ObjectInputStream s = new ObjectInputStream(object);
            helper = s.readObject();
            if (helper instanceof BitSet) {
                bitSet = (BitSet) helper;
            } else {
                System.err.println("Error, The Object in file " + fileName
                        + " is not a instance of DefaultMutableTreeNode class");
            }
            s.close();
        } catch (IOException ioe) {
            System.err.println("Error, " + ioe);
        } catch (Exception e) {
            System.err.println("Error, " + e);
        }
        return bitSet;
    }

    static class AlphabetToCode implements InsertionComparation, Sortable {

        String alphabet;
        int occurrences;

        public AlphabetToCode(String alphabet, int occurrences) {
            this.alphabet = alphabet;
            this.occurrences = occurrences;
        }

        @Override
        public int getIndex() {
            return occurrences;
        }

        @Override
        public void setSelected(boolean selected) {

        }

        @Override
        public void setIndex(int index) {
        }

        @Override
        public int getValue() {
            return occurrences;
        }

    }

}
