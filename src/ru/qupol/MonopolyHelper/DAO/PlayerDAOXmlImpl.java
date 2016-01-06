package ru.qupol.MonopolyHelper.DAO;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.qupol.MonopolyHelper.Entity.Player;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pavel on 21.09.2015.
 */
public  class PlayerDAOXmlImpl implements PlayerDAO {

    private static PlayerDAO instance;

    private  Document playersDocument;




    private PlayerDAOXmlImpl() {
        playersDocument = getDocument();


    }
    public static PlayerDAO getInstance(){
        if(instance==null)
            instance=new PlayerDAOXmlImpl();
        return instance;
    }

    private Document getDocument(){
        DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
        Document document=null;
        try {
            File file = new File("game.xml");
            if(!file.exists()){
                file.createNewFile();
            }
            DocumentBuilder builder = dbf.newDocumentBuilder();

            document = builder.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


    @Override
    public Player get(int id) {
        return null;
    }

    @Override
    public List<Player> getAll() {
        return null;
    }

    @Override
    public void update(Player player) {

    }

    @Override
    public void updateAll(List<Player> players) {

    }

    @Override
    public boolean delete(Player player) {
        return false;
    }

    @Override
    public void deleteAll(List<Player> players) {

    }

    @Override
    public void add(Player player) {

    }

    @Override
    public void clear() {

    }


}
