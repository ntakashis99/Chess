package dataaccess;

import model.GameData;

import java.util.ArrayList;

import static dataaccess.DatabaseManager.createTables;

public class SQLGameDAO implements GameDAO {

    public SQLGameDAO(){
    }

    @Override
    public ArrayList<GameData> getGames() throws DataAccessException {
        return null;
    }

    @Override
    public void deleteGame() throws DataAccessException {

    }

    @Override
    public void createGame(GameData game) throws DataAccessException {

    }

    @Override
    public int getNumGames() {
        return 0;
    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {
        return null;
    }

    @Override
    public void setGame(GameData game) throws DataAccessException {

    }
}
