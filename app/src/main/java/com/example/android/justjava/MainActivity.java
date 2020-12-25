package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
//import com.example.android.justjava.R;




public class MainActivity extends AppCompatActivity {
    int coffeeCount = 2;
    CheckBox hasWhippedCream;
    CheckBox hasChocolate;
    EditText nameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hasWhippedCream = findViewById(R.id.whipped_cream_id);
        hasChocolate = findViewById(R.id.chocolate_id);
        nameId = findViewById(R.id.name_id);

    }

    /**
     This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
/*
        if (coffeeCount == 0) {
            displayPrice(0);
        } else {
//            String priceMessage = "Total: " + NumberFormat.getCurrencyInstance().format(coffeeCount * 75) + "\nThank you!";
            String priceMessage = createOrderSummary(coffeeCount);
            displayMessage(priceMessage);
        }
*/
        int additives = (hasWhippedCream.isChecked()? 1 : 0) + (hasChocolate.isChecked()? 2 : 0);
        String orderText = "Name: " + (nameId.getText().toString().equals("")? "Visitor" : nameId.getText().toString()) + " \n" +
                "Add Whipped Cream? " + (hasWhippedCream.isChecked()? "true": "false") + "\n" +
                "Add Chocolate? " + (hasChocolate.isChecked()? "true": "false") + "\n" +
                "Quantity: " + coffeeCount + "\n" +
                "Total: " + coffeeCount * (5 + additives) + "$\n" +
                "Thank you!";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"some_cafe@gmail.ru"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order to Barista");
        intent.putExtra(Intent.EXTRA_TEXT, orderText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

//    private String createOrderSummary(int quantity) {
//        String name = "Visitor";
//        int oneCupCost = 5;
//        int additives = (hasWhippedCream.isChecked()? 1 : 0) + (hasChocolate.isChecked()? 2 : 0);
//
//        return "Name: " + (nameId.getText().toString().equals("")? name : nameId.getText().toString()) + " \n" +
//                "Add Whipped Cream? " + (hasWhippedCream.isChecked()? "true": "false") + "\n" +
//                "Add Chocolate? " + (hasChocolate.isChecked()? "true": "false") + "\n" +
//                "Quantity: " + quantity + "\n" +
//                "Total: " + quantity * (oneCupCost + additives) + "$\n" +
//                "Thank you!";
//    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Just for 0 quantity
     *//*
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        priceTextView.setText(number + "$");
    }

    private void displayMessage(String message) {
        TextView order = (TextView) findViewById(R.id.price_text_view);
        order.setText(message);
    }*/

    public void increment(View view) {
        if (coffeeCount < 99) {
            coffeeCount++;
            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantityTextView.setText("" + coffeeCount);
        }
    }

    public void decrement(View view) {
        if (coffeeCount > 1) {
            coffeeCount--;
        }
        displayQuantity(coffeeCount);
    }
}