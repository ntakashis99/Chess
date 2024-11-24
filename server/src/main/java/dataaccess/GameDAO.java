package dataaccess;

import model.AuthData;
import model.GameData;

import java.util.ArrayList;

public interface GameDAO {

    ArrayList<GameData> getGames(AuthData user) throws DataAccessException;
    void deleteGame() throws DataAccessException;
    void createGame(GameData game) throws DataAccessException;
    int getNumGames();
}
