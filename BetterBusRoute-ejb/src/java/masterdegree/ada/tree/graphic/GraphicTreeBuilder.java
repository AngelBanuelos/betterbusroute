/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.tree.graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import masterdegree.ada.tree.HuffmanNode;

/**
 * Clase constructora de arboles graficos, Clases deben implementar Graphicable
 * interfaces.
 *
 * @author Angel.Sahagun
 */
public class GraphicTreeBuilder extends JFrame implements ActionListener, TreeSelectionListener {

    private JTree tree;
    private String treeName;
    private String fileExtension;
    private DefaultMutableTreeNode root;
    public static final String DEFAULTDIRECTORY = "." + File.separator + "GraphicTree";

    public DefaultMutableTreeNode getRoot() {
        return root;
    }

    /**
     *
     * @param treeName Nombre del arbol.
     */
    public GraphicTreeBuilder(String treeName) {
        this(treeName, ".yeuuTree");
    }

    /**
     *
     * @param treeName Nombre del Arbol
     * @param fileExtension Extension a guardar o leer arboles.
     */
    public GraphicTreeBuilder(String treeName, String fileExtension) {
        this.treeName = treeName;
        this.fileExtension = fileExtension;
        init();
    }

    public GraphicTreeBuilder() {
        this("YeuuDefualtTree");
    }

    /**
     *
     * @param graphTree Graphicable arbol que implemente interface.
     */
    public void createTree(GraphicableTree graphTree) {
        root = new DefaultMutableTreeNode(graphTree.getNode());
        createNodes(root, graphTree.getNode());
        graphTree(root);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("expand")) {
            expandTree();
        } else if (command.equalsIgnoreCase("SAVETREE")) {
            root.getRoot();
            saveTreeIntoFile(this.treeName);
        }
    }

    /**
     * Metodo recursivo que genera los nodos de un arbol validos para que sea
     * graficado.
     *
     * @param root
     * @param graphNode
     */
    private void createNodes(DefaultMutableTreeNode root, GraphicableNode graphNode) {
        if (graphNode == null || root == null) {
            return;
        }
        DefaultMutableTreeNode nodes;
        while (graphNode.getChildren() != null && !graphNode.getChildren().isEmpty()) {
            GraphicableNode graphNodeCh = graphNode.getChildren().pop();
            nodes = new DefaultMutableTreeNode(graphNodeCh);
            root.add(nodes);
            if (!graphNode.isLeaf()) {
                createNodes(nodes, graphNodeCh);
            }
        }
    }

    private void init() {
        this.setTitle(treeName + fileExtension);
        this.setSize(754, 526);
        this.setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton expandButton = new JButton("Expand");
        expandButton.setActionCommand("EXPAND");
        expandButton.addActionListener(this);

        JButton saveButton = new JButton("Save Tree");
        saveButton.setActionCommand("SAVETREE");
        saveButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(0, 3));
        panel.add(expandButton);
        panel.add(saveButton);
        add(panel, BorderLayout.SOUTH);

    }

    private void expandTree() {
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null) {
            return;
        }

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            HuffmanNode huffmanNode = (HuffmanNode) nodeInfo;
            JOptionPane.showMessageDialog(this, huffmanNode.getBinaryCode());
        } else {

        }
    }

    /**
     * Metodo que guarda arboles Serializados.
     *
     * @param fileName Nombre del arbol a guardar.
     */
    public void saveTreeIntoFile(String fileName) {
        fileName = fileName + fileExtension;
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
            s.writeObject(root);
            s.close();
        } catch (IOException ioe) {
            System.err.println("Error, " + ioe);
        } catch (Exception e) {
            System.err.println("Error, Call to Developer  " + e);
        } finally {
        }

    }

    /**
     * Metodo que lee archivos serializados y los carga.
     *
     * @param fileName
     */
    public void readTreeFromFile(String fileName) {
        if (fileName == null) {
            return;
        }
        if (!fileName.endsWith(fileExtension)) {
            fileName += fileExtension;
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
            if (helper instanceof DefaultMutableTreeNode) {
                root = (DefaultMutableTreeNode) helper;
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
        if (root != null) {
            graphTree(root);
        }
    }

    /**
     * Metodo que crea la interface grafica.
     *
     * @param root
     */
    private void graphTree(DefaultMutableTreeNode root) {
        tree = new JTree(root);

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        JScrollPane treeView = new JScrollPane(tree);

        this.add(treeView);
    }

}
