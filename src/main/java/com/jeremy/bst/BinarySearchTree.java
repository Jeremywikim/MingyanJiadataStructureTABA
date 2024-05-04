package com.jeremy.bst;


public class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T> {

    // set root
    protected BTNode<T> root;

    /**
     * insert elem into the BST.
     * if there is no root, set root equal to BTNode<>(elem)
     * otherwise, perform insertNode(), it takes two parameters
     */
    public void insert(T elem) {

        if(isEmpty()){
            //update root node
            root = new BTNode<>(elem);

        } else {

            insertNode(elem,root);

        }

    }

    /**
     * When the BST is not empty, call this function.
     * determine place to add the new element by comparing to  current element
     * @param elem represents every element (like Employee)
     * @param current
     */
    private void insertNode(T elem, BTNode<T> current){
        //find a place to insert the element
        //compare the element with the root
        if(elem.compareTo(current.element)==-1){
            //add to the left
            if(current.left == null){
                current.left = new BTNode<>(elem);
            } else {
                //recurse down the sub-tree rooted at current.left
                insertNode(elem,current.left);
            }
            // similarly for the right
        } else {
            if(current.right == null){
                current.right = new BTNode<>(elem);
            } else {
                //recurse down the sub-tree rooted at current.left
                insertNode(elem,current.right);
            }
        }
    }

    /**
     * determine if the BST is empty
     * @return a boolean
     */
    public boolean isEmpty() {
        //empty tree
        return root == null;
    }

    /**
     * lowest values first
     * pass root as parameter
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * sort and print with recursion
     * @param current Node is parameter
     */
    private void inOrder(BTNode<T> current){
        //base case
        if(current == null){
            return;
        }
        inOrder(current.left);
        System.out.println(current);
        inOrder(current.right);
    }


    /**
     * @return the size of the BST
     */
    public int size() {
        return size(root);
    }

    /**
     * recursion to calculate the size of the BST
     * @param current
     * @return
     */
    private int size(BTNode<T> current){
        if(current == null){
            return 0;
        } else {
            return 1+size(current.left) + size(current.right); // recursion from the given Node
        }
    }

    /**
     * check from the root
     * pass root as parameter
     */
    public boolean contains(T element) {
        return contains(element, root);
    }

    /**
     *check element from current (Node)
     * @param element
     * @param current
     * @return
     */
    private boolean contains(T element, BTNode<T> current){
        String currentPos = (current == null ? "NULL" : current.element.toString());
        System.out.println("Searching for " + element + " currently at " + currentPos);
        if (current == null){
            return false;
        }

        if (element.compareTo(current.element) == 0){
            System.out.println("We found equality");
            return true;
        } else if (element.compareTo(current.element) <= -1){
            System.out.println("We are going to the left");
            return contains(element, current.left);
        } else {
            System.out.println("Going to the right");
            return contains(element, current.right);
        }
    }

    /**
     * pass root  as parameter to findNode
     * @param elem
     * @return
     */
    private BTNode<T> findNode(T elem){
        return findNode(elem, root);
    }




    /**
     * here we are looking for a Node in the Tree
     * this method returns a Object of type BTNode
     * @param element
     * @param current
     * @return
     */
    private BTNode<T> findNode(T element, BTNode<T> current){
        if (current == null){
            return null;
        }
        if (element.compareTo(current.element) == 0){
//            System.out.println("We found equality");
            return current;
        } else if (element.compareTo(current.element) <= -1){
//            System.out.println("We are going to the left");
            return findNode(element, current.left);
        } else {
            System.out.println("Going to the right");
            return findNode(element, current.right);
        }







        // if we get a match on the element we return the NTNode...
//        if (current.element.equals(elem)){
//            return current;
//        } else if (current.element.compareTo(elem) == -1){
//            return findNode(elem, current.right);
//        } else {
//            return findNode(elem, current.left);
//        }
    }





