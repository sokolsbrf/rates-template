package ru.sberbank.learning.rates;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import ru.sberbank.learning.rates.networking.Currency;

/**
 * Created by Tan-DS on 4/30/2017.
 */

public class CalcActivity extends Activity {

    TextView mName, nOut;
    EditText mEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

      //  Currency cls = getIntent().getParcelableExtra("curr");

        String str = getIntent().getStringExtra("name");
        final Double nom = getIntent().getDoubleExtra("nominal", 1);
        final Double val = getIntent().getDoubleExtra("value", 0);


        mName = (TextView)findViewById(R.id.index_of_val);
        nOut = (TextView) findViewById(R.id.valueOutput);
        mEditText = (EditText) findViewById(R.id.editingNum);

        mName.setText(str);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mEditText.getText().toString().equals("")){
                    Double from = Double.parseDouble(mEditText.getText().toString());
                    nOut.setText(String.format("%s", String.valueOf(from*val/nom)) + "p");
                } else{
                    nOut.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}
