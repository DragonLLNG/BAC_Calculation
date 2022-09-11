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

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView input_recieve;
    Profile user = new Profile();
    ArrayList <Drinks> drinksList = new ArrayList<Drinks>();

/*
    ActivityResultLauncher<Intent> weight_gender_back = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                System.out.println("You in result");
                user = result.getData().getParcelableExtra(SetWeightGender.USER_KEY);

                if (result.getData() != null && Objects.nonNull(user)) {
                    setContentView(R.layout.activity_main);

                    Double weight = user.getWeight();
                    String gender = user.getGender();

                    input_recieve = findViewById(R.id.initial_weight);
                    input_recieve.setText(weight + "lbs" + "(" + gender + ")");

                }

            }
        }
    });
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BAC Calculator");


        findViewById(R.id.set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetWeightGender.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.add_drink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddDrink.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.view_drink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewDrinks.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView userWeight = findViewById(R.id.initial_weight);
        TextView numDrinks = findViewById(R.id.num_drink);

        if(getIntent() != null && getIntent().getExtras() != null){
            if(getIntent().hasExtra(SetWeightGender.USER_KEY)){
                System.out.println("You parsed a user");
                Profile parsedUser = getIntent().getParcelableExtra(SetWeightGender.USER_KEY);
                String weight = Double.toString(parsedUser.getWeight());
                userWeight.setText(weight);
            }
            else if(getIntent().hasExtra(AddDrink.DRINKS_KEY)){
                System.out.println("You added a drink");
                Drinks newDrink;
                newDrink = getIntent().getParcelableExtra(AddDrink.DRINKS_KEY);
                drinksList.add(newDrink);
                numDrinks.setText(drinksList.size());
            }
        }
        /*
        if((getIntent() != null) && (getIntent().getExtras() != null) && (getIntent().hasExtra(SetWeightGender.USER_KEY))){
                System.out.println("You parsed a user");
                Profile parsedUser = getIntent().getParcelableExtra(SetWeightGender.USER_KEY);
                String weight = Double.toString(parsedUser.getWeight());
                userWeight.setText(weight);
        }
        if((getIntent() != null) && (getIntent().getExtras() != null) && (getIntent().hasExtra(AddDrink.DRINKS_KEY))){
            System.out.println("You added a drink");
            Drinks newDrink;
            newDrink = getIntent().getParcelableExtra(AddDrink.DRINKS_KEY);
            drinksList.add(newDrink);
            numDrinks.setText(drinksList.size());
        }
        */
    }

}