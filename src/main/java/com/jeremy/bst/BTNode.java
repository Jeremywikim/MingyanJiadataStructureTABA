package com.jeremy.bst;

/**
 * It is the container to hold the Employee information
*/
public class BTNode<T> { // class definition..

    //instances variables...we have an object of type T and then also we are referring to a left and a right node...
    protected T element;
    protected BTNode<T> left;
    protected BTNode<T> right;


    public BTNode(T element) {
        this.element = element;
    }

    // toString prints all the information inside an Employee
    @Override
    public String toString() {
        String leftVal = (left == null ? "NULL" : left.element.toString());
        String rightVal = (right == null ? "NULL" : right.element.toString());
        return "BTNode{" + "element=" + element + ", left=" + leftVal + ", right=" + rightVal + '}';
    }


}
