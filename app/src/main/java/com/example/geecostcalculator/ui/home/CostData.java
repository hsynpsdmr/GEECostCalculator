package com.example.geecostcalculator.ui.home;

import java.util.Date;

public class CostData {

    int id;
    private String serviceNumber;
    private String meter;
    private String date;
    private String cost;

    public CostData(int id, String serviceNumber, String meter, String date, String cost) {
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.meter = meter;
        this.date = date;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
