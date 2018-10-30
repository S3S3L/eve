/** 
 * Project Name:eve-server 
 * File Name:Globalization.java 
 * Package Name:com.s3s3l.eve.model.eve 
 * Date:Sep 15, 20174:23:57 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.s3s3l.jdbc.bind.annotation.Column;

/**
 * <p>
 * </p>
 * ClassName:Globalization <br>
 * Date: Sep 15, 2017 4:23:57 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@JsonInclude(Include.NON_DEFAULT)
public class Globalization {

    @Column(dbType = "longvarchar")
    private String de;
    @Column(dbType = "longvarchar")
    private String en;
    @Column(dbType = "longvarchar")
    private String es;
    @Column(dbType = "longvarchar")
    private String fr;
    @Column(dbType = "longvarchar")
    private String it;
    @Column(dbType = "longvarchar")
    private String ja;
    @Column(dbType = "longvarchar")
    private String ru;
    @Column(dbType = "longvarchar")
    private String zh;

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

}
