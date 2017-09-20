package com.voyage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Created by Vishwesh on 11-07-2016.
 */

public class Dia extends Dialog {

    ProgressBar progressBar;

    public Dia(Context context){
        super(context);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    //    this.setContentView(R.layout.mDialog);

       // progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
    }
}
