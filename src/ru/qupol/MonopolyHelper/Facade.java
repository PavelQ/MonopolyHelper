package ru.qupol.MonopolyHelper;

import ru.qupol.MonopolyHelper.DAO.GameDAO;
import ru.qupol.MonopolyHelper.DAO.GameDAOMemoryImpl;
import ru.qupol.MonopolyHelper.DAO.PlayerDAO;
import ru.qupol.MonopolyHelper.DAO.PlayerDAOMemoryImpl;

/**
 * Created by Pavel on 22.09.2015.
 */

public class Facade {

    private Facade(){}

    public static PlayerDAO getPlayerDAO() {
        return PlayerDAOMemoryImpl.getInstance();
    }

    public static GameDAO getGameDAO(){
        return GameDAOMemoryImpl.getInstance();
    }

}
