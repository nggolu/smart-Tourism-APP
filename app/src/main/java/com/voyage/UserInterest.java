package com.voyage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInterest extends AppCompatActivity implements View.OnClickListener{
    Bundle bundle;
    TextView txt1,txt2;
    int chBoxMonu=0 ,chBoxWildLife =0,chBoxMuseum =0,chBoxReligious =0,chBoxShopping =0,chBoxOther=0;
    ImageView checkBox1, checkBox2, checkBox3,checkBox4,checkBox5,checkBox6;
    Button submit;
    int flag =0 ;
    String checkedItem ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = getIntent().getExtras();
        checkedItem = "";
            //religious  , monuments , shopping, Wildlife, Museum,others
        submit = (Button) findViewById(R.id.btsub);
        checkBox1 = (ImageView) findViewById(R.id.checkbox1);
        checkBox2 = (ImageView) findViewById(R.id.checkbox2);
        checkBox3 = (ImageView) findViewById(R.id.checkbox3);
        checkBox4 = (ImageView) findViewById(R.id.checkbox4);
        checkBox5 = (ImageView) findViewById(R.id.checkbox5);
        checkBox6 = (ImageView) findViewById(R.id.checkbox6);

        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBox5.setOnClickListener(this);
        checkBox6.setOnClickListener(this);
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.checkbox1:
                if(chBoxReligious == 0) {
                    checkBox1.setImageDrawable(getResources().getDrawable(R.drawable.checked1));

                    chBoxReligious = 1;
                    checkedItem= checkedItem+"religious"+",";
                    break;
                }
                else {
                    checkBox1.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxReligious = 0;
                    break;
                }
            case R.id.checkbox2:
                if(chBoxMonu == 0) {
                    checkBox2.setImageDrawable(getResources().getDrawable( R.drawable.checked1));
                    chBoxMonu =1;
                    checkedItem+="monuments"+",";
                    break;
                }
                else {
                    checkBox2.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxMonu =0;
                    break;
                }
            case R.id.checkbox3:
                if(chBoxShopping == 0) {
                    checkBox3.setImageDrawable(getResources().getDrawable(R.drawable.checked1));
                    chBoxShopping = 1;
                    checkedItem+="Shopping"+",";
                    break;
                }
                else {
                    checkBox3.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxShopping = 0;
                    break;
                }
            case R.id.checkbox4:
                if(chBoxWildLife == 0) {
                    checkBox4.setImageDrawable(getResources().getDrawable(R.drawable.checked1));
                    chBoxWildLife = 1;
                    checkedItem+="wildlife"+",";
                    break;
                }
                else {
                    checkBox4.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxWildLife = 0;

                    break;
                }
            case R.id.checkbox5:
                if(chBoxMuseum == 0) {
                    checkBox5.setImageDrawable(getResources().getDrawable(R.drawable.checked1));
                    chBoxMuseum = 1;
                    checkedItem+="museum"+",";
                    break;
                }
                else {
                    checkBox5.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxMuseum = 0;
                    break;
                }
            case R.id.checkbox6:
                if(chBoxOther == 0) {
                    checkBox6.setImageDrawable(getResources().getDrawable(R.drawable.checked1));
                    chBoxOther = 1;
                    checkedItem+="others"+",";
                    break;
                }
                else{
                    checkBox6.setImageDrawable(getResources().getDrawable(R.drawable.check1));
                    chBoxOther = 0;
                    break;
                }

            case R.id.btsub:
                flag=1;
                Intent i = new Intent(UserInterest.this,RecommendedPlaces.class);
                new AppPreferences().setInterestPlaces(UserInterest.this,checkedItem);
                startActivity(i);
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        if(flag == 1)
        {
            checkedItem="";
            chBoxOther=0;
                    chBoxMonu=0;
                    chBoxMuseum=0;chBoxReligious=0;chBoxShopping=0;chBoxWildLife=0;
            flag=0;
            checkBox1.setImageDrawable(getResources().getDrawable(R.drawable.check1));
            checkBox2.setImageDrawable(getResources().getDrawable(R.drawable.check1));
            checkBox3.setImageDrawable(getResources().getDrawable(R.drawable.check1));
            checkBox4.setImageDrawable(getResources().getDrawable(R.drawable.check1));
            checkBox5.setImageDrawable(getResources().getDrawable(R.drawable.check1));
            checkBox6.setImageDrawable(getResources().getDrawable(R.drawable.check1));



        }
    }
}
