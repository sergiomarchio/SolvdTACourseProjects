package com.solvd.mylinkedlist.linkedlist;


public class LinkedList<T> {
    private Node<T> firstNode = null;
    private Node<T> lastNode = null;
    private int length = 0;

    private Node<T> ithNode(int index){
        if (index < 0 || index >= length)
            throw new ArrayIndexOutOfBoundsException("Cannot access element with index " + index
                    + " in list with " + length + " elements!");

        Node<T> iNode = firstNode;

        // Starts from 1 because 0th node is already selected
        for (int i = 1; i <= index; i++) iNode = iNode.getNextNode();

        return iNode;
    }


    public void addFirst(T value){
        add(value, 0);
    }

    public void addLast(T value){
        add(value, length);
    }

    // By default adds new element at the end of the list
    public void add(T value){
        add(value, length);
    }

    // If index == length, item is added at the end of the list
    public void add(T value, int index){
        Node<T> node = new Node<T>(value);
        Node<T> newPrev = lastNode;

        if (index == length) lastNode = node;
        else {
            Node<T> newNext = ithNode(index);
            newPrev = newNext.getPrevNode();
            node.setNextNode(newNext);
            newNext.setPrevNode(node);
        }

        if (index == 0) firstNode = node;
        else {
            node.setPrevNode(newPrev);
            newPrev.setNextNode(node);
        }

        length++;
    }


    @Override
    public String toString(){
        return toString(false);
    }

    public String toStringReverse(){
        return toString(true);
    }

    public String toString(boolean reverse){
        String nodeString = "";
        Node<T> node = reverse ? lastNode : firstNode;

        while(node != null) {
            nodeString += String.valueOf(node.getValue());
            node = reverse ? node.getPrevNode() : node.getNextNode();
            if (node != null) nodeString += ", ";
        }

        return "[" + nodeString + "]";
    }


    public T removeFirst(){
        return remove(0);
    }

    public T removeLast(){
        return remove(length - 1);
    }

    // Returns the value of the element removed
    public T remove(int index){
        Node<T> node = ithNode(index);

        if (index == 0) firstNode = node.getNextNode();
        else node.getPrevNode().setNextNode(node.getNextNode());

        if (index == length - 1) lastNode = node.getPrevNode();
        else node.getNextNode().setPrevNode(node.getPrevNode());

        length--;

        return node.getValue();
    }


    public T getFirst(){
        return get(0);
    }

    public T getLast(){
        return get(length - 1);
    }

    public T get(int index){
        return ithNode(index).getValue();
    }


    public void setFirst(T value){
        set(value, 0);
    }

    public void setLast(T value){
        set(value, length - 1);
    }

    public void set(T value, int index){
        ithNode(index).setValue(value);
    }

    public int getLength() {
        return length;
    }
}
