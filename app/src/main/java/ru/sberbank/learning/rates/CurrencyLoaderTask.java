package ru.sberbank.learning.rates;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

import ru.sberbank.learning.rates.networking.CurrenciesList;
import ru.sberbank.learning.rates.networking.Currency;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by enigm777 on 28.04.2017.
 */

public class CurrencyLoaderTask extends AsyncTask<Void,Void,CurrenciesList> {
    private static final String URL_CBR = "http://www.cbr.ru/scripts/XML_daily.asp";
    private static final String LOG_TAG = "CurrencyLoaderTask";

    private WeakReference<CurrenciesStorage> mCurrenciesStorageWeakReference;
    private WeakReference<OnAsyncTaskFinishable> mOnAsyncTaskFinishableWeakReference;


    public CurrencyLoaderTask(CurrenciesStorage currenciesStorage, OnAsyncTaskFinishable activity){
        mCurrenciesStorageWeakReference = new WeakReference<>(currenciesStorage);
        mOnAsyncTaskFinishableWeakReference = new WeakReference<>(activity);

    }

    @Override
    protected CurrenciesList doInBackground(Void... params) {
        CurrenciesList mCurrencyList = null;
        try{
            InputStream inputStream = new URL(URL_CBR).openStream();
            mCurrencyList = CurrenciesList.readFromStream(inputStream);
        } catch (IOException e){
            Log.d(LOG_TAG,"couldn't read stream");
        }

        return mCurrencyList;
    }

    @Override
    protected void onPostExecute(CurrenciesList currencies) {
        CurrenciesStorage currenciesStorage = mCurrenciesStorageWeakReference.get();
        OnAsyncTaskFinishable activity = mOnAsyncTaskFinishableWeakReference.get();

        currenciesStorage.setLoadedList(currencies);
        activity.onAsyncTaskFinished();


        super.onPostExecute(currencies);
    }
}
