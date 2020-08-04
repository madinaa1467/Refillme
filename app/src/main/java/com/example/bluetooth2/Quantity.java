package com.example.bluetooth2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class Quantity extends AppCompatActivity {

    Button btnNext;
    int pressedButtonNumber, count = 0, step = 100;
    double price = 0.0, priceFor100ml, max_count = 10000.0, priceBottle;
    TextView tv_count, tv_price, tv_max_count, tv_price_for_count, tv_selected_product_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);

        tv_selected_product_name = (TextView) findViewById(R.id.selectedProductName);
        tv_count = (TextView) findViewById(R.id.integer_number);
        tv_price = (TextView) findViewById(R.id.textViewPriceForRefilling);
//        tv_max_count = (TextView) findViewById(R.id.textViewAvailableQuantity);
        tv_price_for_count = (TextView) findViewById(R.id.textViewPriceToPay);
        btnNext = (Button) findViewById(R.id.btnNext);

        pressedButtonNumber = getIntent().getExtras().getInt("buttonNumber");
        price = getIntent().getExtras().getDouble("price");

        if(price == 0.0) {
            count = (int) ((price * 100) / priceFor100ml);
            tv_count.setText("" + count);
            tv_price_for_count.setText(price + "  тг");
        }

        priceBottle = Double.parseDouble(getString(R.string.price_bottle));

        switch (pressedButtonNumber) {
            case 1:
                priceFor100ml = Double.parseDouble(getString(R.string.price_product1_100ml));

                tv_selected_product_name.setText(getString(R.string.name_product1));
                tv_price.setText(getString(R.string.price_product1_100ml) + "  тг");
                break;
            case 2:
                priceFor100ml = Double.parseDouble(getString(R.string.price_product1_100ml));

                tv_selected_product_name.setText(getString(R.string.name_product2));
                tv_price.setText(getString(R.string.price_product2_100ml) + "  тг");
                break;
            case 3:
                priceFor100ml = Double.parseDouble(getString(R.string.price_product1_100ml));

                tv_selected_product_name.setText(getString(R.string.name_product3));
                tv_price.setText(getString(R.string.price_product3_100ml) + "  тг");
                break;
            case 4:
                priceFor100ml = Double.parseDouble(getString(R.string.price_product1_100ml));

                tv_selected_product_name.setText(getString(R.string.name_product4));
                tv_price.setText(getString(R.string.price_product4_100ml) + "  тг");
                break;
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Payment.class);
                //There is no limit for number of Extras you want to pass to activity
                intent.putExtra("buttonNumber", pressedButtonNumber);
                intent.putExtra("price", price);
                startActivity(intent);
            }
        });
    }

    public void goToMainFromQuantity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        System.out.println("onRadioButtonClicked: " + view.getId());

        switch (view.getId()) {
            case R.id.radioButtonForward10ml:
                if (checked)
                    step = 10;
                break;
            case R.id.radioButtonForward50ml:
                if (checked)
                    step = 50;
                break;
            case R.id.radioButtonForward100ml:
                if (checked)
                    step = 100;
                break;
        }
    }

    public void onClick(View v) {
//        int textview = Integer.parseInt(String.valueOf(findViewById(R.id.integer_number)));
        System.out.println("!!! onClick: " + count + " ;  price: " + price);
        System.out.println("!!!! v.getId(): " + v.getId() + " ;  R.id.increase: " + R.id.increase + " ;  R.id.decrease: " + R.id.decrease);

        switch (v.getId()) {
            case R.id.increase:
                System.out.println("0 max_count <= Double.valueOf(count + step): " + (max_count <= Double.valueOf(count + step)));
                System.out.println(" 000  max_count: " + max_count + " ;  count: " + count + " ;  step: " + step);
                System.out.println(" 000  count + step: " + (count + step) + " ;  max_count: " + max_count);

                if(max_count >= Double.valueOf(count + step)) {
                    count += step;
                    price = count * (priceFor100ml / 100.0);

                    System.out.println("1 Increase count: " + count + " ;  price: " + price);

                    tv_count.setText("" + count);
                    tv_price_for_count.setText(price + "  тг");

                    System.out.println("2 Increase count: " + count + " ;  price: " + price);

                }
                break;
            case R.id.decrease:
                System.out.println("1 Decrease count: " + count + " ;  price: " + price);

                if(0 <= count - step) {
                    count -= step;
                    price = count * (priceFor100ml / 100.0);

                    System.out.println("1 Decrease count: " + count + " ;  price: " + price);

                    tv_count.setText("" + count);
                    tv_price_for_count.setText(price + "  тг");

                    System.out.println("2 Decrease count: " + count + " ;  price: " + price);
                }
                break;
            default:
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBoxBottle:
                if (checked) {
                    price += priceBottle;
                    tv_price_for_count.setText(price + "  тг");
                }
                else {
                    price += priceBottle;
                    tv_price_for_count.setText(price + "  тг");
                }
                break;
        }
    }
}