package com.config;

import com.util.MyException;
import com.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Service
@Validated
public class ConfigService {
    @Autowired
    Config config;
    public RestResult putConfig(@NotEmpty(message =Config.EC_C_CONFIG_KEYPARERROR ) String key,
                                @NotEmpty(message = Config.EC_C_CONFIG_VALUEPARERROR) String value)
            throws ConstraintViolationException,
            MyException {

        Map configInstance =config.getConfig(config);
        if(configInstance.containsKey(key)){
            configInstance.put(key,value);
        }else {
            throw MyException.GET_EXCEPTION(Config.EC_C_CONFIG_KEYPARNOTHAVE_ERROR);
        }

        return RestResult.SUCCESS_REST_RESULT(configInstance);
    }

    public RestResult getConfig() throws MyException {
        return RestResult.SUCCESS_REST_RESULT(config.getConfig(config));
    }
}
