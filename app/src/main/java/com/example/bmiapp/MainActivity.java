package com.example.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {
    String Gender;
    int age,height,weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        MaterialCardView femaleCard = findViewById(R.id.femalecard);
        MaterialCardView maleCard = findViewById(R.id.malecard);
        TextView heightvalue=findViewById(R.id.heightValue);
        Slider heightslider=findViewById(R.id.heightSlider);
        TextView weightvalue=findViewById(R.id.weightValue);
        AppCompatButton weightminus=findViewById(R.id.btnWeightMinus);
        AppCompatButton weightplus=findViewById(R.id.btnWeightPlus);
        TextView agevalue=findViewById(R.id.ageValue);
        AppCompatButton ageminus=findViewById(R.id.btnAgeMinus);
        AppCompatButton ageplus=findViewById(R.id.btnAgePlus);
        Toolbar toolbar =findViewById(R.id.toolbar);
        AppCompatButton calculabtn=findViewById(R.id.calculateBtn);
        setSupportActionBar(toolbar);

        age=Integer.parseInt(agevalue.getText().toString());
        agevalue.setText(String.valueOf(age));
        weight= Integer.parseInt( weightvalue.getText().toString());
        height=(int) heightslider.getValue();
        heightvalue.setText(height+"Cm");
        femaleCard.setOnClickListener(v -> {
//            Toast.makeText(MainActivity.this, "Female clicked", Toast.LENGTH_SHORT).show();
            Gender="Female";
            femaleCard.setStrokeColor(ContextCompat.getColor(this, R.color.selected_stroke));
            femaleCard.setStrokeWidth(5);
            maleCard.setStrokeColor(ContextCompat.getColor(this, R.color.transparent));
        });
        maleCard.setOnClickListener(v -> {
//            Toast.makeText(MainActivity.this, "male clicked", Toast.LENGTH_SHORT).show();
            Gender="Male";
            maleCard.setStrokeColor(ContextCompat.getColor(MainActivity.this, R.color.selected_stroke));
            maleCard.setStrokeWidth(5);
            femaleCard.setStrokeColor(ContextCompat.getColor(MainActivity.this, R.color.transparent));
        });
        heightslider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float v, boolean b) {
                height=Math.round(v);
                heightvalue.setText(height+"Cm");
            }
        });

        weightminus.setOnClickListener(view -> {
            if(weight>0) {
                weight -= 1;
                weightvalue.setText(String.valueOf(weight));
            }
            else {
                Toast.makeText(this, "Negative Weight is not allowed", Toast.LENGTH_SHORT).show();
            }
        });
        weightplus.setOnClickListener(view -> {
            weight+=1;
            weightvalue.setText(String.valueOf(weight));
        });

        ageminus.setOnClickListener(view -> {
            if(age>0) {
                age -= 1;
                agevalue.setText(String.valueOf(age));
            }
            else {
                Toast.makeText(this, "Negative Age is not allowed", Toast.LENGTH_SHORT).show();
            }
        });
        ageplus.setOnClickListener(view -> {
            age+=1;
            agevalue.setText(String.valueOf(age));
        });

        calculabtn.setOnClickListener(view -> {
            float heightm =height/100f;
            float bmi =weight/(heightm*heightm);

            Intent intent=new Intent(MainActivity.this,resultPage.class);
            intent.putExtra("bmi",bmi);
            intent.putExtra("gender",Gender);
            intent.putExtra("age",age);
            startActivity(intent);
        });


    }
}