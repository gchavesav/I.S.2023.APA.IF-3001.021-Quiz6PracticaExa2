package domain;

public class BST implements Tree {
    private BTreeNode root;

    public BST() {
        this.root = null;
    }

    public BTreeNode getRoot() {
        return root;
    }

    @Override
    public int size() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return size(root);
    }

    private int size(BTreeNode node){
        if(node==null)
            return 0;
        else
            return 1+size(node.left)+size(node.right);
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public boolean contains(Object element) throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return binarySearch(root, element);
    }

    private boolean binarySearch(BTreeNode node, Object element){
        if(node==null)
            return false;
        else if(util.Utility.compare(node.data, element)==0)
            return true; //ya lo encontro
        else if(util.Utility.compare(element, node.data)< 0)
                return binarySearch(node.left, element);
        else return binarySearch(node.right, element);
    }

    @Override
    public void add(Object element) {
        root = add(root, element);
    }

    private BTreeNode add(BTreeNode node, Object element){
        if(node==null){ //el arbol esta vacio
            node = new BTreeNode(element);
        }else
            if(util.Utility.compare(element, node.data)< 0)
                node.left = add(node.left, element);
            else if(util.Utility.compare(element, node.data)> 0)//va como hijo der
                    node.right = add(node.right, element);
        return node;
    }

    @Override
    public void remove(Object element) throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        root = remove(root, element);
    }

    private BTreeNode remove(BTreeNode node, Object element){
        if(node!=null){
            if(util.Utility.compare(element, node.data)< 0)
                node.left = remove(node.left, element);
            else if(util.Utility.compare(element, node.data)> 0)
                node.right = remove(node.right, element);
            else if(util.Utility.compare(node.data, element)==0){ //ya encontramos el elemento a eliminar
                //Caso 1. Es un nodo sin hijos. Es una hoja
                if(node.left==null && node.right==null)
                    return null;
                //Caso 2. El nodo solo tiene un hijo
                else if(node.left!=null && node.right==null)
                    return node.left; //retorna el subarbol izq y sustituye el nodo actual
                else if(node.left==null && node.right!=null)
                    return node.right; //retorna el subarbol derecho y sustituye el nodo actual
                //Caso 3. El nodo tiene 2 hijos
                else if(node.left!=null && node.right!=null){
                    Object value = min(node.right);
                    node.data = value;
                    node.right = remove(node.right, value);
                }
            }
        }
        return node; //retorna el nodo modificado
    }

    @Override
    public int height(Object element) throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return height(root, element, 0);
    }

    private int height(BTreeNode node, Object element, int counter){
        if(node==null)
            return -1;
        else if(util.Utility.compare(node.data, element)==0)
            return counter;
        else //en este caso debe buscar por la izq y por la der
        if(util.Utility.compare(element, node.data)< 0)
            return height(node.left, element, ++counter);
        else return height(node.right, element, ++counter);
            //return Math.max(height(node.left, element, ++counter), height(node.right, element, counter));
    }

    @Override
    public int height() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return height(root)-1; //pq no cuente el nivel de la raiz
    }

    private int height(BTreeNode node){
        if(node==null)
            return 0;
        else
            return 1+Math.max(height(node.left), height(node.right));
    }

    @Override
    public Object min() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Search Tree is empty");
        return min(root);
    }

    private Object min(BTreeNode node){
        if(node.left!=null)
            return min(node.left);
        return node.data;
    }

    @Override
    public Object max() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Search Tree is empty");
        return max(root);
    }

    private Object max(BTreeNode node){
        if(node.right!=null)
            return max(node.right);
        return node.data;
    }

    @Override
    public String preOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Search Tree is empty");
        return "PreOrder Transversal Tour: "+preOrder(root)+"\n";
    }

    //metodo interno
    //preOrder: node-left-right
    private String preOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result=node.data+", ";
            result+=preOrder(node.left);
            result+=preOrder(node.right);
        }
        return result;
    }


    @Override
    public String InOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Search Tree is empty");
        return "InOrder Transversal Tour: "+inOrder(root)+"\n";
    }

    //metodo interno
    //preOrder: left-node-right
    private String inOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result=inOrder(node.left);
            result+=node.data+", ";
            result+=inOrder(node.right);
        }
        return result;
    }

    @Override
    public String postOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Search Tree is empty");
        return "PostOrder Transversal Tour: "+postOrder(root)+"\n";
    }

    //metodo interno
    //preOrder: left-right-node
    private String postOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result=postOrder(node.left);
            result+=postOrder(node.right);
            result+=node.data+", ";
        }
        return result;
    }

    @Override
    public String toString() {
        if(isEmpty()) return "Binary Search tree is empty";
        String result = "Binary Search Tree Tour...\n";
        result+="PreOrder: "+preOrder(root)+"\n";
        result+="InOrder: "+inOrder(root)+"\n";
        result+="PostOrder: "+postOrder(root)+"\n";
        return result;
    }

    public void modify(Object a, Object b) throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Search Tree is empty");
        else if (!contains(a))
            throw new TreeException("The object a isn't in the Binary Search Tree");
        remove(a);
        add(b);
    }

    //Josue Barbosa
    public boolean isBalanced() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("AVL Binary Search Tree is empty");
        }

        return isBalanced(root);
    }

    private boolean isBalanced(BTreeNode node) {
        if (node == null) {
            return true;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.left) && isBalanced(node.right)) {
            return true;
        }

        return false;
    }

    //Diego Paesani
    public String printElementHeights() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return "Height of each element:\n"+printElementHeights(root, 0);
    }

    private String printElementHeights(BTreeNode node, int height) {
        String result="";
        if (node != null) {
            return "Element: " + node.data + ", Height: " + height+"\n"
                    +printElementHeights(node.left, height + 1)
                    +printElementHeights(node.right, height + 1);
        }
        return result;
    }
    public void preOrderPrintElementHeights(BTreeNode node, int height) {
        if (node != null) {
            System.out.println("Element: " + node.data + ", Height: " + height);
            preOrderPrintElementHeights(node.left, height + 1);
            preOrderPrintElementHeights(node.right, height + 1);
        }
    }


}
