package ru.qupol.MonopolyHelper.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ru.qupol.Hello2App.R;
import ru.qupol.MonopolyHelper.Facade;

/**
 * Created by Pavel on 20.09.2015.
 */
public class NewGameActivity extends Activity {

    private int playersCount;
    private int startBalance;
    private int income;

    TextView balanceTV;
    TextView incomeTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgame);


        checkPlayersCount();
        checkStartBalance();
        checkIncome();

        nextButton();


    }

    private void checkIncome() {
        incomeTV = (TextView) findViewById(R.id.income);


    }

    private void checkStartBalance() {
        balanceTV = (TextView) findViewById(R.id.startBalance);
    }

    private void nextButton() {
        //TODO: need check
        Button nextButton = (Button) findViewById(R.id.bPlayersSettings);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (balanceTV.getText().length() != 0)
                    startBalance = Integer.parseInt(balanceTV.getText().toString());

                if (incomeTV.getText().length() != 0)
                    income = Integer.parseInt(incomeTV.getText().toString());

                if (startBalance > 0 && income > 0 && playersCount > 1 && playersCount < 9) {
                    Intent playersSettingsIntent = new Intent(getApplicationContext(), PlayersSettingsActivity.class);

                    Facade.getGameDAO().getGame().setPlayersCount(playersCount);
                    Facade.getGameDAO().getGame().setStartBalance(startBalance);
                    Facade.getGameDAO().getGame().setIncomeValue(income);

                    startActivity(playersSettingsIntent);
                }
            }
        });
    }

    private void checkPlayersCount() {
        final EditText playersCountEdit = (EditText) findViewById(R.id.PlayersCount);
        playersCountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    String error = getResources().getString(R.string.validate_newGame_error_playersCount);
                    playersCountEdit.setError(error);
                } else {
                    playersCountEdit.setError(null);
                    playersCount = Integer.parseInt(playersCountEdit.getText().toString());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}