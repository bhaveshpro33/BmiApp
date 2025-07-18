package com.example.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class resultPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_page);
        Intent intent=getIntent();
        TextView bmiValueText=findViewById(R.id.bmiValueText);
        CircularProgressBar circularProgressBar=findViewById(R.id.circularProgressBar);
        TextView bmiCategory=findViewById(R.id.bmiCategory);
        TextView summary =findViewById(R.id.summary);
        TextView advice =findViewById(R.id.advice);
        AppCompatButton closebtn=findViewById(R.id.closebtn);
        float bmi=intent.getFloatExtra("bmi",0);
        String gender=intent.getStringExtra("gender");
        int age =intent.getIntExtra("age",19);
        String resultline,adviceline,category;

        bmiValueText.setText(String.format("%.1f",bmi));
        circularProgressBar.setProgress(bmi);

        if (bmi < 18.5f) {
            category = "Underweight";
            resultline = "Your BMI is " + String.format("%.1f",bmi) + ", indicating you are underweight for your height.";
            adviceline = "You should consider a nutritious diet to reach a healthy weight.";
        } else if (bmi >= 18.5f && bmi <= 24.9f) {
            category = "Normal";
            resultline = "Your BMI is " + String.format("%.1f",bmi) + ", indicating your weight is in the normal range for your height.";
            adviceline = "Maintain your healthy habits with balanced diet and exercise.";
        } else if (bmi >= 25.0f && bmi <= 29.9f) {
            category = "Overweight";
            resultline = "Your BMI is " + String.format("%.1f",bmi) + ", indicating you are overweight for your height.";
            adviceline = "Regular exercise and a calorie-conscious diet can help reduce your BMI.";
        } else {
            category = "Obese";
            resultline = "Your BMI is " + String.format("%.1f",bmi) + ", indicating obesity for your height.";
            adviceline = "It's recommended to consult a doctor and adopt a structured weight-loss plan.";
        }
        bmiCategory.setText(category);
        summary.setText(resultline);
        advice.setText(adviceline);
        closebtn.setOnClickListener(view -> {
            Intent intent1=new Intent(resultPage.this,MainActivity.class);
            startActivity(intent1);
        });

    }
}
