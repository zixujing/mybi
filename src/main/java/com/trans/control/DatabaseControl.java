package com.trans.control;

import com.trans.entity.DB;
import com.trans.entity.DBAttribute;
import com.trans.service.DBService;
import com.util.RestResult;
import org.apache.poi.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import javax.xml.bind.JAXBException;
import java.util.HashMap;

@RestController
@RequestMapping(value = "trans")
public class DatabaseControl {
    @Autowired
    DBService dbService;
    @RequestMapping(value = "dbs",method = {RequestMethod.GET})
    public RestResult getDbs(){
        return dbService.getDbs();
    }
    @RequestMapping(value = "dbs",method = {RequestMethod.PUT})
    public RestResult pugDbs(@Validated DB db, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return dbService.pugDbs(db);
    }


}
