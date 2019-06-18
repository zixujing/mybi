package com.config;

import com.util.MyException;
import com.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping(value = "mykettle")
public class ConfigControl {
    @Autowired
    ConfigService configService;
    @RequestMapping(value = "config",method = {RequestMethod.GET})
    public RestResult getConfig() throws MyException {
        return configService.getConfig();
    }
    @RequestMapping(value = "config",method = {RequestMethod.PUT})
    public RestResult setConfig( String key,String value) throws ConstraintViolationException,
            MyException {
        return configService.putConfig(key,value);
    }
}
