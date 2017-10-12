/** 
 * Project Name:eve-server 
 * File Name:Type.java 
 * Package Name:com.s3s3l.eve.model.eve 
 * Date:Sep 15, 20174:24:59 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve.items;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.s3s3l.eve.model.eve.Globalization;

/**
 * <p>
 * </p>
 * ClassName:Type <br>
 * Date: Sep 15, 2017 4:24:59 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class Type {
    private int groupID;
    /**
     * 质量
     */
    private Double mass;
    private Globalization name;
    private Integer portionSize;
    private boolean published;
    /**
     * 体积
     */
    private Double volume;
    private Double radius;
    private Globalization description;
    private Integer graphicID;
    private Integer soundID;
    private Integer marketGroupID;
    private Integer iconID;
    private BigDecimal basePrice;
    private Integer raceID;
    private String sofFactionName;
    /**
     * 容量
     */
    private Integer capacity;
    /**
     * 专精
     */
    private Map<Integer, List<Integer>> masteries;
    /**
     * 特性
     */
    private Trait traits;

    /**
     * <p>
     * 特性
     * </p>
     * ClassName: Trait <br>
     * date: Sep 15, 2017 5:34:33 PM <br>
     * 
     * @author kehw_zwei
     * @version 1.0.0 Type
     * @since JDK 1.8
     */
    public static class Trait {
        private List<MiscBonus> miscBonuses;
        private List<RoleBonus> roleBonuses;
        private Map<Integer, List<BonusType>> types;

        public static class Bonus {
            private Integer importance;
            private Integer unitID;

            public Integer getImportance() {
                return importance;
            }

            public void setImportance(Integer importance) {
                this.importance = importance;
            }

            public Integer getUnitID() {
                return unitID;
            }

            public void setUnitID(Integer unitID) {
                this.unitID = unitID;
            }
        }

        public static class MiscBonus extends Bonus {
            private Integer nameID;

            public Integer getNameID() {
                return nameID;
            }

            public void setNameID(Integer nameID) {
                this.nameID = nameID;
            }
        }

        public static class RoleBonus extends Bonus {
            private Integer bonus;
            private Globalization bonusText;

            public Integer getBonus() {
                return bonus;
            }

            public void setBonus(Integer bonus) {
                this.bonus = bonus;
            }

            public Globalization getBonusText() {
                return bonusText;
            }

            public void setBonusText(Globalization bonusText) {
                this.bonusText = bonusText;
            }
        }

        public static class BonusType extends Bonus {
            private Integer bonus;
            private Globalization bonusText;

            public Integer getBonus() {
                return bonus;
            }

            public void setBonus(Integer bonus) {
                this.bonus = bonus;
            }

            public Globalization getBonusText() {
                return bonusText;
            }

            public void setBonusText(Globalization bonusText) {
                this.bonusText = bonusText;
            }
        }

        public List<MiscBonus> getMiscBonuses() {
            return miscBonuses;
        }

        public void setMiscBonuses(List<MiscBonus> miscBonuses) {
            this.miscBonuses = miscBonuses;
        }

        public List<RoleBonus> getRoleBonuses() {
            return roleBonuses;
        }

        public void setRoleBonuses(List<RoleBonus> roleBonuses) {
            this.roleBonuses = roleBonuses;
        }

        public Map<Integer, List<BonusType>> getTypes() {
            return types;
        }

        public void setTypes(Map<Integer, List<BonusType>> types) {
            this.types = types;
        }
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Globalization getName() {
        return name;
    }

    public void setName(Globalization name) {
        this.name = name;
    }

    public Integer getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(Integer portionSize) {
        this.portionSize = portionSize;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Globalization getDescription() {
        return description;
    }

    public void setDescription(Globalization description) {
        this.description = description;
    }

    public Integer getGraphicID() {
        return graphicID;
    }

    public void setGraphicID(Integer graphicID) {
        this.graphicID = graphicID;
    }

    public Integer getSoundID() {
        return soundID;
    }

    public void setSoundID(Integer soundID) {
        this.soundID = soundID;
    }

    public Integer getMarketGroupID() {
        return marketGroupID;
    }

    public void setMarketGroupID(Integer marketGroupID) {
        this.marketGroupID = marketGroupID;
    }

    public Integer getIconID() {
        return iconID;
    }

    public void setIconID(Integer iconID) {
        this.iconID = iconID;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getRaceID() {
        return raceID;
    }

    public void setRaceID(Integer raceID) {
        this.raceID = raceID;
    }

    public String getSofFactionName() {
        return sofFactionName;
    }

    public void setSofFactionName(String sofFactionName) {
        this.sofFactionName = sofFactionName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Map<Integer, List<Integer>> getMasteries() {
        return masteries;
    }

    public void setMasteries(Map<Integer, List<Integer>> masteries) {
        this.masteries = masteries;
    }

    public Trait getTraits() {
        return traits;
    }

    public void setTraits(Trait traits) {
        this.traits = traits;
    }
}
