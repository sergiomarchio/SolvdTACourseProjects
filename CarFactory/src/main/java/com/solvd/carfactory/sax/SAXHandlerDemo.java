package com.solvd.carfactory.sax;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandlerDemo extends DefaultHandler {
    private final static Logger LOGGER = Logger.getLogger(SAXHandlerDemo.class);

    private StringBuilder elementValue = new StringBuilder();

    @Override
    public void startDocument() throws SAXException {
        LOGGER.debug("Document started");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
            throws SAXException {

        elementValue.setLength(0);

        LOGGER.debug("Element started:\n"
                + "namespaceURI: " + namespaceURI + "\n"    // with spf.setNamespaceAware(true);
                + "localName: " + localName + "\n"
                + "qName: " + qName + "\n"
                + "attributes: " + attr);

    }

    @Override
    public void characters (char[] ch, int start, int length)
            throws SAXException{
        elementValue.append(ch, start, length);
        LOGGER.debug("Characters: " + elementValue.toString());
    }

    @Override
    public void endElement (String uri, String localName, String qName)
        throws SAXException{

        LOGGER.debug("Element ended:\n"
                + "uri: " + uri + "\n"
                + "localName: " + localName + "\n"
                + "qName: " + qName);


    }

    @Override
    public void endDocument() throws SAXException {
        LOGGER.debug("Document ended");
    }

}

