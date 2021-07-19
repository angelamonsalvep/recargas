package com.example.recargas.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recargas.R;

public class Recharges extends AppCompatActivity {
    Spinner spinner;
    Button btnConfirmRecharge;
    EditText etPhone;
    EditText etConfirmPhone;
    EditText etAmount;
    EditText etConfirmAmount;
    String phone, confirmPhone;
    String amount, confirmAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharges);

        spinner= (Spinner) findViewById(R.id.recharge_operator_spinner);
        btnConfirmRecharge= (Button)  findViewById(R.id.confirm_recharge_submit);
        etPhone= (EditText) findViewById(R.id.recharges_phone);
        etConfirmPhone= (EditText) findViewById(R.id.recharges_confirm_phone);
        etAmount= (EditText) findViewById(R.id.recharges_amount);
        etConfirmAmount= (EditText) findViewById(R.id.recharges_confirm_amount);

        phone= etPhone.getText().toString();
        confirmPhone= etConfirmPhone.getText().toString();



        String[] operators = {"CLARO","MOVISTAR","TIGO","WOM"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, operators));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    phone= etPhone.getText().toString();
                    if(phone.length()<4)
                    {
                        Toast.makeText(Recharges.this, R.string.phonemin, Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });


        etConfirmPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    phone= etPhone.getText().toString();
                    confirmPhone=etConfirmPhone.getText().toString();

                    if(compareStrings(phone, confirmPhone)==false)
                    {
                        Toast.makeText(Recharges.this, R.string.phonenotmatches, Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });

        etConfirmPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(Recharges.this, "Los numeros coinciden", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {



                if(s.length()>=10) {
                    phone= etPhone.getText().toString();
                    confirmPhone=s.toString();

                    if(compareStrings(phone, confirmPhone)==false)
                    {
                        Toast.makeText(Recharges.this, R.string.phonenotmatches, Toast.LENGTH_SHORT).show();

                    }


                }


            }
        });

        etAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {


                    amount= etAmount.getText().toString();
                    int amountInt;

                    if(amount.equals(""))
                    {
                        amountInt=0;
                    } else {
                        amountInt= Integer.parseInt(amount);
                    }

                    if(amountInt<1000)
                    {
                        Toast.makeText(Recharges.this, R.string.amountmin, Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        etConfirmAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>=4) {
                    amount= etAmount.getText().toString();
                    confirmAmount=s.toString();

                    if(compareStrings(amount, confirmAmount)==false)
                    {
                        Toast.makeText(Recharges.this, R.string.amountsnotmatches, Toast.LENGTH_SHORT).show();

                    }


                }

            }
        });

        etConfirmAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    amount= etAmount.getText().toString();
                    confirmAmount=etConfirmAmount.getText().toString();

                    if(compareStrings(amount, confirmAmount)==false)
                    {
                        Toast.makeText(Recharges.this, R.string.amountsnotmatches, Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });


        btnConfirmRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone=etPhone.getText().toString();
                confirmPhone= etConfirmPhone.getText().toString();
                amount= etAmount.getText().toString();
                confirmAmount=etConfirmAmount.getText().toString();
                int amountint;

                if(phone.isEmpty() || confirmPhone.isEmpty() || amount.isEmpty() || confirmAmount.isEmpty()) {
                    Toast.makeText(Recharges.this, R.string.login_register_empty, Toast.LENGTH_SHORT).show();

                } else {
                    amountint= Integer.parseInt(amount);

                    if(compareStrings(phone,confirmPhone)== true ) {
                        if(phone.length()==10) {
                            if(compareStrings(amount, confirmAmount) == true) {
                                if(amountint>=1000) {
                                    Toast.makeText(Recharges.this, R.string.rechargesuccefull, Toast.LENGTH_SHORT).show();
                                    goToHome();
                                    finish();
                                } else {
                                    Toast.makeText(Recharges.this, R.string.amountmin, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Recharges.this, R.string.amountsnotmatches, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Recharges.this, R.string.phonemin, Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Recharges.this, R.string.phonenotmatches, Toast.LENGTH_SHORT).show();

                    }
                }



            }
        });
    }

    @Override
    public void onBackPressed() {


    }

    private void goToHome() {
        Intent intent = new Intent(Recharges.this, MainMenuActivity.class);
        startActivity(intent);
    }


    private boolean compareStrings(String str1, String str2) {
        if (str1.equalsIgnoreCase(str2)) {
            return true;
        } else {
            return false;
        }
    }
}