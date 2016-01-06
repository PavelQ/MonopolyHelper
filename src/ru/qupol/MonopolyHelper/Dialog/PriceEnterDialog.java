package ru.qupol.MonopolyHelper.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.qupol.Hello2App.R;

/**
 * Created by Pavel on 29.09.2015.
 */
public abstract class PriceEnterDialog extends Dialog {

    final EditText priceEditText;
    private View.OnClickListener onOkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //fixme:add edit not empty validation
            onOkClick();
        }
    };
    private View.OnClickListener onCancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onCancelClick();

        }
    };

    public void onCancelClick() {
        this.cancel();
    }


    public PriceEnterDialog(Context context) {
        super(context);
        this.setContentView(R.layout.price_enter_dialog);
        this.setTitle(context.getResources().getString(R.string.enterPriceText));
        priceEditText = (EditText) this.findViewById(R.id.editTextKeywordsToBlock);
        Button okButton = (Button) this.findViewById(R.id.buttonBlockByKeyword);
        okButton.setOnClickListener(onOkClickListener);

        Button cancelButton = (Button) this.findViewById(R.id.buttonCancelBlockKeyword);
        cancelButton.setOnClickListener(onCancelClickListener);
    }

    public String getEditText(){
        return priceEditText.getText().toString();
    }
    public void setEditText(String text){
        priceEditText.setText(text);
    }

    public EditText getPriceEditText() {
        return priceEditText;
    }

    public abstract void onOkClick();

}


