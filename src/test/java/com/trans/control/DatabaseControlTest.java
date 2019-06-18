package com.trans.control;

import com.config.Config;
import com.trans.entity.DB;
import com.trans.entity.DBAttribute;
import com.util.MyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseControlTest {
    @Autowired
    Config config;
    @Test
    public void test() throws JAXBException, MyException {

        DB db=new DB();
        db.setAccess("Native");
        db.setName("myname");
        db.setServer("192.168.106.174");
        db.setType("Oracle");
        db.setDbs("orcl");
        db.setPort(1521);
        db.setUsername("smartecap_data");
        db.setPassword("123456");
        DBAttribute dbAttribute=new DBAttribute();
        dbAttribute.setCode("EXTRA_OPTION_ORACLE.characterEncoding");
        dbAttribute.setAttribute("utf-8");
        db.setAttributes(dbAttribute);
        String xmlStr=db.convertToXml(config);
        System.out.println(xmlStr);
    }


}