package ru.qupol.MonopolyHelper.Entity;


/**
 * Created by Pavel on 28.09.2015.
 */
public class Game {
    private int playersCount;
    private int startBalance;
    private int incomeValue;

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public int getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(int startBalance) {
        this.startBalance = startBalance;
    }

    public int getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(int incomeValue) {
        this.incomeValue = incomeValue;
    }
}
