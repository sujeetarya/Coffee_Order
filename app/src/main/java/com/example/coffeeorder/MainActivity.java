package com.example.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private boolean hasWhippedCream() {
        CheckBox whippedCream = findViewById(R.id.whipped_cb);
        return whippedCream.isChecked();
    }

    private boolean hasChocolate() {
        CheckBox chocolate = findViewById(R.id.chocolate_cb);
        return chocolate.isChecked();
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "Can not order less then 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "Can not order more then 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    private int calculatePrice() {
        price = quantity * 50;
        if (hasWhippedCream()) {
            price += 20 * quantity;// 20 extra for each whipped cream toppings
        }
        if (hasChocolate()) {
            price += 30 * quantity;// 30 extra for each chocolate toppings
        }
        return price;
    }

    private void displayQuantity(int quantity) {
        TextView quantity_txt = findViewById(R.id.quantity_tv);
        quantity_txt.setText("" + quantity);
    }

    private void displayPrice() {
        TextView price_txt = findViewById(R.id.price_tv);
        price_txt.setText("₹" + price);
    }

    public void order(View view) {
        calculatePrice();
        displayPrice();
        displayOrderSummery(orderSummery());
    }

    private String orderSummery() {
        String summery;
        EditText name_edt = findViewById(R.id.name_edt);
        String name = name_edt.getText().toString();

        summery = "Name :" + name;
        summery += "\nAdd Whipped Cream ? " + hasWhippedCream();
        summery += "\nAdd Chocolate ? " + hasChocolate();
        summery += "\nQuantity: " + quantity;
        summery += "\nTotal Price - ₹" + price;
        summery += "\nThank You";
        return summery;
    }

    private void displayOrderSummery(String orderSummery) {
        TextView summery_tv = findViewById(R.id.summery_tv);
        summery_tv.setText(orderSummery);
    }
}