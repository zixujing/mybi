package com.trans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "connection")

@TableName(value = "tb_db")
public class DB implements XmlInterface{

    @NotEmpty(message = "000_000")
    //连接的名字
    private String name;
    @NotEmpty(message = "000_000")
    private String server;

    @NotEmpty(message = "000_000")
    private String type;
    @NotEmpty(message = "000_000")
    private String access;
    @NotEmpty(message = "000_000")
    @XmlElement(name = "database")
    private String dbs;
    //@NotEmpty(message = "000_000")
    private int port;
    @NotEmpty(message = "000_000")
    private String username;
    @NotEmpty(message = "000_000")
    private String password;

    @TableField(exist = false)
    private DBAttribute attributes;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getDbs() {
        return dbs;
    }

    public void setDbs(String dbs) {
        this.dbs = dbs;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DBAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(DBAttribute attributes) {
        this.attributes = attributes;
    }


}
