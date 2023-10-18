package com.example.geecostcalculator.ui.home;

import static com.example.geecostcalculator.constants.Constant.ALPHANUMERIC;
import static com.example.geecostcalculator.constants.Constant.ONLYNUMERIC;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.geecostcalculator.service.ToastClass;
import com.example.geecostcalculator.service.ValidateFilter;
import com.example.geecostcalculator.databinding.FragmentHomeBinding;
import com.example.geecostcalculator.service.DatabaseHelper;
import com.example.geecostcalculator.ui.detail.DataDetail;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ArrayList<CostData> dataList = new ArrayList<>();
    ToastClass toastClass;
    String serviceNumberText;
    int meter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
toastClass=new ToastClass();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final  EditText serviceNumber = binding.etvServiceNumber;
        final  EditText currentMeter = binding.etvCurrentMeter;
        final Button submitButton=binding.btnSubmitButton;
        serviceNumber.setFilters( new InputFilter[]{ new ValidateFilter(ALPHANUMERIC,HomeFragment.this), new InputFilter.LengthFilter(10)}) ;
        currentMeter.setFilters( new InputFilter[]{ new ValidateFilter(ONLYNUMERIC,HomeFragment.this)}) ;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 serviceNumberText=Objects.requireNonNull(serviceNumber.getText()).toString();
                 meter=Integer.parseInt(currentMeter.getText().toString());
                DatabaseHelper db = new DatabaseHelper(getContext());
                dataList = db.getDataList(serviceNumberText);
                if(dataList.size()!=0){
                    int dataMeter=Integer.parseInt(dataList.get(dataList.size()-1).getMeter());
                    if(dataMeter>meter){
                        toastClass.toastIconFail(HomeFragment.this,"Girdiğiniz endeks son okunan endeksten küçük olamaz");
                    }else {
                        goTtoDetail();
                    }
                }else{
                    goTtoDetail();
                }

            }
        });

        return root;
    }

    public void goTtoDetail(){
        Intent intent = new Intent(getContext(), DataDetail.class);
        intent.putExtra("SERVICE_NUMBER", serviceNumberText);
        intent.putExtra("CURRENT_METER", meter);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}