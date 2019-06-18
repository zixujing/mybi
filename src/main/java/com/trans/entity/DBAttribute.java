package com.trans.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attribute")
public class DBAttribute {
    private String code;
    private String attribute;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
