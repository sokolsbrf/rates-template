package ru.sberbank.learning.rates;

import android.content.pm.PackageInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
// import Curr package
import ru.sberbank.learning.rates.networking.Currency;
import ru.sberbank.learning.rates.utils.DateFormatUtils;

import java.util.List;

/**
 * Created by Tan-DS on 4/27/2017.
 */

public class AdapterRates extends BaseAdapter{

    private List<Currency> mRates;

    public AdapterRates(List<Currency> mRates) {
        this.mRates = mRates;
    }

    @Override
    public int getCount() {
        return mRates.size();
    }

    @Override
    public Object getItem(int position) {
        return mRates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRates.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_elem, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.course = (TextView) view.findViewById(R.id.course);
            holder.nominal = (TextView) view.findViewById(R.id.nominal);
            holder.index_of_val = (TextView) view.findViewById(R.id.index_of_val);
            holder.name_val = (TextView) view.findViewById(R.id.name_val);

            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        holder.nominal.setText( mRates.get(position).getNominal().toString());
        holder.course.setText( mRates.get(position).getValue().toString());
        holder.name_val.setText(mRates.get(position).getName());
        holder.index_of_val.setText(mRates.get(position).getId() + " (" + mRates.get(position).getCharCode() + ")" );

        return view;
    }



    private static class ViewHolder {
        private TextView nominal; private TextView name_val; private TextView course; private TextView index_of_val;
    }

}
