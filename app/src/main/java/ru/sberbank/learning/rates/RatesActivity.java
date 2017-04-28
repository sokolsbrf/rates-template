package ru.sberbank.learning.rates;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import ru.sberbank.learning.rates.storage.CurrenciesStorage;

public class RatesActivity extends Activity implements OnAsyncTaskFinishable{

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
            mProgressDialog = ProgressDialog.show(this, "Loading","Loading currencies...",false,false);
            currencyLoaderTask = new CurrencyLoaderTask(mCurrenciesStorage,this);
            currencyLoaderTask.execute();
        }
        else {
            mCurrenciesListAdapter = new CurrenciesListAdapter(mCurrenciesStorage);
            mCurrenciesListView.setAdapter(mCurrenciesListAdapter);
        }
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
