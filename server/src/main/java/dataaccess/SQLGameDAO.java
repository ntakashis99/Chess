package dataaccess;

import model.GameData;

import java.util.ArrayList;

public class SQLGameDAO implements GameDAO {
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
