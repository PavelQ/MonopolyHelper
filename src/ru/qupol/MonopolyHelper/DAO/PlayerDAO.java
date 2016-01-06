package ru.qupol.MonopolyHelper.DAO;

import ru.qupol.MonopolyHelper.Entity.Player;

import java.util.List;

/**
 * Created by Pavel on 21.09.2015.
 */
public  interface PlayerDAO {


    Player get(int id);

    List<Player> getAll();

    void update(Player player);

    void updateAll(List<Player> players);

    boolean delete(Player player);

    void deleteAll(List<Player> players);

    public void add(Player player);

    public void clear();



}
