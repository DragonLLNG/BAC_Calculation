package com.example.baccalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewDrinks extends AppCompatActivity {

    ArrayList <Drinks> drinkList = new ArrayList<Drinks>();
    TextView label, size, percent, dateAdded;
    int iterator = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drinks);
        setTitle("View Drinks");



        label = findViewById(R.id.num_drinks_view);
        size = findViewById(R.id.drink_size_view);
        percent = findViewById(R.id.percent_view);

        if(getIntent() != null  && getIntent().getParcelableArrayListExtra(MainActivity.DRINKLIST_KEY)!=null){
            drinkList = getIntent().getParcelableArrayListExtra(MainActivity.DRINKLIST_KEY);
            System.out.println("You passed in a array list");
        }

        if(drinkList.size() != 0){

            //Label
            String sizeStr = Integer.toString(drinkList.size());
            String itrString = Integer.toString(iterator+1);
            label.setText("Drink " + itrString + " out of " + sizeStr);

            //Ounces
            String drinkOz = Integer.toString(drinkList.get(iterator).getSize());
            size.setText(drinkOz + " oz");

            //Percent
            String percentStr = Double.toString(drinkList.get(iterator).getAlcPercent()*100);
            percent.setText(percentStr + "% Alcohol");
        }


        findViewById(R.id.close_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewDrinks.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iterator++;
                System.out.println("you hit next");
            }
        });
        findViewById(R.id.previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iterator--;
                System.out.println("you hit prev");
            }
        });
        findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("you hit del");
            }
        });

    }

}