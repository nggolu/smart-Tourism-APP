package com.voyage;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;


public class ShowProgressDialog {
    Dia dia;

    public ShowProgressDialog(Context context) {



        dia = new Dia(context);
        dia.setCancelable(false);
        dia.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void show() {
        dia.show();
//        dai.progressBar.setVisibility(View.VISIBLE);

    }
    public void dismiss() {
        dia.dismiss();
     //   dai.progressBar.setVisibility(View.GONE);

    }

}
