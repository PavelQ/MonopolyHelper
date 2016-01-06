package ru.qupol.MonopolyHelper.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import ru.qupol.MonopolyHelper.Entity.Player;
import ru.qupol.MonopolyHelper.Facade;
import ru.qupol.Hello2App.R;

/**
 * Created by Pavel on 20.09.2015.
 */
public class PlayersSettingsActivity extends Activity {
    private int playersCount=0;
    private ViewGroup layoutWithNames = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.playersCount = Facade.getGameDAO().getGame().getPlayersCount();

        setContentView(R.layout.playerssettings);

        createComponents();

        Button nextButton = (Button) findViewById(R.id.bPlayersSettingsNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < layoutWithNames.getChildCount(); i++) {
                    EditText editText =(EditText)layoutWithNames.getChildAt(i);
                    String name = editText.getText().toString();
                    int startBalance =Facade.getGameDAO().getGame().getStartBalance();
                    Player player = new Player(name,startBalance);

                    Facade.getPlayerDAO().add(player);
                }

                Intent playersListIntent = new Intent();
                playersListIntent.setClass(getApplicationContext(),PlayersListActivity.class);
                startActivity(playersListIntent);
            }
        });
    }

    private void createComponents() {
         layoutWithNames = (LinearLayout)findViewById(R.id.layout);

        for(int i = 0; i<playersCount; i++){
           EditText editText = createPlayerEdit(i+1);
            layoutWithNames.addView(editText);
            if(i==0){
                editText.requestFocus();
            }
        }
    }
    private EditText createPlayerEdit(int num){
        EditText editText =new EditText(getApplicationContext());
        String hint = getResources().getString(R.string.playerNameText)+" "+num;
        editText.setHint(hint);
        return editText;
    }

}