package ru.qupol.MonopolyHelper.Activity;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import ru.qupol.MonopolyHelper.Entity.Player;
import ru.qupol.MonopolyHelper.Facade;

import java.util.List;

/**
 * Created by Pavel on 13.10.2015.
 */
public class SendToActivity extends PlayersListActivity {

    @Override
    protected void fillPlayers(LinearLayout layout) {
        List<Player> players = Facade.getPlayerDAO().getAll();
        for (final Player player : players) {
            RelativeLayout relativeLayout = createPlayerBar(player.getName(), player.getBalance(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle extras = getIntent().getExtras();
                    Player sender = Facade.getPlayerDAO().get(extras.getInt("senderPlayerId"));
                    int takeCount = extras.getInt("sendValue");
                    sender.sendBalanceTo(player, takeCount);
                    setResult(RESULT_OK);
                    finish();
                }
            });
            layout.addView(relativeLayout);
        }

    }
}
