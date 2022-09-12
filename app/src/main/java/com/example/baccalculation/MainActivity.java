package com.example.baccalculation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView input_recieve, bacOut, status, numDrinks, usrWeight;
    static Profile user = new Profile();
    ArrayList <Drinks> drinksList = new ArrayList<Drinks>();
    final static public String DRINKLIST_KEY = "DRINKS_LIST";

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
                intent.putParcelableArrayListExtra(DRINKLIST_KEY, drinksList);
                startActivity(intent);
            }
        });
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usrWeight = findViewById(R.id.initial_weight);
                numDrinks = findViewById(R.id.num_drink);
                bacOut = findViewById(R.id.bac_level);

                try {
                    status.setBackgroundResource(R.drawable.roundedcorner);
                    GradientDrawable drawable = (GradientDrawable) status.getBackground();

                    drinksList.clear();
                    bacOut.setText("0.000");
                    usrWeight.setText("N/A");
                    numDrinks.setText("0");
                    status.setText("You're safe");
                    drawable.setColor(Color.GREEN);
                    user = new Profile();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView userWeight = findViewById(R.id.initial_weight);
        TextView numDrinks = findViewById(R.id.num_drink);



        //Parsing in the Drinks and user
        if((getIntent() != null) && (getIntent().getExtras() != null) && (getIntent().hasExtra(SetWeightGender.USER_KEY))){
                System.out.println("You parsed a user");
                Profile parsedUser = getIntent().getParcelableExtra(SetWeightGender.USER_KEY);
                user = parsedUser;
                String weight = Double.toString(parsedUser.getWeight());
                userWeight.setText(weight);
        }
        if((getIntent() != null) && (getIntent().getExtras() != null) && (getIntent().hasExtra(AddDrink.DRINKS_KEY))){
            System.out.println("You added a drink");
            Drinks newDrink;
            newDrink = getIntent().getParcelableExtra(AddDrink.DRINKS_KEY);
            drinksList.add(newDrink);
            String drinkTally = Integer.toString(drinksList.size());
            numDrinks.setText(drinkTally);
        }

        try {
            if(user.getWeight() != 0.0) {
                userWeight.setText(Double.toString(user.getWeight()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(drinksList.size() != 0) {
                numDrinks.setText(Integer.toString(drinksList.size()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Recalling the Bac Calculation to update the status
        if(user.getWeight() != 0.0) {
            bacCalculation();
        }
    }
    public void bacCalculation(){
        double bac = 0.0;
        double consumed = 0.0;

        for(int i = 0; i < drinksList.size(); i++){
            consumed += drinksList.get(i).getAlcPercent();
        }
        if(user.getGender() == "male") {
            bac = consumed * 5.14 / user.getWeight() * 0.73;
            System.out.println(bac);
        }
        else{
            bac = consumed * 5.14 / user.getWeight() * 0.66;
        }

        //Outputting the bac
        bacOut = findViewById(R.id.bac_level);
        String bacString = Double.toString(bac);
        bacOut.setText(String.format("%.3f",bac));

        //Changing the color of the status bar
        status = findViewById(R.id.status);
        status.setBackgroundResource(R.drawable.roundedcorner);
        GradientDrawable drawable = (GradientDrawable) status.getBackground();
        if(bac >= 0.25){
            //disable add drink
            status.setText("Over the limit");
            drawable.setColor(Color.RED);

            Toast overLimit = Toast.makeText(MainActivity.this, "No more drinks for you!", Toast.LENGTH_LONG);
            overLimit.setGravity(Gravity.CENTER, 0,0);
            overLimit.show();
        }
        else if(bac > 0.2){
            status.setText("Over the limit");
            drawable.setColor(Color.RED);
        }
        else if(bac > 0.08){
            status.setText("Be Careful!");
            drawable.setColor(Color.YELLOW);
        }
        else{
            status.setText("You're Safe");
            drawable.setColor(Color.GREEN);
        }
    }

}