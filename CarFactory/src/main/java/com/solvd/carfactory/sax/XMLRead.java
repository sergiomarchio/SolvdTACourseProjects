package com.solvd.carfactory.sax;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XMLRead {
    private final static Logger LOGGER = Logger.getLogger(XMLRead.class);

    public static <T> T xmlRead(String xmlFile, UniversalSAX<T> classSax){
        SAXParserFactory spf = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            xmlReader.setContentHandler(classSax);
            xmlReader.setErrorHandler(new SAXErrorHandler());
            xmlReader.parse(String.valueOf(new File(xmlFile).toURI()));

            return classSax.getResult();

        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error("Error with SAX parser:\n" + e);
        } catch (IOException e){
            LOGGER.error("Error parsing XML file: " + xmlFile + "\n" + e);
        }

        return null;
    }
}
