package com.example.android.justjava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
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
        display(coffeeCount);
        displayPrice(coffeeCount * 5);
    }
    /**

     This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        priceTextView.setText("$" + number);
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
        display(coffeeCount);
    }
}