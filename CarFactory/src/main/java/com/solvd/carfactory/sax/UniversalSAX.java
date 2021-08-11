package com.solvd.carfactory.sax;

import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.models.location.Country;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class UniversalSAX <T> extends DefaultHandler {
    private final static Logger LOGGER = Logger.getLogger(UniversalSAX.class);

    private StringBuilder elementValue = new StringBuilder();
    private Map<String, Class<?>> classes = Map.of("city", City.class,
                                                             "country", Country.class);
    private Stack<Map.Entry<String, Object>> objects;
    private T returnObject;

    public T getResult() {
        return returnObject;
    }

    @Override
    public void startDocument() throws SAXException {
        LOGGER.debug("Document started");
        classes = ClassSet.getClassTags();
        objects = new Stack<>();
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
            throws SAXException {

        elementValue.setLength(0);
        String debugLine = "<" + qName;

        // If this element is a complex element, creates object and stores it in the object stack
        if(classes.containsKey(qName)){
            Object object;
            String id = attr.getValue("id");

            try {
                object = classes.get(qName).getDeclaredConstructor().newInstance();
                ObjectCreator.populateObject(object, "id", id);
                objects.push(new AbstractMap.SimpleEntry<>(qName, object));
            } catch (InstantiationException | IllegalAccessException
                    | InvocationTargetException | NoSuchMethodException e) {
                LOGGER.error("Error creating object of " + qName);
            }

            debugLine += " id = " + id;
        }
        LOGGER.debug(debugLine + ">");
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        String stringValue = elementValue.toString();

        LOGGER.debug(stringValue.replace("\n", "") + "</" + qName + ">");

        Object fieldValue;
        // If the ending element is complex, the value to populate is the object
        if (objects.peek().getKey().equals(qName)) {
            fieldValue = objects.pop().getValue();
        } else {
            fieldValue = stringValue;
        }

        // If there is a parent object, populate field, else this is the full object to return
        if (objects.size() > 0) {
            ObjectCreator.populateObject(objects.peek().getValue(), qName, fieldValue);
        } else {
            returnObject = (T) fieldValue;
        }

        elementValue.setLength(0);
    }

    @Override
    public void endDocument() throws SAXException {
        LOGGER.debug("Document ended");
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        elementValue.append(ch, start, length);
    }
}
