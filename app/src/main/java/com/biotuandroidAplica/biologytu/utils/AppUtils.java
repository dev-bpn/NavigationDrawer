package com.biotuandroidAplica.biologytu.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.biotuandroidAplica.biologytu.R;

/**
 * Created by Dell on 4/2/2016.
 */
public class AppUtils {

    public static void showSnackBar(View view , String message , Context context){

        Snackbar snackbar = Snackbar.make(view, message ,Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.primaryDark));
        snackbar.show();
    }

    public static void showToast(Context context , String message){

        Toast.makeText(context , message , Toast.LENGTH_SHORT).show();

    }

    public static void showLog(String message){

        Log.e(GlobalVariable.TAG , message);

    }

}
