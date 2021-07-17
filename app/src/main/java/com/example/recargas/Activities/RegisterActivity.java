package com.example.recargas.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recargas.R;
import com.example.recargas.models.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button registerSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // iniciacion de elementos de android

        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        registerSubmit = findViewById(R.id.register_submit);



        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data_email = email.getText().toString();
                String data_password = password.getText().toString();

                User user = new User();
                //añadir atributos a objeto user
                user.emailUser= data_email;
                user.passUser= data_password;

                ArrayList<User> listUsers =new ArrayList<User>();
                Bundle bundle = getIntent().getExtras();
                listUsers = bundle.getParcelableArrayList("listUsers");


                if (data_email.length() != 0 && data_password.length() != 0) {

                    if(data_password.length() >= 8 ) {
                        saveUser(listUsers, user);
                        Toast.makeText(RegisterActivity.this, R.string.registerSuccesfull, Toast.LENGTH_SHORT).show();
                        gotoLogin(listUsers);
                        finish();

                    }
                    else {
                        Toast.makeText(RegisterActivity.this, R.string.pass_min, Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(RegisterActivity.this, R.string.login_register_empty, Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

    @Override
    public void onBackPressed() {


    }

    private void  saveUser(ArrayList<User> listUsers, User user) {

        //añadir objeto a la lista
        listUsers.add(user);
    }

    private void gotoLogin(ArrayList<User> listUsers) {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listUsers", listUsers);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}