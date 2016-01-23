package ru.qupol.MonopolyHelper.Entity;

import java.util.List;

/**
 * Created by Pavel on 20.09.2015.
 * Entity for a players
 */
public class Player {

    private int id;
    private String name;
    private int balance;

    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int ballance) {
        this.balance = ballance;
    }

    public void addBalance(int balanceToAdd) {
        balance += balanceToAdd;
    }

    public boolean decreaseBalance(int balance) {
        int preparedResult = this.balance - balance;
        if (preparedResult < 0)
            return false;
        this.balance = preparedResult;
        return true;
    }

    public boolean sendBalanceTo(Player getterPlayer, int getCount) {
        if (decreaseBalance(getCount)) {
            getterPlayer.addBalance(getCount);
            return true;
        } else
            return false;
    }

    public boolean sendBalanceTo(List<Player> players, int getCount) {
        int otherPlayersCount = players.size() - 1;
        if (decreaseBalance(getCount * otherPlayersCount)) {
            for (Player player : players) {
                if (player != this) {
                    player.addBalance(getCount);
                }
            }
            return true;
        } else
            return false;
    }

    @Override
    public String toString() {
        return
//                String.valueOf(getId()) + ": " +
                        getName() + "(" + getBalance() + ")";
    }

    @Override
    public int hashCode() {
        return id + name.hashCode() + balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Player) {
            Player player = (Player) o;

            if (this.hashCode() == player.hashCode()) {
                if (this.id == player.id
                        && this.name.equals(player.name)
                        && this.balance == player.balance)
                    return true;
            }
        }
        return false;
    }
}
