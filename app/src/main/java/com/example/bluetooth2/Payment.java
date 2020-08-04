package com.example.bluetooth2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Payment extends AppCompatActivity {

    Button btnNext, btnCancel;
    int pressedButtonNumber;
    double price;
    boolean payByQR = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        TextView tv1 = (TextView)findViewById(R.id.selectedProductName);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        pressedButtonNumber = getIntent().getExtras().getInt("buttonNumber");
        price = getIntent().getExtras().getInt("price");

        switch (pressedButtonNumber) {
            case 1:
                tv1.setText(getString(R.string.name_product1));
                break;
            case 2:
                tv1.setText(getString(R.string.name_product2));
                break;
            case 3:
                tv1.setText(getString(R.string.name_product4));
                break;
            case 4:
                tv1.setText(getString(R.string.name_product3));
                break;
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Payment.class);
                //There is no limit for number of Extras you want to pass to activity
                intent.putExtra("price", price);
                intent.putExtra("buttonNumber", pressedButtonNumber);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Quantity.class);
                //There is no limit for number of Extras you want to pass to activity
                intent.putExtra("price", price);
                intent.putExtra("buttonNumber", pressedButtonNumber);
                startActivity(intent);
            }
        });
    }


    public void goToMainFromPayment(View view) {
        System.err.println("!!!!!!!!!!!!GO TO MAIN");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.payByQR:
                if (checked)
                    payByQR = true;
                    break;
            case R.id.payByApp:
                if (checked)
                    payByQR = false;
                    break;
        }
    }
}