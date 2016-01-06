package ru.qupol.MonopolyHelper.DAO;

import ru.qupol.MonopolyHelper.Entity.Game;

/**
 * Created by Pavel on 28.09.2015.
 */
public class GameDAOMemoryImpl implements GameDAO {


    static GameDAO instance;
    Game game;

    private GameDAOMemoryImpl() {
    }

    ;

    public static GameDAO getInstance() {
        if (instance == null) {
            instance = new GameDAOMemoryImpl();
        }
        return instance;
    }

    @Override
    public Game getGame() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }
}
