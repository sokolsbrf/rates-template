package ru.sberbank.learning.rates;

import android.app.Application;

import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by Tan-DS on 4/29/2017.
 */

public class patesApp extends Application {

    public CurrenciesStorage getStr() {
        return str;
    }

   private CurrenciesStorage str = new CurrenciesStorage();


}
