package ru.sberbank.learning.rates;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.learning.rates.networking.CurrenciesList;
import ru.sberbank.learning.rates.networking.Currency;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

public class RatesActivity extends Activity {

    private ListView mRatesListView;
    private AdapterRates mRatesAdaper;
    CurrenciesList curr;
    boolean isOnl;
    CurrenciesStorage str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
        isOnl = isOnline(getApplicationContext());
        mRatesListView = (ListView) findViewById(R.id.CouseList);
        List <Currency> infoPack = new ArrayList<Currency>();
        Button mButt = (Button) findViewById(R.id.noInternetLayoutButt);

        CurrenciesStorage currenciesStorage = new CurrenciesStorage();
        currenciesStorage.setLoadedList(curr);
        ListView lv = (ListView) findViewById(R.id.CouseList);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.noInternetLayout);

        str = ((patesApp)getApplication()).getStr(); // work with application
        if (!isOnl && str.getLoadedList() == null) { // no internet

            lv.setVisibility(View.INVISIBLE);
            rl.setVisibility(View.VISIBLE);

        }else {

            lv.setVisibility(View.VISIBLE);
            rl.setVisibility(View.INVISIBLE);

            if (str.isReady()) {
                curr = str.getLoadedList();
                infoPack = curr.getCurrencies();

                mRatesAdaper = new AdapterRates(infoPack);
                mRatesListView.setAdapter(mRatesAdaper);
            } else {
                new assync(str, this, mRatesListView).execute();
            }
        }

            mButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    isOnl = isOnline(getApplicationContext());
                    runMeSingleTop();
                }
            });

        mRatesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Currency currenc = str.getLoadedList().getCurrencies().get(position);
                runCalc(currenc);
                // тут будет открываться
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOnl && str.getLoadedList() == null){
            str = ((patesApp)getApplication()).getStr(); // work with application
            new assync(str, this, mRatesListView).execute();

            ListView lv = (ListView) findViewById(R.id.CouseList);
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.noInternetLayout);
            lv.setVisibility(View.VISIBLE);
            rl.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void runCalc(Currency curr){
        Intent runMe = new Intent(this, CalcActivity.class);


        runMe.putExtra("name", curr.getCharCode());
        runMe.putExtra("nominal", curr.getNominal());
        runMe.putExtra("value", curr.getValue());


        startActivity(runMe);
    }

    private void runMeSingleTop() {
        Intent runMe = new Intent(this, RatesActivity.class);
        runMe.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(runMe);
    }
    // checking internet comnection
    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }
}
