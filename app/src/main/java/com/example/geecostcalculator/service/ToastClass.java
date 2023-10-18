package com.example.geecostcalculator.service;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.geecostcalculator.R;

public class ToastClass extends AppCompatActivity {
    ImageView toast_image;
    TextView toast_text;
    LinearLayout custom_toast;
    View toastView;
    String status;
    public void toastIconSuccess(final AppCompatActivity activity) {
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        View custom_view = activity.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText("Success!");
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(activity.getColor(R.color.green_500));

        toast.setView(custom_view);
        toast.show();
    }

    public void toastIconSuccess(final AppCompatActivity activity,String message) {
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        View custom_view = activity.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(activity.getColor(R.color.green_500));

        toast.setView(custom_view);
        toast.show();
    }

    public void toastIconError(final AppCompatActivity activity,String message) {
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        View custom_view = activity.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_close    );
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(activity.getColor(R.color.red_500));

        toast.setView(custom_view);
        toast.show();
    }

    public void toastIconFail(final AppCompatActivity activity,String message) {
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        View custom_view = activity.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline    );
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(activity.getColor(R.color.red_500));

        toast.setView(custom_view);
        toast.show();
    }

    public void toastIconFail(final Fragment fragment, String message) {
        Toast toast = new Toast(fragment.getContext());
        toast.setDuration(Toast.LENGTH_LONG);

        View custom_view = fragment.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline    );
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(fragment.getActivity().getColor(R.color.red_500));

        toast.setView(custom_view);
        toast.show();
    }






}
