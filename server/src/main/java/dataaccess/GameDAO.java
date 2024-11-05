package dataaccess;

import model.GameData;

public interface GameDAO {

    GameData getGame(GameData game) throws DataAccessException;
    void deleteGame(GameData game) throws DataAccessException;
    GameData createGame(GameData game) throws DataAccessException;
}
