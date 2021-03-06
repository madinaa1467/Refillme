package com.example.bluetooth2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bluetooth2.dao.Order;
import com.example.bluetooth2.rest.CallAPI;

import androidx.appcompat.app.AppCompatActivity;

public class Qrcode extends AppCompatActivity {

    Order order;
    Button btnNext, btnCancel, buttonPaid;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);


        order = (Order) getIntent().getSerializableExtra("order");


//        btnNext = (Button) findViewById(R.id.btnNext);
        buttonPaid = (Button) findViewById(R.id.buttonPaid);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        imageView = (ImageView) findViewById(R.id.qrImage);



//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), Paid.class);
//                intent.putExtra("order", order);
//                startActivity(intent);
//            }
//        });

        buttonPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("senim".equals(order.getPaidByCompany())) {
                    CallAPI callAPI = new CallAPI(order);
                    callAPI.callSenimStatusAPI(Qrcode.this);
                } else if("rahmet".equals(order.getPaidByCompany())) {
                    CallAPI callAPI = new CallAPI(order);
                    callAPI.callRahmetStatusAPI(Qrcode.this);
                } else {
//                    TODO
                }

//                Intent intent = new Intent(v.getContext(), Paid.class);
//                intent.putExtra("order", order);
//                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Payment.class);
                intent.putExtra("order", order);
                startActivity(intent);
            }
        });

        apiCall();
    }

    public void goToMainFromPayment(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void apiCall(){
        CallAPI callAPI = new CallAPI(order);
        callAPI.callCreateAPI(Qrcode.this, imageView);
    }
}