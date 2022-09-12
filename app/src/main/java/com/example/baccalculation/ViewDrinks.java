package com.example.baccalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ViewDrinks extends AppCompatActivity {

    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    TextView dateTime;

    ArrayList <Drinks> drinkList = new ArrayList<Drinks>();
    TextView label, size, percent, dateAdded;
    int iterator = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drinks);
        setTitle("View Drinks");

        dateTime = findViewById(R.id.date);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyy HH:mm");
        date = dateFormat.format(calendar.getTime());
        dateTime.setText(date);


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
                iterator++;
                if( iterator > drinkList.size() -1){
                    iterator = drinkList.size() - 1;
                }
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
                System.out.println("you hit prev");
            }
        });
        findViewById(R.id.previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iterator--;
                if( iterator < 0){
                    iterator = drinkList.size() - 1;
                }
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
                System.out.println("you hit prev");
            }
        });
        findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drinkList.remove(iterator);
                iterator -= 1;
                if(drinkList.size() != 0){
                    if(iterator < 0){
                        iterator = 0;
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
                        System.out.println("you hit prev");
                    }

                }
                else{
                    label.setText("Drink # out of N");
                    size.setText("1 oz");
                    percent.setText("x% Alcohol");
                }
            }
        });

    }

}