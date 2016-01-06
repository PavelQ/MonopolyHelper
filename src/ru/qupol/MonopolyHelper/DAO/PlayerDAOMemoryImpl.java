package ru.qupol.MonopolyHelper.DAO;

import ru.qupol.MonopolyHelper.Entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pavel on 22.09.2015.
 */
public class PlayerDAOMemoryImpl implements PlayerDAO {

    private Set<Player> players;
    private int lastId = 0;
    private static PlayerDAO instance;

    private PlayerDAOMemoryImpl() {
        players = new HashSet<Player>();
    }

    public static PlayerDAO getInstance() {
        if (instance == null) {
            instance = new PlayerDAOMemoryImpl();
        }
        return instance;
    }


    @Override
    public Player get(int id) {
        Player targetPlayer = null;
        for (Player player : players) {
            if (player.getId() == id) {
                targetPlayer = player;
                break;
            }
        }
        return targetPlayer;
    }

    @Override
    public List<Player> getAll() {
        return new ArrayList<Player>(players);
    }

    @Override
    public void update(Player player) {
        players.add(player);
    }

    @Override
    public void updateAll(List<Player> players) {
        this.players.addAll(players);
    }

    @Override
    public boolean delete(Player player) {
        return players.remove(player);
    }

    @Override
    public void deleteAll(List<Player> players) {
        this.players.removeAll(players);
    }

    @Override
    public void add(Player player) {
        player.setId(lastId++);
        players.add(player);
    }

    public void clear(){
        players.clear();
    }

}
