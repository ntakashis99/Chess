package dataaccess;

import model.GameData;

import java.util.ArrayList;

public interface GameDAO {

    ArrayList<GameData> getGames() throws DataAccessException;
    void deleteAllGames() throws DataAccessException;
    int createGame(String gameName) throws DataAccessException;
    int getNumGames() throws DataAccessException;
    GameData getGame(int gameID) throws DataAccessException;
    void setGame(GameData game) throws DataAccessException;
}
