package domain.Tree;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class BTreeBSTAVLTest {
    private BTree bTree;
    private BST bst;
    private AVL avl;

    public BTreeBSTAVLTest() {
        this.bTree = new BTree();
        this.bst = new BST();
        this.avl = new AVL();

        //filling trees
        for (int i = 0; i < 20; i++) {
            int value = util.Utility.random(99);
            this.bTree.add(value);
            this.bst.add(value);
            this.avl.add(value);
        }
    }

    @Test
    void test() {

        try {
            System.out.println("NODES WITH CHILDREN\n");
            System.out.println("BTree: "+printNodesWithChildren(this.bTree));
            System.out.println("BST: "+printNodesWithChildren(this.bst));
            System.out.println("AVL: "+printNodesWithChildren(this.avl));

            System.out.println("\n\nNODES 1 CHILD\n");
            System.out.println("BTree: "+printNodes1Child(this.bTree));
            System.out.println("BST: "+printNodes1Child(this.bst));
            System.out.println("AVL: "+printNodes1Child(this.avl));

            System.out.println("\n\nNODES 2 CHILDREN\n");
            System.out.println("BTree: "+printNodes2Children(this.bTree));
            System.out.println("BST: "+printNodes2Children(this.bst));
            System.out.println("AVL: "+printNodes2Children(this.avl));

            System.out.println("\n\nLEAVES NODES\n");
            System.out.println("BTree: "+printLeaves(this.bTree));
            System.out.println("BST: "+printLeaves(this.bst));
            System.out.println("AVL: "+printLeaves(this.avl));

            System.out.println("\n\nGRAND FATHER\n");
            System.out.println("BTree: "+showPreOrder(this.bTree, this.bTree.getRoot(), "GrandFather"));
            System.out.println("BST: "+showPreOrder(this.bst, this.bst.getRoot(), "GrandFather"));
            System.out.println("AVL: "+showPreOrder(this.avl, this.avl.getRoot(), "GrandFather"));

            System.out.println("\n\nFATHER\n");
            System.out.println("BTree: "+showPreOrder(this.bTree, this.bTree.getRoot(), "Father"));
            System.out.println("BST: "+showPreOrder(this.bst, this.bst.getRoot(), "Father"));
            System.out.println("AVL: "+showPreOrder(this.avl, this.avl.getRoot(), "Father"));

            System.out.println("\n\nBROTHER\n");
            System.out.println("BTree: "+showPreOrder(this.bTree, this.bTree.getRoot(), "Brother"));
            System.out.println("BST: "+showPreOrder(this.bst, this.bst.getRoot(), "Brother"));
            System.out.println("AVL: "+showPreOrder(this.avl, this.avl.getRoot(), "Brother"));

            System.out.println("\n\nCOUSINS\n");
            System.out.println("BTree: "+showPreOrder(this.bTree, this.bTree.getRoot(), "Cousins"));
            System.out.println("BST: "+showPreOrder(this.bst, this.bst.getRoot(), "Cousins"));
            System.out.println("AVL: "+showPreOrder(this.avl, this.avl.getRoot(), "Cousins"));

            System.out.println("\n\nSUBTREE\n");
            System.out.println("BTree: "+showPreOrder(this.bTree, this.bTree.getRoot(), "SubTree"));
            System.out.println("BST: "+showPreOrder(this.bst, this.bst.getRoot(), "SubTree"));
            System.out.println("AVL: "+showPreOrder(this.avl, this.avl.getRoot(), "SubTree"));

            System.out.println("\n\nTOTAL LEAVES\n");
            System.out.println("BTree: "+totalLeaves(this.bTree));
            System.out.println("BST: "+totalLeaves(this.bst));
            System.out.println("AVL: "+totalLeaves(this.avl));


        } catch (TreeException e) {
            throw new RuntimeException(e);
        }


    }

    private String printNodesWithChildren(Tree tree) throws TreeException {
        return tree.printNodesWithChildren();
    }

    private String printNodes1Child(Tree tree) throws TreeException {
        return tree.printNodes1Child();
    }

    private String printNodes2Children(Tree tree) throws TreeException {
        return tree.printNodes2Children();
    }

    private String printLeaves(Tree tree) throws TreeException {
        return tree.printLeaves();
    }

    private String showPreOrder(Tree tree, BTreeNode node, String name) throws TreeException {
        String result="";
        if(node!=null){
            switch (name) {
                case "GrandFather":
                    result = "\ngrandpa of "+node.data+": "+tree.grandFather(node.data).toString();
                    break;
                case "Father":
                    result = "\nfather of "+node.data+": "+tree.father(node.data).toString();
                    break;
                case "Brother":
                    result = "\nBrother of "+node.data+": "+tree.brother(node.data).toString();
                    break;
                case "Cousins":
                    result = "\nCousins of "+node.data+": "+tree.cousins(node.data).toString();
                    break;
                case "SubTree":
                    result = "\nSubTree of "+node.data+": "+tree.subTree(node.data).toString();
            }
            result+=showPreOrder(tree, node.left, name);
            result+=showPreOrder(tree, node.right, name);
        }
        return result;
    }

    private int totalLeaves(Tree tree) throws TreeException {
        return tree.totalLeaves();
    }

}