    /**
     * Remove the given element
     * @param element
     * @return
     */
    public boolean remove(T element) {
        // we need to locate the node which we must remove
        BTNode<T> toRemove = findNode(element);
        if (toRemove == null){
            System.out.println("not found");
            return false;
        }
        System.out.println("toRemove = " + toRemove);
        // we need to find its parent as well
        BTNode<T> parent = findParent(element);
        System.out.println("parent = " + parent);

        // to do the simplest case which is the removal of a leaf node
        if (toRemove.left == null && toRemove.right == null){
            // determine which child it is
            if (toRemove.element.compareTo(parent.element) == -1){
                parent.left = null;
            } else {
                parent.right = null;
            }
            return true;
        } else if (toRemove.left != null && toRemove.right == null){
            // in this case the node has a left child but no right child.
            if (toRemove.element.compareTo(parent.element) == -1){
                // the removed node is the left child
                parent.left = toRemove.left;
            } else {
                // it's the right child
                parent.right = toRemove.left;
            }
            return true;
        } else if (toRemove.left == null && toRemove.right != null){
            if (toRemove.element.compareTo(parent.element) == -1){
                parent.left = toRemove.right;
            } else {
                parent.right = toRemove.right;
            }
            return true;
        } else if (toRemove.left != null && toRemove.right != null){
            //this is the point at where we have objects to the left and to the right....

            // it has both a left and right child
            // find the max value in the left subtree rooted at the current node
            T minValue = findMin(toRemove.right);// we are finding the minimum Node in the right sub tree..
            // We know that when we are searching for the minimum Node we recursively search by passing in the
            // the left pointer starting at a particular Node.
            // The starting point is the point of the new subtree

            //find the Node
            BTNode<T> replacement = findNode(minValue);

            //find the parent
            BTNode<T> replacementParent = findParent(minValue);
            System.out.println("replacementParent = " + replacementParent);

            /*
                T maxValue = findMax(toRemove.left);
                System.out.println("maxValue = " + maxValue);
                BTNode<T> replacement = findNode(maxValue);
                BTNode<T> replacementParent = findParent(maxValue);
           */

            replacementParent.left = null;
            toRemove.element = replacement.element;
            return true;
        }
        System.out.println("parent = " + parent);
        return false;
    }

    /**
     * pass root as parameter
     * @param element
     * @return
     */
    private BTNode<T> findParent(T element) {
        return findParent(element, root);
    }

    /**
     * find the parent Node of a given element
     * @param element
     * @param current
     * @return
     */
    private BTNode<T> findParent(T element, BTNode<T> current) {
        // special case
        if (element.equals(root.element)){
            return null;
        }
        if (element.compareTo(current.element) == -1){
            if (current.left == null){
                // not in the tree
                return null;
            } else if (element.compareTo(current.left.element) == 0){
                return current;
            } else {
                return findParent(element, current.left);
            }
        } else {
            if (current.right == null){
                return null;
            } else if (element.compareTo(current.right.element) == 0) {
                return current;
            } else {
                return findParent(element, current.right);
            }
        }
    }

    public T findMax() {
        if(isEmpty()){
            throw new TreeEmptyException();
        } else{
            return findMax(root);
        }
    }

    /**
     * bigger elements are in right subtree
     * @param current
     * @return
     */

    private T findMax(BTNode<T> current){
        //base case
        if(current.right ==null){

            return current.element;

        } else {

            return findMax(current.right);

        }

    }

    /** Smaller elements are in left subtree
     * pass rrot as parameter
     * @return
     */
    public T findMin() {
        if(isEmpty()){

            throw new TreeEmptyException();

        } else{
            return findMin(root);

        }

    }

    /**
     * @param current
     * @return
     */
    private T findMin(BTNode<T> current){
        //base case
        if(current.left ==null){
            return current.element;
        } else {
            return findMin(current.left);
        }
    }



    /**
     * print all employee records in the BST based on their employee ID.
     */
    public void printEmployeeRecords() {
        printEmployeeRecords(root);
    }

    private void printEmployeeRecords(BTNode<T> current) {
        if (current != null) {
            printEmployeeRecords(current.left);
            System.out.println(current.element);
            printEmployeeRecords(current.right);
        }
    }

}
