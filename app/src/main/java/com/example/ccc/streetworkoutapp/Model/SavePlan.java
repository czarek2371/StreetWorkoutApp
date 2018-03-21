package com.example.ccc.streetworkoutapp.Model;

import java.util.List;

/**
 * Created by ccc on 21.03.2018.
 */

public class SavePlan {
    private String planName;
    private List<Plan> myplan;

    public SavePlan() {
    }

    public SavePlan(String planName, List<Plan> myplan) {
        this.planName = planName;
        this.myplan = myplan;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public List<Plan> getMyplan() {
        return myplan;
    }

    public void setMyplan(List<Plan> myplan) {
        this.myplan = myplan;
    }
}












































