package ru.qupol.MonopolyHelper.Watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;


/**
 * Created by Pavel on 20.09.2015.
 */
public class MoneyWather implements TextWatcher {


    private TextView textView;

    public MoneyWather(TextView textView) {
        this.textView = textView;

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        format(s);
    }

    private void format(Editable s) {
        String countString = s.toString();
        int count = Integer.parseInt(countString);
        int kStart = countString.length() - 4;

        if (count >= 1000) {
            boolean hasThousands = countString.substring(kStart).equals("000");
            if (hasThousands) {
                String countK = countString.substring(0, kStart + 1) + "K";
                textView.setText(countK);
            }
        }

    }
}
