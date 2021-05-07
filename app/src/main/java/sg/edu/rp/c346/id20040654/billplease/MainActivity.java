package sg.edu.rp.c346.id20040654.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // creating variables
    EditText etAmount;
    EditText etPax;
    EditText etDiscount;
    ToggleButton tbSVC;
    ToggleButton tbGST;
    RadioGroup rgPayment;
    Button bSplit;
    Button bReset;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning values to variables
        etAmount = findViewById(R.id.editTextAmount);
        etPax = findViewById(R.id.editTextPax);
        etDiscount = findViewById(R.id.editTextDiscount);
        tbSVC = findViewById(R.id.toggleButtonSVC);
        tbGST = findViewById(R.id.toggleButtonGST);
        rgPayment = findViewById(R.id.radioGroupPayment);
        bSplit = findViewById(R.id.buttonSplit);
        bReset = findViewById(R.id.buttonReset);
        tvResult = findViewById(R.id.textViewResults);

        bSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sAmount = etAmount.getText().toString();
                String sPax = etPax.getText().toString();
                String sDiscount = etDiscount.getText().toString();
                double amount = 0;
                int pax = 0;
                double discount = 0;
                String result = "";
                boolean error = false;
                tvResult.setTextColor(Color.parseColor("#000000"));

                if(sAmount.trim().length() != 0) {
                    amount = Double.parseDouble(sAmount);
                    if(amount <= 0) {
                        result += "Invalid amount.\n";
                        error = true;
                    }
                } else {
                    result += "The amount field is empty.\n";
                    error = true;
                }
                if(sPax.trim().length() != 0) {
                    pax = Integer.parseInt(sPax);
                    if(pax <= 0) {
                        result += "Invalid num of pax.\n";
                        error = true;
                    }
                } else {
                    result += "The num of pax field is empty.\n";
                    error = true;
                }
                if(sDiscount.trim().length() != 0) {
                    discount = Double.parseDouble(sDiscount);
                    if(discount < 0 || discount > 100) {
                        result += "Invalid discount.\n";
                        error = true;
                    }
                }

                if (error == true) {
                    tvResult.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    if(tbSVC.isChecked()) {
                        amount = amount*1.1;
                    }
                    if(tbGST.isChecked()) {
                        amount = amount*1.07;
                    }
                    if(discount != 0) {
                        amount = amount * (((100-discount)/100));
                    }

                    result += String.format("Total : $%.2f \nAmount Per Person: $%.2f", amount, amount/pax);
                    int checkedRadioId = rgPayment.getCheckedRadioButtonId();
                    if(checkedRadioId == R.id.radioButtonPaynow) {
                        result += "\nPayNow to 91234567";
                    }
                }

                tvResult.setText(result);

            }
        });

        bReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etAmount.clearFocus();
                etPax.setText("");
                etPax.clearFocus();
                etDiscount.setText("");
                etDiscount.clearFocus();
                tbSVC.setChecked(false);
                tbGST.setChecked(false);
                rgPayment.check(R.id.radioButtonCash);
                rgPayment.clearFocus();
                tvResult.setText("");
                tvResult.setTextColor(Color.parseColor("#000000"));

            }
        });


    }
}