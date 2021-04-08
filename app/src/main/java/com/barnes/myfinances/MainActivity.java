package com.barnes.myfinances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.barnes.myfinances.helper.DatabaseHelper;
import com.barnes.myfinances.model.CD;
import com.barnes.myfinances.model.Checking;
import com.barnes.myfinances.model.Loan;

public class MainActivity extends AppCompatActivity {
    public static DatabaseHelper db;

    Button btnSave,btnCancel,btnDeposit;
    EditText etAccountNumber,etCurrentBal,etInitalBal,etInterestRate,etPayment;
    RadioButton rdCD,rdLoan,rdChecking;
    String value = "";
    LinearLayout lAccNum,lCurrBal,lInitialBal,lintRate,lPayment;


    void initView() {
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        btnDeposit = findViewById(R.id.btnDeposit);
        etAccountNumber = findViewById(R.id.etAccNumber);
        etInitalBal = findViewById(R.id.etIntialBal);
        etCurrentBal = findViewById(R.id.etCurrentBal);
        etInterestRate = findViewById(R.id.etIntRate);
        etPayment = findViewById(R.id.etPayment);
        rdCD = findViewById(R.id.rdCD);
        rdLoan = findViewById(R.id.rdLoan);
        rdChecking = findViewById(R.id.rdChecking);
        lAccNum = findViewById(R.id.linearAccNum);
        lCurrBal = findViewById(R.id.linearCurrBal);
        lInitialBal = findViewById(R.id.linearIniBal);
        lintRate = findViewById(R.id.linearIntRate);
        lPayment = findViewById(R.id.linearPayment);

        rdCD.setChecked(true);
        lPayment.setVisibility(View.INVISIBLE);
        value = "rdCD";


    }

    void setEditTextBlank() {
        etInitalBal.setText("");
        etCurrentBal.setText("");
        etInterestRate.setText("");
        etPayment.setText("");
        etAccountNumber.setText("");
    }

    void showToast() {
        Toast.makeText(getApplicationContext(), "Operation Success", Toast.LENGTH_SHORT).show();
    }

    void showToastNoAccount() {
        Toast.makeText(getApplicationContext(), "Please enter the Account Number first in the Box above", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        initView();

        //Clear View
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditTextBlank();
            }
        });


        //CD Radio Button Clicked
        rdCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdCD.isChecked()) {
                    rdChecking.setChecked(false);
                    rdLoan.setChecked(false);
                    value = "rdCD";
                    lAccNum.setVisibility(View.VISIBLE);
                    lCurrBal.setVisibility(View.VISIBLE);
                    lInitialBal.setVisibility(View.VISIBLE);
                    lintRate.setVisibility(View.VISIBLE);
                    lPayment.setVisibility(View.INVISIBLE);
                    btnDeposit.setVisibility(View.INVISIBLE);
                }
            }
        });

        rdChecking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdChecking.isChecked()) {
                    rdCD.setChecked(false);
                    rdLoan.setChecked(false);
                    value = "rdChecking";
                    lAccNum.setVisibility(View.VISIBLE);
                    lCurrBal.setVisibility(View.VISIBLE);
                    lInitialBal.setVisibility(View.INVISIBLE);
                    lintRate.setVisibility(View.INVISIBLE);
                    lPayment.setVisibility(View.INVISIBLE);
                    btnDeposit.setVisibility(View.VISIBLE);
                }
            }
        });

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etAccountNumber.getText().toString().isEmpty()) {
                    Intent myIntent = new Intent(MainActivity.this, DepositActivity.class);
                    myIntent.putExtra("accNumber", Integer.parseInt(etAccountNumber.getText().toString())); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                } else {
                    showToastNoAccount();
                }

            }
        });

        rdLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdLoan.isChecked()) {
                    rdCD.setChecked(false);
                    rdChecking.setChecked(false);
                    value = "rdLoan";
                    lAccNum.setVisibility(View.VISIBLE);
                    lCurrBal.setVisibility(View.VISIBLE);
                    lInitialBal.setVisibility(View.VISIBLE);
                    lintRate.setVisibility(View.VISIBLE);
                    lPayment.setVisibility(View.VISIBLE);
                    btnDeposit.setVisibility(View.INVISIBLE);
                }
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(value.contains("rdCD")) {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            CD cd = new CD(Integer.parseInt(etAccountNumber.getText().toString()),
                                    Integer.parseInt(etInitalBal.getText().toString()),
                                    Integer.parseInt(etCurrentBal.getText().toString()),
                                    Integer.parseInt(etInterestRate.getText().toString()));
                            long createdId =  db.createCD(cd);
                        }
                    });
                    showToast();
                }
                else if(value.contains("rdLoan")) {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            Loan loan = new Loan(Integer.parseInt(etAccountNumber.getText().toString()),
                                    Integer.parseInt(etInitalBal.getText().toString()),
                                    Integer.parseInt(etCurrentBal.getText().toString()),
                                    Integer.parseInt(etInterestRate.getText().toString()),
                                    Integer.parseInt(etPayment.getText().toString()));
                            long createdId =   db.createLoan(loan);
                        }
                    });
                    showToast();
                }
                else if(value.contains("rdChecking")){
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            Checking checking = new Checking(Integer.parseInt(etAccountNumber.toString()),
                                    Integer.parseInt(etCurrentBal.toString())
                            );
                            long createdId =   db.createChecking(checking);
                        }
                    });
                    showToast();
                }

                setEditTextBlank();
            }


        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.closeDB();
    }
}