package org.smallbun.fast.manage.mybi.entity;

import com.alibaba.fastjson.JSONObject;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ParameterEntity {
    private String form_data;
    private String standalone="true";
    private String height="400";

    public String getForm_data() {
        return form_data;
    }

    public void setForm_data(String form_data) {
        JSONObject object= JSONObject.parseObject(form_data);
        this.form_data=object.toJSONString();
    }

    public String getStandalone() {
        return standalone;
    }

    public void setStandalone(String standalone) {
        this.standalone = standalone;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    public void setPars(String key,Object value){
        JSONObject object= JSONObject.parseObject(form_data);
        object.put(key,value);
        form_data=object.toJSONString();
    }
    /**
     * 按照key排序得到参数列表字符串
     * @return  参数列表字符串
     */
    public String getParamsOrderByKey() throws UnsupportedEncodingException {
        return "?form_data="+ URLEncoder.encode(form_data)+"&standalone="+standalone+"&height="+height;
    }
}
