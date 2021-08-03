package com.solvd.mylinkedlist.runner;

import com.solvd.mylinkedlist.linkedlist.LinkedList;
import org.apache.log4j.Logger;

public class Runner {
    private final static Logger LOGGER = Logger.getLogger(Runner.class);

    public final static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<String>();

        ll.add("");
        ll.addLast(null);
        ll.add("null");
        ll.add("abc");
        ll.add("def");
        ll.add("GHI");
        LOGGER.debug("List of " + ll.getLength() + " elements");
        LOGGER.debug(ll.toString());

        // Try to access invalid indexes
        try {
            ll.add("ZZZ", 99);
        } catch (IndexOutOfBoundsException e){
            LOGGER.error(e);
        }

        try {
            ll.add("000",-11);
        } catch (IndexOutOfBoundsException e){
            LOGGER.error(e);
        }

        ll.add("ABC",1);
        ll.addFirst("\u03B1");  // alpha
        LOGGER.debug(ll.toString());
        LOGGER.debug("Reversed list of " + ll.getLength() + " elements");
        LOGGER.debug(ll.toStringReverse());

        ll.setFirst("Zero");
        LOGGER.debug(ll.toString());

        ll.setLast(ll.getFirst());
        LOGGER.debug(ll.toString());

        LinkedList<Integer> listOfInts = new LinkedList<Integer>();
        listOfInts.add(222);
        listOfInts.add(45);
        listOfInts.addFirst(0);
        listOfInts.add(95,2);

        LOGGER.debug("List of " + listOfInts.getLength() + " elements");
        LOGGER.debug(listOfInts.toString());
        LOGGER.debug("Reversed list of " + listOfInts.getLength() + " elements");
        LOGGER.debug(listOfInts.toStringReverse());

        listOfInts.add(listOfInts.remove(2));
        LOGGER.debug(listOfInts.toString());

        listOfInts.remove(2);
        LOGGER.debug(listOfInts.toString());

        listOfInts.removeLast();
        LOGGER.debug(listOfInts.toString());

        listOfInts.removeFirst();
        LOGGER.debug(listOfInts.toString());

        listOfInts.removeLast();
        LOGGER.debug(listOfInts.toString());

        // Try to remove element from empty list
        try {
            listOfInts.removeLast();
        } catch (IndexOutOfBoundsException e){
            LOGGER.error(e);
        }

        try {
            listOfInts.removeFirst();
        } catch (IndexOutOfBoundsException e){
            LOGGER.error(e);
        }

        listOfInts.add(404);
        LOGGER.debug(listOfInts.toString());
    }

}
