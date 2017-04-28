package ru.sberbank.learning.rates;

import android.app.Application;

import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by enigm777 on 28.04.2017.
 */

public class AppStorage extends Application {
    private CurrenciesStorage mCurrenciesStorage = new CurrenciesStorage();
    public CurrenciesStorage getCurrenciesStorage(){
        return mCurrenciesStorage;
    }
}
