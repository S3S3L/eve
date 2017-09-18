/** 
 * Project Name:eve-server 
 * File Name:BluePrint.java 
 * Package Name:com.s3s3l.eve.model.eve 
 * Date:Sep 12, 20173:55:10 PM 
 * Copyright (c) 2017, kehewei@hellobike.com All Rights Reserved. 
 * 
*/

package com.s3s3l.eve.model.eve;

import java.util.List;

/**
 * <p>
 * </p>
 * ClassName:BluePrint <br>
 * Date: Sep 12, 2017 3:55:10 PM <br>
 * 
 * @author kehw_zwei
 * @version 1.0.0
 * @since JDK 1.8
 */
public class Blueprint {

    private Activities activities;
    private int blueprintTypeID;
    private int maxProductionLimit;

    public static class Activities {
        private Timing copying;
        private Manufacturing manufacturing;
        private Timing research_meterial;
        private Timing research_time;

        public static class Invention extends Timing {
            private List<Material> materials;
            private List<Product> products;
            private List<Skill> skills;

            public List<Material> getMaterials() {
                return materials;
            }

            public void setMaterials(List<Material> materials) {
                this.materials = materials;
            }

            public List<Product> getProducts() {
                return products;
            }

            public void setProducts(List<Product> products) {
                this.products = products;
            }

            public List<Skill> getSkills() {
                return skills;
            }

            public void setSkills(List<Skill> skills) {
                this.skills = skills;
            }
        }

        public static class Manufacturing extends Timing {
            private List<Material> materials;
            private List<Product> products;

            public List<Material> getMaterials() {
                return materials;
            }

            public void setMaterials(List<Material> materials) {
                this.materials = materials;
            }

            public List<Product> getProducts() {
                return products;
            }

            public void setProducts(List<Product> products) {
                this.products = products;
            }
        }

        public Timing getCopying() {
            return copying;
        }

        public void setCopying(Timing copying) {
            this.copying = copying;
        }

        public Manufacturing getManufacturing() {
            return manufacturing;
        }

        public void setManufacturing(Manufacturing manufacturing) {
            this.manufacturing = manufacturing;
        }

        public Timing getResearch_meterial() {
            return research_meterial;
        }

        public void setResearch_meterial(Timing research_meterial) {
            this.research_meterial = research_meterial;
        }

        public Timing getResearch_time() {
            return research_time;
        }

        public void setResearch_time(Timing research_time) {
            this.research_time = research_time;
        }
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    public int getBlueprintTypeID() {
        return blueprintTypeID;
    }

    public void setBlueprintTypeID(int blueprintTypeID) {
        this.blueprintTypeID = blueprintTypeID;
    }

    public int getMaxProductionLimit() {
        return maxProductionLimit;
    }

    public void setMaxProductionLimit(int maxProductionLimit) {
        this.maxProductionLimit = maxProductionLimit;
    }
}
