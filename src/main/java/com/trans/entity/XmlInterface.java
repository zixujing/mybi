package com.trans.entity;

import com.config.Config;
import com.util.MyException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public interface XmlInterface {
    public final String ENCODING="encoding";
    default public String convertToXml(Config config) throws JAXBException, MyException {
        JAXBContext context = JAXBContext.newInstance(this.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING,Config.getConfig(config).get(ENCODING) );
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(this, stringWriter);
        return stringWriter.toString();
    }
}
