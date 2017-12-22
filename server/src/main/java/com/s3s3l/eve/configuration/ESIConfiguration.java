/** 
 * Project Name:eve-server 
 * File Name:ESIConfiguration.java 
 * Package Name:com.s3s3l.eve.configuration 
 * Date:Sep 29, 20173:29:36 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.s3s3l.eve.configuration.base.HttpInterface;
import com.s3s3l.eve.model.enumetrations.esi.EnumDatasource;
import com.s3s3l.eve.model.enumetrations.esi.EnumLanguage;
import com.s3s3l.eve.model.enumetrations.esi.EnumVersion;

/**
 * <p>
 * </p>
 * ClassName:ESIConfiguration <br>
 * Date: Sep 29, 2017 3:29:36 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Configuration
@ConfigurationProperties(prefix = "esi")
public class ESIConfiguration {
    private Interfaces interfaces;
    private List<EnumLanguage> languages;
    private String endpoint;
    private EnumVersion version;
    private EnumDatasource datasource;
    private int retryCount;
    private String totalPageKey;
    private long timeout;

    public static class Interfaces {
        private HttpInterface region;
        private HttpInterface constellation;
        private HttpInterface system;
        private HttpInterface planet;
        private HttpInterface moon;
        private HttpInterface star;
        private HttpInterface type;
        private HttpInterface group;
        private HttpInterface orders;

        public HttpInterface getRegion() {
            return region;
        }

        public void setRegion(HttpInterface region) {
            this.region = region;
        }

        public HttpInterface getConstellation() {
            return constellation;
        }

        public void setConstellation(HttpInterface constellation) {
            this.constellation = constellation;
        }

        public HttpInterface getSystem() {
            return system;
        }

        public void setSystem(HttpInterface system) {
            this.system = system;
        }

        public HttpInterface getPlanet() {
            return planet;
        }

        public void setPlanet(HttpInterface planet) {
            this.planet = planet;
        }

        public HttpInterface getMoon() {
            return moon;
        }

        public void setMoon(HttpInterface moon) {
            this.moon = moon;
        }

        public HttpInterface getStar() {
            return star;
        }

        public void setStar(HttpInterface star) {
            this.star = star;
        }

        public HttpInterface getType() {
            return type;
        }

        public void setType(HttpInterface type) {
            this.type = type;
        }

        public HttpInterface getGroup() {
            return group;
        }

        public void setGroup(HttpInterface group) {
            this.group = group;
        }

        public HttpInterface getOrders() {
            return orders;
        }

        public void setOrders(HttpInterface orders) {
            this.orders = orders;
        }
    }

    public Interfaces getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Interfaces interfaces) {
        this.interfaces = interfaces;
    }

    public List<EnumLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<EnumLanguage> languages) {
        this.languages = languages;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public EnumVersion getVersion() {
        return version;
    }

    public void setVersion(EnumVersion version) {
        this.version = version;
    }

    public EnumDatasource getDatasource() {
        return datasource;
    }

    public void setDatasource(EnumDatasource datasource) {
        this.datasource = datasource;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getTotalPageKey() {
        return totalPageKey;
    }

    public void setTotalPageKey(String totalPageKey) {
        this.totalPageKey = totalPageKey;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
