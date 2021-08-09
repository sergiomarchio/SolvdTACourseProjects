package com.solvd.carfactory.sax;

import com.solvd.carfactory.models.location.Country;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

public class CountrySAX extends DefaultHandler implements ISAXResult<Country> {
    private final static Logger LOGGER = Logger.getLogger(CountrySAX.class);

    private StringBuilder elementValue = new StringBuilder();
    private Stack<String> tags;
    private Country country;

    public Country getResult() {
        return country;
    }

    @Override
    public void startDocument() throws SAXException {
        LOGGER.debug("Country document started");

        tags = new Stack<>();
        country = new Country();
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
            throws SAXException {

        elementValue.setLength(0);

        tags.push(qName);

        switch (qName) {
            case "country":
                country.setId(Long.parseLong(attr.getValue("id")));
                break;
            default:
        }

        String id = attr.getValue("id");
        LOGGER.debug("<" + qName + (id != null ? " id = " + id : "") + ">");
    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        elementValue.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        LOGGER.debug(elementValue + "</" + qName + ">");

        tags.pop();

        if (tags.size() > 0) {

            switch (tags.peek()) {
                case "country":
                    switch (qName) {
                        case "name":
                            country.setName(elementValue.toString());
                            break;
                        default:
                    }
                    break;
                default:
            }
        }

        elementValue.setLength(0);
    }

    @Override
    public void endDocument() throws SAXException {
        LOGGER.debug("Document ended (Country)");
    }
}
