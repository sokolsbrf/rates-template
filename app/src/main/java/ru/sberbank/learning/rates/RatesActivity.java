package ru.sberbank.learning.rates;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import ru.sberbank.learning.rates.storage.CurrenciesStorage;

public class RatesActivity extends Activity implements OnAsyncTaskFinishable{

    private ListView mCurrenciesListView;
    private CurrenciesListAdapter mCurrenciesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);

        mCurrenciesListView = (ListView)findViewById(R.id.currency_list_view);
        CurrenciesStorage mCurrenciesStorage = ((AppStorage)getApplication()).getCurrenciesStorage();

        if(!mCurrenciesStorage.isReady()){
            //loader asynctask
        }
        else{
            mCurrenciesListAdapter = new CurrenciesListAdapter(mCurrenciesStorage);
            mCurrenciesListView.setAdapter(mCurrenciesListAdapter);
        }


    }

    @Override
    public void onAsyncTaskFinished() {
        //mCurrenciesListAdapter = new CurrenciesListAdapter(mCurrenciesStorage);
        mCurrenciesListView.setAdapter(mCurrenciesListAdapter);
    }
}
