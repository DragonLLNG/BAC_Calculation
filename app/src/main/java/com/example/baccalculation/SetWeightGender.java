package com.example.baccalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SetWeightGender extends AppCompatActivity {

    Integer num_weight;
    EditText weight_input;
    RadioGroup gender;
    String genderReturn;
    Button setw, cancel;
    final static public String KEY = "KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_weight_gender);
        setTitle("Set Weigh/Gender");


        setw = findViewById(R.id.set2);
        setw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                weight_input = findViewById(R.id.weight_input);
                num_weight = Integer.parseInt(weight_input.getText().toString());

                gender = findViewById(R.id.gender_group);
                int checked = gender.getCheckedRadioButtonId();
                if(checked==R.id.female){
                    genderReturn="Female";
                }
                else if (checked==R.id.male){
                    genderReturn="Male";
                }
                if(weight_input.getText().length()==0 || gender.getCheckedRadioButtonId() == -1){
                    Toast toast2 = Toast.makeText(SetWeightGender.this, "Set weight and gender first.", Toast.LENGTH_SHORT);
                    toast2.setGravity(Gravity.CENTER, 0, 0);
                    toast2.show();
                }
                Profile user = new Profile(num_weight,genderReturn);

                Intent intent = new Intent();
                intent.putExtra(KEY,user);
                setResult(RESULT_OK,intent);
                finish();


            }
        });
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}