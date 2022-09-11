package com.example.baccalculation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView input_receive;


    ActivityResultLauncher<Intent> weight_gender_back = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){

                Profile user = result.getData().getParcelableExtra(SetWeightGender.KEY);

                if(result.getData() != null && Objects.nonNull(user)){
                    setContentView(R.layout.activity_main);

                    Double weight = user.getWeight();
                    String gender = user.getGender();

                    input_receive = findViewById(R.id.initial_weight);
                    input_receive.setText(weight+"lbs"+"("+gender+")");

                }

            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BAC Calculator");


        findViewById(R.id.set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetWeightGender.class);
                weight_gender_back.launch(intent);
            }
        });

    }

}