package ru.sberbank.learning.rates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.simpleframework.xml.Text;

import java.util.List;

import ru.sberbank.learning.rates.networking.Currency;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by enigm777 on 28.04.2017.
 */

public class CurrenciesListAdapter extends BaseAdapter {

    private List<Currency> mCurrencyList;

    public CurrenciesListAdapter(CurrenciesStorage currenciesStorage){
        mCurrencyList = currenciesStorage.getLoadedList().getCurrencies();
    }

    @Override
    public int getCount() {
        return mCurrencyList.size();
    }

    @Override
    public Currency getItem(int position) {
        return mCurrencyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view==null){
            CurrencyViewHolder currencyViewHolder = new CurrencyViewHolder();

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.currency_item,parent,false);

            currencyViewHolder.mCurrencyNameView = (TextView)view.findViewById(R.id.currency_name);
            currencyViewHolder.mCurrencyCharCodeView = (TextView)view.findViewById(R.id.currency_char_code);
            currencyViewHolder.mCurrencyNominalView = (TextView)view.findViewById(R.id.currency_nominal);
            currencyViewHolder.mCurrencyNumCodeView = (TextView)view.findViewById(R.id.currency_num_code);
            currencyViewHolder.mCurrencyValueView = (TextView)view.findViewById(R.id.currency_value);

            view.setTag(currencyViewHolder);
        }

        CurrencyViewHolder currencyViewHolder = (CurrencyViewHolder)view.getTag();

        Currency currency = mCurrencyList.get(position);

        if(currency!=null) {
            currencyViewHolder.mCurrencyNameView.setText(currency.getName());
            currencyViewHolder.mCurrencyValueView.setText(String.valueOf(currency.getValue()));
            currencyViewHolder.mCurrencyNominalView.setText(String.valueOf(currency.getNominal()));
            currencyViewHolder.mCurrencyNumCodeView.setText(String.valueOf(currency.getNumCode()));
            currencyViewHolder.mCurrencyCharCodeView.setText(currency.getCharCode());
        } else {
            currencyViewHolder.mCurrencyNameView.setText("text");
            currencyViewHolder.mCurrencyValueView.setText("text");
            currencyViewHolder.mCurrencyNominalView.setText("text");
            currencyViewHolder.mCurrencyNumCodeView.setText("text");
            currencyViewHolder.mCurrencyCharCodeView.setText("text");
        }
        return view;
    }

    private static class CurrencyViewHolder{
        TextView mCurrencyNameView;
        TextView mCurrencyNumCodeView;
        TextView mCurrencyCharCodeView;
        TextView mCurrencyValueView;
        TextView mCurrencyNominalView;
    }
}
