package com.example.android.justjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




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
    @SuppressLint("QueryPermissionsNeeded")
    public void submitOrder(View view) {

        int additives = (hasWhippedCream.isChecked()? 1 : 0) + (hasChocolate.isChecked()? 2 : 0);
        String namePattern = nameId.getText().toString().equals("")? getString(R.string.visitor) : nameId.getText().toString();
        String orderText = getString(R.string.order_summary_name, namePattern) + " \n" +
                getString(R.string.additives,
                        hasWhippedCream.isChecked()? "✓": "✗",
                        hasChocolate.isChecked()? "✓": "✗") + "\n" +
                getString(R.string.quantity_word) + " " + coffeeCount + "\n" +
                getString(R.string.total) + " " + coffeeCount * (5 + additives) + "$\n" +
                getString(R.string.thanks);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"some_cafe@gmail.ru"});
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject_of_mail));
        intent.putExtra(Intent.EXTRA_TEXT, orderText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

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
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.caution), Toast.LENGTH_SHORT).show();
        }
        displayQuantity(coffeeCount);
    }
}