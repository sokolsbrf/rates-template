package ru.sberbank.learning.rates;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ru.sberbank.learning.rates.networking.Currency;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

public class RatesActivity extends Activity implements OnAsyncTaskFinishable{
    public static final String CHAR_CODE = "CHAR_CODE";

    private ListView mCurrenciesListView;
    private CurrenciesListAdapter mCurrenciesListAdapter;
    private CurrenciesStorage mCurrenciesStorage;
    private CurrencyLoaderTask currencyLoaderTask;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);

        mCurrenciesListView = (ListView)findViewById(R.id.currency_list_view);
        mCurrenciesStorage = ((AppStorage)getApplication()).getCurrenciesStorage();

        if(!mCurrenciesStorage.isReady()){
            currencyLoaderTask = new CurrencyLoaderTask(mCurrenciesStorage,this);
            currencyLoaderTask.execute();
            mProgressDialog = ProgressDialog.show(this, "Loading","Loading currencies...",false,false);
        }
        else {
            mCurrenciesListAdapter = new CurrenciesListAdapter(mCurrenciesStorage);
            mCurrenciesListView.setAdapter(mCurrenciesListAdapter);
        }

        mCurrenciesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RatesActivity.this, CalcActivity.class);


                intent.putExtra(CHAR_CODE, mCurrenciesStorage.getLoadedList().getCurrencies().get(position).getCharCode());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAsyncTaskFinished() {
        mProgressDialog.dismiss();
        mCurrenciesListAdapter = new CurrenciesListAdapter(mCurrenciesStorage);
        mCurrenciesListView.setAdapter(mCurrenciesListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(currencyLoaderTask != null)
            currencyLoaderTask.cancel(true);
    }
}
