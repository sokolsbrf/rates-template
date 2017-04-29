package ru.sberbank.learning.rates.appView;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user3 on 29.04.2017.
 */

public class CurrencyGraphicView extends View {
    public CurrencyGraphicView(Context context) {
        super(context);
    }

    public CurrencyGraphicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CurrencyGraphicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CurrencyGraphicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void init(){

    }


}
