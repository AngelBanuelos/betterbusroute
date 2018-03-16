/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.tree;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import masterdegree.ada.tree.HuffmanTree;
import static masterdegree.ada.tree.graphic.GraphicTreeBuilder.DEFAULTDIRECTORY;

/**
 * Esta clase hace uso del arbol de huffman para codificar un mensaje.
 *
 * @author Angel.Sahagun
 */
public class HuffmanCode implements Serializable {

    private HuffmanTree tree;
    private int asciiBits;
    private int huffmanBits;
    private String code;
    private BitSet codeBit;

    public int getAsciiBits() {
        return asciiBits;
    }

    public int getHuffmanBits() {
        return huffmanBits;
    }

    public HuffmanCode(HuffmanTree tree) {
        this.tree = tree;
    }

    /**
     *
     * @param saveTo Directorio donde se guardara el texto codificado
     * @param fileName Nombre deseado para el archivo.
     * @throws IOException
     */
    public void saveCodeToFile(String saveTo, String fileName) throws IOException {
        if (tree == null) { // Si no se ha creado el arbol 'No codifiques'
            throw new NullPointerException("The tree is null, create huffman tree");
        }
        if (saveTo.isEmpty()) {// En caso no haber indicado directorio a guardar pon el default. 
            saveTo = HuffmanConstants.SAVEDIRECTORY;
        }
        File dir = new File(HuffmanConstants.SAVEDIRECTORY);
        if (!dir.exists()) {//crea el directorio si no existe
            dir.mkdirs();
        }
        fileName = fileName.substring(0, (fileName.indexOf(".") == -1 ? fileName.length() : fileName.indexOf(".")));
        File file = new File(dir.getAbsolutePath() + File.separator + fileName + HuffmanConstants.HUFFMANCODEEXTENTION);
        if (!file.exists()) {// Genera el archivo para gurdar texto codificado.
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(code);// escribe el texto en el archivo nuevo.
        bw.close();
    }

    public String code(String message) throws Exception {
        if (tree == null) {// Si no hay arbol pide que sea creado.
            throw new Exception("Create the tree");
        }
        asciiBits = message.length() * 7; // Cuantos bits equivaleria en ascci?
        code = "";
        String temp;
        for (int i = 0; i < message.length(); i++) {
            if ((temp = tree.getHuffmanCodeTable().get(message.charAt(i) + "")) != null) {// busca la letra equivalente para generar codigo.
                code += temp;
            } else {// Si la letra no existe en el arbol indica cual fue.
                throw new RuntimeException("Dictionary not enough impossible to code. Missing " + message.charAt(i));
            }
        }
        huffmanBits = code.length();// cuantos bits equivale en codigo huffman.
        createBitCode();
        return code;
    }

    public void compressFile(String saveTo, String fileName) throws IOException {
        fileName = fileName + ".txt";
        ObjectOutputStream s;
        FileOutputStream object;
        try {
            File dir = new File(DEFAULTDIRECTORY);
            if (!dir.exists()) {
                dir.mkdir();
            }
            object = new FileOutputStream(dir.getAbsolutePath()
                    + File.separator + fileName);
            s = new ObjectOutputStream(object);
            s.writeObject(codeBit);
            s.close();
        } catch (IOException ioe) {
            System.err.println("Error, " + ioe);
        } catch (Exception e) {
            System.err.println("Error, Call to Developer  " + e);
        } finally {
        }
    }

    public ObjectOutputStream getStream(String saveTo, String fileName) throws IOException {
        fileName = fileName + ".txt";
        ObjectOutputStream s = null;
        FileOutputStream object;
        try {
            File dir = new File(DEFAULTDIRECTORY);
            if (!dir.exists()) {
                dir.mkdir();
            }
            object = new FileOutputStream(dir.getAbsolutePath()
                    + File.separator + fileName);
            s = new ObjectOutputStream(object);
            s.writeObject(codeBit);
            s.close();
        } catch (IOException ioe) {
            System.err.println("Error, " + ioe);
        } catch (Exception e) {
            System.err.println("Error, Call to Developer  " + e);
        } finally {
            return s;
        }
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(codeBit);
            oos.close();
            bais = new ByteArrayInputStream(baos.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(HuffmanCode.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(HuffmanCode.class.getName()).log(Level.SEVERE, null, ex);
            }
            return bais;
        }
    }

    public byte[] getByte() {
        return codeBit.toByteArray();
    }

    private void createBitCode() {
        codeBit = new BitSet(huffmanBits);
        for (int i = 0; i < code.length(); i++) {
            codeBit.set(i, (code.charAt(i) == '0' ? false : true));
        }
    }

}
