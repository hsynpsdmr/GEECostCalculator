package com.example.geecostcalculator.ui.detail;

import android.provider.BaseColumns;

public class TablesInfo {

    public static final class DataEntry implements BaseColumns {
        public static final String TABLE_NAME = "data";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_SERVICE = "service";
        public static final String COLUMN_METER = "meter";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_COST = "cost";
    }
}
