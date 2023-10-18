package com.example.geecostcalculator.ui.detail;

import static com.example.geecostcalculator.constants.Constant.TABLE_HEADERS_DATA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geecostcalculator.MainActivity;
import com.example.geecostcalculator.R;
import com.example.geecostcalculator.service.DatabaseHelper;
import com.example.geecostcalculator.ui.home.CostData;
import com.example.geecostcalculator.ui.table.TableData;
import com.example.geecostcalculator.ui.table.TableFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class DataDetail extends AppCompatActivity {


    public String serviceNumber;
    public int currentMeter;
    TableView tableView;
    Button saveButton;


    TextView consumptionCost;
    ArrayList<CostData> dataList = new ArrayList<>();
    int totalCost=0;
    int lastmeter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail);
        tableView = (TableView) findViewById(R.id.tv_data_detail);
        TableColumnWeightModel columnModel = new TableColumnWeightModel(3);
        columnModel.setColumnWeight(0, 2);
        columnModel.setColumnWeight(1, 1);
        columnModel.setColumnWeight(2, 1);
        tableView.setColumnModel(columnModel);
        consumptionCost = (TextView) findViewById(R.id.tv_consumption_cost);
        saveButton=findViewById(R.id.btn_save_button);


        serviceNumber = getIntent().getExtras().get("SERVICE_NUMBER").toString();
        currentMeter = getIntent().getIntExtra("CURRENT_METER", 0);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData(totalCost);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        getDataDetailByServiceNumber(serviceNumber);
        calculate();


    }

    public void addData(int total){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        CostData cs=new CostData(0,serviceNumber,String.valueOf(currentMeter),sdf.format(new Date()), String.valueOf(total));
        db.addData(cs);
    }

    public void getDataDetailByServiceNumber(String serviceNumber){
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        dataList = db.getDataList(serviceNumber);

        while(dataList.size()>3){
            db.deleteData(dataList.get(0).getId());
            dataList.remove(0);

        }

         //String[][] data = new String[dataList.size()][3];
         String[][] data = {{},{},{}};

        if(dataList!=null){
            for (int i = 0; i < dataList.size(); i++) {

                CostData cost= (CostData) dataList.get(i);
                data[i]= new String[]{String.valueOf(cost.getDate()), String.valueOf(cost.getMeter()), cost.getCost()+"$"};

            }
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS_DATA));
            tableView.setDataAdapter(new SimpleTableDataAdapter(this, data));
        }



    }

    public void calculate(){

        TableFragment tf=new TableFragment();
         List<TableData> dl = new ArrayList<TableData>();
        dl=tf. getsetTable();
        for (int i = 0; i <dl.size() ; i++) {
           TableData td=new TableData();
           td=dl.get(i);

           if(currentMeter<dl.get(2).getMin()){
               if(currentMeter>td.getMax()){
                   lastmeter=currentMeter-td.getMax();
                   totalCost=totalCost+(td.getMax()*td.getMoney());
               }else{
                   totalCost=totalCost+(lastmeter*td.getMoney());
               }
           }else{
               if(currentMeter>td.getMax()&&td.getMax()!=0){
                   lastmeter=currentMeter-td.getMax();
                   totalCost=totalCost+((td.getMax()-td.getMin()+1)*td.getMoney());
               }else{
                   totalCost=totalCost+(lastmeter*td.getMoney());
               }
           }

        }
        consumptionCost.setText(totalCost+"$");
    }
}