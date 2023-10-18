package com.example.geecostcalculator.ui.table;

import static com.example.geecostcalculator.constants.Constant.TABLE_HEADERS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.geecostcalculator.constants.Constant;
import com.example.geecostcalculator.databinding.FragmentTableBinding;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class TableFragment extends Fragment {

    private FragmentTableBinding binding;
    private String[][] data ={{},{},{}};
    TableView tableView;

    View root;
   public List<TableData> dataList = new ArrayList<TableData>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TableViewModel notificationsViewModel =
                new ViewModelProvider(this).get(TableViewModel.class);
        binding = FragmentTableBinding.inflate(inflater, container, false);
         root = binding.getRoot();
         tableView  = binding.tableView;

        getsetTable();
        setTableData();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public List<TableData> getsetTable(){
        dataList.add(new TableData(1,100,5));
        dataList.add(new TableData(101,500,8));
        dataList.add(new TableData(500,0,10));

        return dataList;
    }

    public void setTableData(){

        for (int i = 0; i < dataList.size(); i++) {
            TableData td= dataList.get(i);
            String max;
            if(td.getMax()==0){
                max="";
            }else{
                max=String.valueOf(td.getMax());
            }
            data[i] = new String[]{td.getMin()+"-"+max+" units", "@ $"+td.getMoney()+" / unit"};
        }
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(root.getContext(), TABLE_HEADERS));
        tableView.setDataAdapter(new SimpleTableDataAdapter(root.getContext(),data));
    }

}