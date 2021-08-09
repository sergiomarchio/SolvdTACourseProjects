package com.solvd.carfactory.sax;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SAXErrorHandler implements ErrorHandler {
    private final static Logger LOGGER = Logger.getLogger(SAXErrorHandler.class);

    public SAXErrorHandler() {
    }

    private String getParseErrorMessage(SAXParseException exception) {
        return "URI: " + exception.getSystemId() + "\n"
                + "Line: " + exception.getLineNumber() + "\n"
                + exception.getMessage();
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        LOGGER.debug("Warning:\n" + getParseErrorMessage(exception));
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        throw new SAXException("Error:\n" + getParseErrorMessage(exception));
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        throw new SAXException("FATAL ERROR:\n" + getParseErrorMessage(exception));
    }
}
