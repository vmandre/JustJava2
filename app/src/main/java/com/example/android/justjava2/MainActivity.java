package com.example.android.justjava2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int quantity;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity -= 1;
        displayQuantity(quantity);
    }

    private void displayQuantity(int number) {
        TextView numberText = (TextView) findViewById(R.id.quantity_number);
        numberText.setText(String.valueOf(number));
    }

    public void submitOrder(View view) {
        int price = calculatePrice();
        Log.v(this.getClass().getName(), "This price is " + price);

        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
    }

    private void displayMessage(String priceMessage) {
        TextView summary = (TextView) findViewById(R.id.summaryOrder);
        summary.setText(priceMessage);
    }

    private String createOrderSummary(int price) {
        CheckBox whippredCrem = (CheckBox) findViewById(R.id.whipped_crem);
        String summaryText = "";

        summaryText += "Name: Lyla the Labirinth \\nQuantity: " + quantity;
        summaryText += "\nTotal: " + price;
        summaryText += "\nAdd Whipped cream ? " + whippredCrem.isChecked();
        summaryText += "\nThank you!";

        return summaryText;
    }

    private int calculatePrice() {
        return quantity * 5;
    }
}
