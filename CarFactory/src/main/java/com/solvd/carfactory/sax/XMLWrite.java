package com.solvd.carfactory.sax;

import org.apache.log4j.Logger;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class XMLWrite {
    private final static Logger LOGGER = Logger.getLogger(XMLWrite.class);

    private Set<Class<?>> classes;

    private int indentCount = 0;
    private int indentAmount = 4;

    private Object getFieldValue(Object object, Method m) {
        try {
            return m.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Error getting value from " + m.getName()
                    + " in " + object.getClass().getSimpleName() + "\n" + e);
        }
        return null;
    }

    private void writeFieldValues(XMLStreamWriter xsw, Object object, Method m) {
        String elementName = StringUtils.camelToSnake(m.getName().substring(3));
        Object fieldValue = getFieldValue(object, m);

        try {
            xsw.writeCharacters("\n" + " ".repeat(indentCount));
            xsw.writeStartElement(elementName);

            if (classes.contains(m.getReturnType())) {
                indentCount += indentAmount;
                writeObject(xsw, fieldValue);
                indentCount -= indentAmount;
                xsw.writeCharacters("\n" + " ".repeat(indentCount));
            } else {
                xsw.writeCharacters(String.valueOf(fieldValue));
            }
            xsw.writeEndElement();

        } catch (XMLStreamException e) {
            LOGGER.error("Error writing to xml file: " + e);
        }
    }

    private void writeAttributes(XMLStreamWriter xsw, Object object, Method m) {
        String elementName = StringUtils.camelToSnake(m.getName().substring(3));
        Object fieldValue = getFieldValue(object, m);

        try {
            xsw.writeAttribute(elementName, String.valueOf(fieldValue));
        } catch (XMLStreamException e) {
            LOGGER.error("Error writing to xml file: " + e);
        }
    }

    private void writeObject(XMLStreamWriter xsw, Object object) {
        List<Method> methods = Arrays.asList(object.getClass().getMethods());
        List<String> excludeMethods = Arrays.asList("getClass", "getId");

        // first looks for id method to set attribute just after StartElement
        methods.stream()
                .filter(m -> m.getName().equals("getId"))
                .forEach(m -> writeAttributes(xsw, object, m));

        methods.stream()
                .filter(m -> m.getName().startsWith("get") && !excludeMethods.contains(m.getName()))
                .forEach(m -> writeFieldValues(xsw, object, m));
    }

    public void xmlWrite(String fileName, Object object) {
        classes = ClassSet.getClasses();

        XMLOutputFactory xof = XMLOutputFactory.newFactory();
        XMLStreamWriter xsw = null;
        try {
            xsw = xof.createXMLStreamWriter(new FileWriter(fileName));

            xsw.writeStartDocument();
            xsw.writeCharacters("\n");
            xsw.writeStartElement(StringUtils.camelToSnake(object.getClass().getSimpleName()));
            indentCount += indentAmount;

            writeObject(xsw, object);

            xsw.writeCharacters("\n");
            xsw.writeEndElement();
            xsw.writeEndDocument();

        } catch (XMLStreamException | IOException e) {
            LOGGER.error("Error creating XML Stream Writer for " + fileName + "\n" + e);
        } finally {
            if (xsw != null) {
                try {
                    xsw.close();
                } catch (XMLStreamException e) {
                    LOGGER.error(e);
                }
            }
        }

    }


    public int getIndentAmount() {
        return indentAmount;
    }
    public void setIndentAmount(int indentAmount) {
        this.indentAmount = indentAmount;
    }
}
