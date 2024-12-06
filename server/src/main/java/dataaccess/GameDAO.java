package dataaccess;

import model.AuthData;
import model.GameData;

import java.util.ArrayList;

public interface GameDAO {

    ArrayList<GameData> getGames() throws DataAccessException;
    void deleteAllGames() throws DataAccessException;
    void createGame(GameData game) throws DataAccessException;
    int getNumGames();
    GameData getGame(int gameID) throws DataAccessException;
    void setGame(GameData game) throws DataAccessException;
}
