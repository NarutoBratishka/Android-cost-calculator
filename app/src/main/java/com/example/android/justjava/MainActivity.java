package com.example.android.justjava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
//import com.example.android.justjava.R;




public class MainActivity extends AppCompatActivity {
    int coffeeCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        if (coffeeCount == 0) {
            displayPrice(0);
        } else {
//            String priceMessage = "Total: " + NumberFormat.getCurrencyInstance().format(coffeeCount * 75) + "\nThank you!";
            String priceMessage = createOrderSummary(coffeeCount);
            displayMessage(priceMessage);
        }
    }

    private String createOrderSummary(int quantity) {
        String name = "Visitor";
        int oneCupCost = 25;

        return "Name: " + name + " \n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + quantity * oneCupCost + "$\n" +
                "Thank you!";
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Just for 0 quantity
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        priceTextView.setText("$" + number);
    }

    private void displayMessage(String message) {
        TextView order = (TextView) findViewById(R.id.price_text_view);
        order.setText(message);
    }

    public void increment(View view) {
        coffeeCount++;
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + coffeeCount);
    }

    public void decrement(View view) {
        if (coffeeCount != 0) {
            coffeeCount--;
        }
        displayQuantity(coffeeCount);
    }
}