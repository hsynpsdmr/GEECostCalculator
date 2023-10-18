package com.example.geecostcalculator.service;

import android.text.InputFilter ;
import android.text.Spanned ;
import android.util.Log ;

import androidx.fragment.app.Fragment;

import java.util.regex.Matcher ;
import java.util.regex.Pattern ;

public class ValidateFilter implements InputFilter {
    private String regex;
    ToastClass toastClass;
    private Fragment fragment;
    public ValidateFilter(String regex,Fragment fragment) {
        toastClass = new ToastClass();
        this.regex = regex;
        this.fragment=fragment;
    }

    @Override
    public CharSequence filter (CharSequence source , int start , int end , Spanned dest ,
                                int dstart , int dend) {
        for ( int i = start ; i < end ; i++) {
            String checkMe = String. valueOf (source.charAt(i)) ;
            Pattern pattern = Pattern. compile (regex) ;
            Matcher matcher = pattern.matcher(checkMe) ;
            boolean valid = matcher.matches() ;
            if (!valid) {
                Log. d ( "" , "invalid" ) ;
                toastClass.toastIconFail(fragment,"Geçersiz karakter");
                return "" ;
            }
        }
        return null;
    }
}
