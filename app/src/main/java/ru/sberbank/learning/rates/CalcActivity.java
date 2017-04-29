package ru.sberbank.learning.rates;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import ru.sberbank.learning.rates.networking.Currency;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by user3 on 29.04.2017.
 */

public class CalcActivity extends Activity {

    private EditText mSumEditText;
    private TextView mCharCodeTextView;
    private TextView mResultTextView;

    private CurrenciesStorage mCurrenciesStorage;
    private Currency currency;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_calc);

        mSumEditText = (EditText)findViewById(R.id.calc_currency_value_edit_text);
        mCharCodeTextView = (TextView)findViewById(R.id.calc_char_code_view);
        mResultTextView = (TextView)findViewById(R.id.calc_result_num_text_view);

        mCurrenciesStorage = ((AppStorage)getApplication()).getCurrenciesStorage();
        String charCode = getIntent().getStringExtra(RatesActivity.CHAR_CODE);
        currency = mCurrenciesStorage.findByCode(charCode);

        if(currency != null)
            mCharCodeTextView.setText(currency.getCharCode());
            mSumEditText.
        else
            mCharCodeTextView.setText("not found");

    }
}
