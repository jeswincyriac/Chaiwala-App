package com.example.jeswin.justjava;

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 1;
    public void submitOrder(View view) {
        CheckBox cream=(CheckBox) findViewById(R.id.checkBox);
        boolean cisc = cream.isChecked();
        EditText text=(EditText)findViewById(R.id.editText) ;
        String name = text.getText().toString();
        EditText tex=(EditText)findViewById(R.id.editText2) ;
        String phn = tex.getText().toString();
        int w=0;
        if(cisc == true)
            w=2;
        String message = "Name:"+name+"\nphn:"+phn+"\nQuantity"+quantity+"\nOrder Confirmed!!\ninterest in whipped cream? "+cisc+"\nTottal Cost Rs." +( (quantity*5)+(quantity*w)) + " deducted";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:windows7testdata@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"coffee order");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }
    public void increment(View view) {
        quantity=quantity+1;
        display(quantity);
        displayMessage(NumberFormat.getCurrencyInstance().format(quantity*5)+"(+extra 2$ for each whipped cream coffee)");

    }
    public void decrement(View view) {
        if(quantity==1)
        {
            Toast.makeText(this , " Min no of coffees is 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);
        displayMessage(NumberFormat.getCurrencyInstance().format(quantity*5)+"(+extra 2$ for each whipped cream coffee)");

    }

    /**
     * This method displays the given quantity value on the screen.
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
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }



}
