package ru.qupol.MonopolyHelper.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ru.qupol.MonopolyHelper.Dialog.ExitDialog;
import ru.qupol.MonopolyHelper.Entity.Player;
import ru.qupol.MonopolyHelper.Facade;
import ru.qupol.MonopolyHelper.R;
import ru.qupol.MonopolyHelper.Utils.ViewUtils;

import java.util.List;

/**
 * Created by Pavel on 22.09.2015.
 * Window with a list of players and their balance
 */
public class PlayersListActivity extends Activity {
   protected LinearLayout mainLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.players_list);

        mainLayout = (LinearLayout) findViewById(R.id.playersList);
       updatePlayerList();


    }

    @Override
    public void onBackPressed() {
        ExitDialog.show(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Facade.getPlayerDAO().clear();
               finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatePlayerList();
    }

    protected void updatePlayerList(){
        mainLayout.removeAllViewsInLayout();
        fillPlayers(mainLayout);
    }

    private View.OnClickListener onClickGoToPlayerPanel(final int playerId){
        final Intent intent = new Intent(getApplicationContext(),PlayerPanelActivity.class);
       return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("playerId",playerId);
                startActivity(intent);
            }
        };
    }

    protected void fillPlayers(LinearLayout layout) {

        List<Player> players = Facade.getPlayerDAO().getAll();
        for (final Player player : players) {

            RelativeLayout relativeLayout = createPlayerBar(player.getName(),player.getBalance(),onClickGoToPlayerPanel(player.getId()));
            layout.addView(relativeLayout);

        }

    }

    protected RelativeLayout createPlayerBar(String playerName, int playerBalance, View.OnClickListener clickListener){
        RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());

        TextView nameTV = new TextView(getApplicationContext());
        nameTV.setTextAppearance(getApplicationContext(), android.R.style.TextAppearance_DeviceDefault_Large);
        nameTV.setText(playerName);
        relativeLayout.addView(nameTV);

        TextView balanceTV = new TextView(getApplicationContext());
        balanceTV.setTextAppearance(getApplicationContext(), android.R.style.TextAppearance_DeviceDefault_Medium);
//        balanceTV.setText(Integer.toString(playerBalance));
        balanceTV.setText(ViewUtils.MoneyStringFormat(playerBalance));
        relativeLayout.addView(balanceTV);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) balanceTV.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        balanceTV.setLayoutParams(params);
        relativeLayout.setOnClickListener(clickListener);
        return relativeLayout;
    }

}