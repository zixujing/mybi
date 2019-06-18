package com.config;


import com.util.MyException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Service
public class Config {
    private static final String STR_CLASS="class";
    @Autowired
    private InitConfig initConfig;

    public InitConfig getInitConfig() {
        return initConfig;
    }

    private static Map<String,String> config=null;
    private Config(){
    }

    private String encoding;
    public static   Map<String, String> getConfig(Config configFromSpringBoot) throws MyException {
        if(config==null){
            synchronized (Config.class){
                if(config==null){
                    try {
                        config= BeanUtils.describe(configFromSpringBoot.getInitConfig());
                    } catch (IllegalAccessException e) {
                        throw MyException.GET_EXCEPTION(EC___CONFIG_FAIL_CODE);
                    } catch (InvocationTargetException e) {
                        throw MyException.GET_EXCEPTION(EC___CONFIG_FAIL_CODE);
                    } catch (NoSuchMethodException e) {
                        throw MyException.GET_EXCEPTION(EC___CONFIG_FAIL_CODE);
                    }
                    if(config.containsKey(STR_CLASS)){
                        config.remove(STR_CLASS);
                    }
                }
                return config;
            }
        }
        return config;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public static final HashMap<String,String> ERROR=new HashMap(){
        {
            put(EC___DEFAULT,"_____!(-_-)....Zz%*%");
            put(EC_C_CONFIG_KEYPARNOTHAVE_ERROR,"配置文件中不包含此参数，请指定一个有效的配置参数！");
            put(EC_C_CONFIG_KEYPARERROR,"传入参数key值无效，请指定key的内容！");
            put(EC_C_CONFIG_VALUEPARERROR,"传入参数value值无效，请指定value的内容！");
            put(EC___CONFIG_FAIL_CODE,"程序读取配置文件的配置项时失败，配置项存放到内存时发生转换失败！");
        }
    };
    /**
     * EC error code
     * C control S service M mode
     * COFNIG 类名
     * PARERROR 说明
     */
    public static final String EC___DEFAULT="000_000";

    public static final String EC___CONFIG_FAIL_CODE="000_001";

    public static final String EC_C_CONFIG_KEYPARNOTHAVE_ERROR="001_001";
    public static final String EC_C_CONFIG_KEYPARERROR="001_002";
    public static final String EC_C_CONFIG_VALUEPARERROR="001_003";
}
