/** 
 * Project Name:eve-server 
 * File Name:GlobalizationTable.java 
 * Package Name:com.s3s3l.eve.model.eve 
 * Date:Oct 10, 20174:39:42 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.s3s3l.eve.annotation.Primary;
import com.s3s3l.jdbc.bind.annotation.Column;
import com.s3s3l.jdbc.bind.annotation.SqlModel;

/**
 * <p>
 * </p>
 * ClassName:GlobalizationTable <br>
 * Date: Oct 10, 2017 4:39:42 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@SqlModel(table = "t_globalization")
@JsonInclude(Include.NON_DEFAULT)
public class GlobalizationTable extends Globalization {

    @Primary
    @Column(dbType = "varchar(36)")
    private String guid;
    @Column(dbType = "varchar(20)")
    private String id;
    @Column(dbType = "integer")
    private Integer type;
    @Column(dbType = "varchar(20)")
    private String field;

    public GlobalizationTable() {

    }

    public GlobalizationTable(Globalization g) {
        this.setDe(g.getDe());
        this.setEn(g.getEn());
        this.setEs(g.getEs());
        this.setFr(g.getFr());
        this.setIt(g.getIt());
        this.setJa(g.getJa());
        this.setRu(g.getRu());
        this.setZh(g.getZh());
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
