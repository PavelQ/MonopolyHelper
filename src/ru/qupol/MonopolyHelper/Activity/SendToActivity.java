package ru.qupol.MonopolyHelper.Activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import ru.qupol.MonopolyHelper.Entity.Player;
import ru.qupol.MonopolyHelper.Facade;
import ru.qupol.MonopolyHelper.R;

import java.util.List;

/**
 * Created by Pavel on 13.10.2015.
 * Players list showed on player send money action
 */
public class SendToActivity extends PlayersListActivity {

    @Override
    protected void fillPlayers(LinearLayout layout) {
        final List<Player> players = Facade.getPlayerDAO().getAll();
        Bundle extras = getIntent().getExtras();
        final Player sender = Facade.getPlayerDAO().get(extras.getInt("senderPlayerId"));
        final int takeCount = extras.getInt("sendValue");
        for (final Player player : players) {

            RelativeLayout relativeLayout = createPlayerBar(player.getName(), player.getBalance(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sender.sendBalanceTo(player, takeCount);
                    setResult(RESULT_OK);
                    finish();
                }
            });
            if (player != sender) {
                layout.addView(relativeLayout);
            }
        }
        RelativeLayout relativeLayoutAll = createPlayerBar("все", 0, new View.OnClickListener() {
            final String errorTitle = getString(R.string.money_transaction_error);
            final String errorMessage = getString(R.string.not_enough_money_for_send_all);
            final String okText = getApplicationContext().getResources().getString(R.string.OkButtonText);

            @Override
            public void onClick(View v) {
                if (sender.sendBalanceTo(players, takeCount)) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    new AlertDialog.Builder(SendToActivity.this)
                            .setTitle(errorTitle)
                            .setMessage(errorMessage)
                            .setPositiveButton(okText, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .create()
                            .show();
                }

            }
        });
        layout.addView(relativeLayoutAll);

    }
}
