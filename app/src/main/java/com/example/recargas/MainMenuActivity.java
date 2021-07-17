package com.example.recargas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recargas.Activities.MainActivity;

public class MainMenuActivity extends AppCompatActivity {

    Button btnRecharges;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // iniciacion de elementos de android

        btnRecharges = findViewById(R.id.recharge_submit);
        btnExit = findViewById(R.id.exit_submit);

        btnRecharges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToRecharges();
                finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {


    }

    private void goToRecharges() {
        Intent intent = new Intent(MainMenuActivity.this, Recharges.class);
        startActivity(intent);
    }


}