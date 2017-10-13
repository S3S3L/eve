/** 
 * Project Name:eve-server 
 * File Name:ResourceLoaderTest.java 
 * Package Name:model 
 * Date:Sep 15, 20174:40:54 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.items.Type;
import com.s3s3l.resource.JacksonUtil;
import com.s3s3l.utils.file.FileUtil;

/**
 * <p>
 * </p>
 * ClassName:ResourceLoaderTest <br>
 * Date: Sep 15, 2017 4:40:54 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ResourceLoaderTest {

    @Test
    public void blueprintTest() throws IOException {
        Map<Integer, Blueprint> blueprintMap = JacksonUtil.create(new YAMLFactory())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .toObject(FileUtil.getFirstExistFile("file:eve_data/fsd/blueprints.yaml"),
                        new TypeReference<Map<Integer, Blueprint>>() {
                        });

        assertEquals(blueprintMap.get(681)
                .getActivities()
                .getCopying()
                .getTime(), 480);
    }

    @Test
    public void typeTest() throws IOException {
        Map<Integer, Type> blueprintMap = JacksonUtil.create(new YAMLFactory())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .toObject(FileUtil.getFirstExistFile("file:eve_data/fsd/typeIDs.yaml"),
                        new TypeReference<Map<Integer, Type>>() {
                        });

        assertEquals(blueprintMap.get(9247)
                .getSofFactionName(), "minmatarbase");
    }
}
