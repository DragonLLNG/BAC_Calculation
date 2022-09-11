package com.example.baccalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class AddDrink extends AppCompatActivity {
    RadioGroup drinkSize;
    SeekBar alcohol;
    double alcoholPercent;
    TextView percentStr;
    final static public String DRINKS_KEY = "DRINKS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);
        setTitle("Add Drink");

        findViewById(R.id.cancel2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDrink.this, MainActivity.class);
                startActivity(intent);
            }
        });
        alcohol = findViewById(R.id.alcohol_seekbar);
        alcohol.setMax(30);
        percentStr = findViewById(R.id.percent_txt);
        alcohol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentStr.setText(i+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        drinkSize = findViewById(R.id.size_group);
        findViewById(R.id.addDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("You pressed Add Drink");

                //add drink goes here
                if (drinkSize.getCheckedRadioButtonId() == -1) {
                    Toast toast2 = Toast.makeText(AddDrink.this, "Please select a valid drink size", Toast.LENGTH_SHORT);
                    toast2.setGravity(Gravity.CENTER, 0,0);
                    toast2.show();
                }
                alcoholPercent = (double) alcohol.getProgress();
                System.out.println(alcoholPercent/100);

                int numDrink = drinkSize.getCheckedRadioButtonId();
                int ounces = 0;
                if(numDrink == R.id.one_oz){
                    ounces = 1;
                }
                else if(numDrink == R.id.five_oz){
                    ounces = 5;
                }
                else{
                    ounces = 12;
                }
                double decimal = alcoholPercent/100;
                Drinks newDrink = new Drinks(decimal, ounces);

                Intent intent = new Intent(AddDrink.this, MainActivity.class);
                intent.putExtra(DRINKS_KEY, newDrink);
                setResult(RESULT_OK,intent);
                startActivity(intent);
            }
        });

    }

}