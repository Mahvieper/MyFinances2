package com.barnes.myfinances;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.barnes.myfinances.helper.DatabaseHelper;
import com.barnes.myfinances.model.Deposit;
import com.barnes.myfinances.model.Loan;

import java.io.ByteArrayOutputStream;

import static com.barnes.myfinances.MainActivity.db;

public class DepositActivity extends AppCompatActivity {
    ImageView imgBtnFront,imgBtnBack;
    Button btnSave,btnCancel;
    EditText etAmount;
    TextView txtAccountNumber;
    ImageView click_image_id;
    Bitmap imageFront,imgBack;
    byte imgFrontByteArray[],imgBackByteArray[];


    private static final int pic_id = 123;
    private static final int pic_id_1 = 1234;


    void showToastNoAmount() {
        Toast.makeText(getApplicationContext(), "Please enter the Amount before saving also check if Image is Captured", Toast.LENGTH_SHORT).show();
    }

    void setEditTextBlank() {
        etAmount.setText("");
    }
    void showToast() {
        Toast.makeText(getApplicationContext(), "Operation Success", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deposit);

        Intent intent = getIntent();
        final int accNumber = intent.getIntExtra("accNumber",0);

        txtAccountNumber = findViewById(R.id.txtAccountNumber);
        imgBtnFront = findViewById(R.id.imgBtnFront);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        etAmount = findViewById(R.id.etAmount);

        txtAccountNumber.setText("Account Number : " + String.valueOf(accNumber));

        imgBtnFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, pic_id);
            }
        });

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, pic_id_1);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditTextBlank();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etAmount.getText().toString().isEmpty() && imgFrontByteArray != null && imgBackByteArray != null) {
                    Deposit loan = new Deposit(Integer.parseInt(String.valueOf(accNumber)),
                            Integer.parseInt(etAmount.getText().toString()),
                            imgFrontByteArray,
                            imgBackByteArray);
                    long createdId =   db.createDeposit(loan);
                    if(createdId > -1)
                        showToast();
                } else {
                    showToastNoAmount();
                }
            }
        });

    }

    // This method will help to retrieve the image
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        // Match the request 'pic id with requestCode
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
            imageFront = (Bitmap) data.getExtras()
                    .get("data");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            imageFront.compress(Bitmap.CompressFormat.PNG,100,bos);

            imgFrontByteArray = bos.toByteArray();


            // Set the image in imageview for display
            imgBtnFront.setImageBitmap(imageFront);
        } else if(requestCode == pic_id_1) {
            imgBack = (Bitmap) data.getExtras()
                    .get("data");

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            imageFront.compress(Bitmap.CompressFormat.PNG,100,bos);

            imgBackByteArray = bos.toByteArray();

            // Set the image in imageview for display
            imgBtnBack.setImageBitmap(imgBack);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.closeDB();
    }

}
