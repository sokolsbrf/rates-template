package ru.sberbank.learning.rates;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ru.sberbank.learning.rates.networking.CurrenciesList;
import ru.sberbank.learning.rates.networking.Currency;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by Tan-DS on 4/27/2017.
 */

public class assync extends AsyncTask<String, assync.Bundle, CurrenciesList> {

    private URLConnection urlConnection;
    private URL url;
    private List<Currency> curr;
    private WeakReference<CurrenciesStorage> curStor = new WeakReference<CurrenciesStorage>(null);
    private WeakReference<RatesActivity> ratesActivity = new WeakReference<RatesActivity>(null);
    private WeakReference<ListView> listView = new WeakReference<ListView>(null);

    @Override
    protected void onCancelled() {
        curStor.clear();
        ratesActivity.clear();
        listView.clear();
    }

    public assync(CurrenciesStorage currenciesStorage, RatesActivity rtAct, ListView lv){
        curStor = new WeakReference<>(currenciesStorage);
        ratesActivity = new WeakReference<RatesActivity>(rtAct);
        listView = new WeakReference<ListView>(lv);

    }

    @Override
    protected CurrenciesList doInBackground(String... urls) {

        CurrenciesList currenciesList = null;

        try {
            url = new URL("http://cbr.ru/scripts/XML_daily.asp");

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection = (HttpURLConnection) url.openConnection();
            currenciesList = CurrenciesList.readFromStream(urlConnection.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return currenciesList;
    }


    @Override
    protected void onPostExecute(CurrenciesList currenciesList) {

            curStor.get().setLoadedList(currenciesList);
            Activity activity = ratesActivity.get();
            ListView lv = listView.get();
            if (activity!=null && lv!= null){

                AdapterRates mRatesAdaper = new AdapterRates(currenciesList.getCurrencies());
                lv.setAdapter(mRatesAdaper);

            }

    }

    public class Bundle {
        CharSequence mCourse, mNominal, mName, mIndex;
    }
}


