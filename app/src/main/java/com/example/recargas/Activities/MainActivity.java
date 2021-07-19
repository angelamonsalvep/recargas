package com.example.recargas.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recargas.R;
import com.example.recargas.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button submit;
    TextView registrationLink;
    int poss;
    ArrayList<User> listUsers = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // iniciacion de elementos de android


        etEmail = findViewById(R.id.login_email);
        etPassword = findViewById(R.id.login_password);
        submit = findViewById(R.id.login_submit);
        registrationLink = findViewById(R.id.login_register_link);


        try {
            Bundle bundle = getIntent().getExtras();
            listUsers = bundle.getParcelableArrayList("listUsers");
        } catch (Exception e) {

        }


        registrationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister(listUsers);


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listUsers.size()>0) {


                    String data_email = etEmail.getText().toString();
                    String data_password = etPassword.getText().toString();
                    User user = new User();
                    //añadir atributos a objeto user
                    user.emailUser= data_email;
                    user.passUser= data_password;

                    if (data_email.length() != 0 && data_password.length() != 0) {

                        int countbeforeatsign=0;
                        int countatsign=0;
                        int countafteratsign=0;
                        int countdot=0;


                            int n=0;
                            while(n<data_email.length()-2) {

                                if(countatsign==1) {
                                    countafteratsign++;
                                }

                                if(data_email.charAt(n)== ' ') {
                                    Toast.makeText(MainActivity.this, R.string.messageEmailInvalid, Toast.LENGTH_SHORT).show();
                                    break;
                                } else {

                                    if(data_email.charAt(n)!= '@') {
                                        countbeforeatsign++;
                                        if(data_email.charAt(n)=='.') {
                                            countdot++;
                                            if(countatsign==1) {
                                                if(countafteratsign<5) {
                                                    Toast.makeText(MainActivity.this, R.string.messageEmailInvalid, Toast.LENGTH_SHORT).show();
                                                    break;
                                                } else {
                                                    if(n == data_email.length()-4) {
                                                        if(data_email.charAt(n+1) == 'c' && data_email.charAt(n+2) == 'o'  && data_email.charAt(n+3) == 'm') {

                                                            if(data_password.length() >= 8 ) {
                                                                if(searchEmail(listUsers,user)==true) {
                                                                    if(compareStrings(listUsers.get(poss).passUser, data_password)==true) {
                                                                        Toast.makeText(MainActivity.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                                                                        goToHome();



                                                                    } else {
                                                                        Toast.makeText(MainActivity.this, R.string.message_pass_incorrect, Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(MainActivity.this, R.string.usernotexist, Toast.LENGTH_SHORT).show();

                                                                }

                                                            }
                                                            else {
                                                                Toast.makeText(MainActivity.this, R.string.pass_min, Toast.LENGTH_SHORT).show();
                                                            }
                                                            break;

                                                        } else {
                                                            Toast.makeText(MainActivity.this, R.string.messageEmailInvalid, Toast.LENGTH_SHORT).show();
                                                            break;
                                                        }
                                                    } else {
                                                            Toast.makeText(MainActivity.this, R.string.messageEmailInvalid, Toast.LENGTH_SHORT).show();
                                                        break;
                                                    }
                                                }

                                            }

                                        }

                                    } else {
                                        if(countbeforeatsign<6) {
                                            Toast.makeText(MainActivity.this, R.string.messageEmailInvalid, Toast.LENGTH_SHORT).show();
                                            break;
                                        } else {
                                            countatsign++;
                                        }
                                    }

                                }

                                n++;


                            }

                            if(countatsign>1 || countatsign==0) {
                                Toast.makeText(MainActivity.this, R.string.messageEmailInvalid, Toast.LENGTH_SHORT).show();

                            }
                            if(countdot==0 || countdot>1) {
                                Toast.makeText(MainActivity.this, R.string.messageEmailInvalid, Toast.LENGTH_SHORT).show();

                            }



                    }
                    else {
                        Toast.makeText(MainActivity.this, R.string.login_register_empty, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, R.string.message_no_users, Toast.LENGTH_SHORT).show();
                }


            }
        });




    }


    @Override
    protected void onResume() {
        super.onResume();

        etEmail.setText("");
        etPassword.setText("");
        etEmail.requestFocus();


    }

    @Override
    public void onBackPressed() {


    }

    private void goToRegister(ArrayList<User> listUsers) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listUsers", listUsers);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goToHome() {
        Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    private boolean searchEmail(ArrayList<User> listUsers, User user) {
        int i=0;
        boolean result;
        int countFound=0;
        while(i<listUsers.size()) {
            result= compareStrings(listUsers.get(i).emailUser,user.emailUser);
            if(result==true)
            {
                poss=i;
                countFound++;
                break;
            } else {
                i++;
            }
        }

        if(countFound==0){
            Toast.makeText(MainActivity.this, R.string.message_email_no_exist, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean compareStrings(String str1, String str2) {
        if (str1.equalsIgnoreCase(str2)) {
            return true;
        } else {
            return false;
        }
    }


}