package dataaccess;

import model.GameData;

public class MemoryGameDAO implements GameDAO{
    @Override
    public GameData getGame(GameData game) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteGame(GameData game) throws DataAccessException {

    }

    @Override
    public GameData createGame(GameData game) throws DataAccessException {
        return null;
    }
}
