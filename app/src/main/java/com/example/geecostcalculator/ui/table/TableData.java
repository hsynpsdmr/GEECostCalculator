package com.example.geecostcalculator.ui.table;

public class TableData {
    private int min;
    private int max;
    private int money;

    public TableData(int min, int max, int money) {
        setMin(min);
        setMax(max);
        setMoney(money);
    }
    public TableData() {
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
