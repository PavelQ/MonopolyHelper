package ru.qupol.MonopolyHelper.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ru.qupol.MonopolyHelper.Dialog.PriceEnterDialog;
import ru.qupol.MonopolyHelper.Entity.Player;
import ru.qupol.MonopolyHelper.Facade;
import ru.qupol.MonopolyHelper.R;

/**
 * Created by Pavel on 27.09.2015.
 */
public class PlayerPanelActivity extends Activity {
    private Player player;
    private TextView balanceTV;
    private TextView nameTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_panel);
        player = Facade.getPlayerDAO().get(getIntent().getExtras().getInt("playerId"));

        this.nameTV = (TextView) findViewById(R.id.playerPanelNameTV);
        this.balanceTV = (TextView) findViewById(R.id.playerPanelBalanceTV);
        setName();
        updateBalance();

        setButtonClick();

    }

    private void setButtonClick() {
        //onclick button

        Button incomeBtutton = incomeButtonSettings();

        Button plusButton = plusButtonSettings();
        Button minusButton =  minusButtonSettings();
        Button sendToButton = sendToButtonSettings();

    }

    private Button sendToButtonSettings() {
        Button sendToButton = (Button) findViewById(R.id.playerPanelSendToBtn);
        sendToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PriceEnterDialog priceEnterDialog = new PriceEnterDialog(PlayerPanelActivity.this) {
                    @Override
                    public void onOkClick() {
                        int balanceToInc = Integer.parseInt(this.getEditText());
                        int preparedResult = player.getBalance() - balanceToInc;
                        if (preparedResult >= 0) {
                            Intent intent = new Intent(this.getContext(),SendToActivity.class);
                            intent.putExtra("senderPlayerId",player.getId());
                            intent.putExtra("sendValue",balanceToInc);
                            int requestCode = 0; // nothing
                            startActivityForResult(intent,requestCode);
                            this.dismiss();

                        } else {
                            this.getPriceEditText().setError(getResources().getString(R.string.notEnoughMoney));
                        }
                    }
                };
                priceEnterDialog.show();
            }
        });
        return sendToButton;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            finish();
        }
    }

    private Button incomeButtonSettings() {
        Button incomeButton = (Button) findViewById(R.id.playerPanelIncomeBtn);
        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int incomeValue = Facade.getGameDAO().getGame().getIncomeValue();
                player.setBalance(player.getBalance() + incomeValue);
                updateBalance();
            }
        });
        return  incomeButton;
    }

    private  Button plusButtonSettings(){
        Button plusButton = (Button) findViewById(R.id.playerPanelIncreaseBtn);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PriceEnterDialog priceEnterDialog = new PriceEnterDialog(PlayerPanelActivity.this) {
                    @Override
                    public void onOkClick() {
                        int balanceToInc = Integer.parseInt(this.getEditText());
                        player.setBalance(player.getBalance() + balanceToInc);
                        updateBalance();
                        this.dismiss();
                    }
                };
                priceEnterDialog.show();
            }
        });
        return  plusButton;
    }

    private Button minusButtonSettings() {
        Button minusButon = (Button) findViewById(R.id.playerPanelDecreaseBtn);
        minusButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PriceEnterDialog priceEnterDialog = new PriceEnterDialog(PlayerPanelActivity.this) {
                    @Override
                    public void onOkClick() {
                        int balanceToInc = Integer.parseInt(this.getEditText());
                        int preparedResult = player.getBalance() - balanceToInc;
                        if (preparedResult >= 0) {
                            player.setBalance(preparedResult);
                            updateBalance();
                            this.dismiss();
                        } else {
                            this.getPriceEditText().setError(getResources().getString(R.string.notEnoughMoney));
                        }

                    }

                };
                priceEnterDialog.show();
            }
        });
        return minusButon;
    }

    private void updateBalance() {
        //set ballance
        TextView balanceView = this.balanceTV;
        Player player = this.player;
        balanceView.setText(String.valueOf(player.getBalance()));
    }

    private void setName() {
        this.nameTV.setText(player.getName());
    }
}