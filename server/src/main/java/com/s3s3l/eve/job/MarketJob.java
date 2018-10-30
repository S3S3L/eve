/** 
 * Project Name:eve-server 
 * File Name:MarketJob.java 
 * Package Name:com.s3s3l.eve.job 
 * Date:Oct 17, 20175:34:12 PM 
 * Copyright (c) 2017, kehw.zwei@gmail.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.s3s3l.data.cache.CacheHelper;
import com.s3s3l.eve.configuration.BusinessConfiguration;
import com.s3s3l.eve.model.enumetrations.eve.EnumCacheScope;
import com.s3s3l.eve.model.eve.items.Blueprint;
import com.s3s3l.eve.model.eve.market.BlueprintTradeInfo;
import com.s3s3l.eve.service.CommonService;
import com.s3s3l.eve.service.MarketService;
import com.s3s3l.utils.concurrent.TaskExecutor;

/**
 * <p>
 * </p>
 * ClassName:MarketJob <br>
 * Date: Oct 17, 2017 5:34:12 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component
@Configurable
@EnableScheduling
public class MarketJob {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BusinessConfiguration businessConfiguration;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private CacheHelper<String, Object> cache;

    @Scheduled(cron = "0 08 0/1 * * ?")
    public void blueprintCalculate() {
        logger.info("Starting blueprint calculate job...");
        List<Blueprint> blueprints = commonService.getBlueprint(new Blueprint());
        for (final String regionID : businessConfiguration.getWatchingRegions()) {
            logger.info("Starting blueprint calculate job for region [{}]", regionID);
            Map<String, BlueprintTradeInfo> tradeCache = new HashMap<>();
            List<Callable<BlueprintTradeInfo>> calls = new ArrayList<>();
            for (Blueprint blueprint : blueprints) {
                final String blueprintID = blueprint.getBlueprintTypeID();
                calls.add(() -> {
                    try {
                        return new BlueprintTradeInfo().setRegionID(regionID)
                                .setBlueprintID(blueprintID)
                                .setCost(marketService.getCost(regionID, blueprintID))
                                .setSell(marketService.getIncome(regionID, blueprintID))
                                .calculateIncome();
                    } catch (Exception e) {
                        logger.warn("fail to fetch calculated data for blueprint [{}] in region [{}]. Caused by: {}",
                                blueprintID, regionID, e);
                        return null;
                    }
                });
            }

            taskExecutor.collect(calls)
                    .stream()
                    .filter(r -> r != null)
                    .forEach(r -> tradeCache.put(r.getBlueprintID(), r));
            cache.put(regionID, tradeCache, EnumCacheScope.Business.name());
            logger.info("Finished blueprint calculate job for region [{}]", regionID);
        }
        logger.info("Finished blueprint calculate job...");
    }
    
    public void cacheOrders() {
        // TODO 定时缓存关注的蓝图相关的订单
    }
}
