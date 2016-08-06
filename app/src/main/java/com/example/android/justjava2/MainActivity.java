package com.example.android.justjava2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

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
//        displayMessage(priceMessage);

        //Abre um mapa com a localização informada
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6, -122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

        composeEmail(new String[]{"vmandre@gmail.com"}, "Teste de email via App", priceMessage);

    }

    private void composeEmail(String[] addresses, String subject, String text) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL, addresses);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, text);

        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }

    }

    private void displayMessage(String priceMessage) {
        TextView summary = (TextView) findViewById(R.id.summaryOrder);
        summary.setText(priceMessage);
        summary.setVisibility(View.VISIBLE);
    }

    private String createOrderSummary(int price) {
        CheckBox whippredCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        TextView name = (TextView) findViewById(R.id.name);
        String summaryText = "";

        summaryText += ": " + name.getText(); //getString(R.string.hint, name.getText()); //  + ": " + name.getText();
        summaryText += "\n" + getString(R.string.quantity) + ": " + quantity;
        summaryText += "\n" + getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance().format(price);
        summaryText += "\n" + getString(R.string.add_whipped_cream) + " " + whippredCream.isChecked();
        summaryText += "\n" + getString(R.string.add_chocolate) + " " + chocolate.isChecked();
        summaryText += "\n" + getString(R.string.thank_you);

        return summaryText;
    }

    private int calculatePrice() {
        return quantity * 5;
    }
}
