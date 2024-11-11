package dataaccess;

import model.GameData;

import java.util.ArrayList;

public class MemoryGameDAO implements GameDAO{
    private ArrayList<GameData> games = null;

    public MemoryGameDAO(){
        this.games = new ArrayList<>();
    }

    @Override
    public GameData getGame(GameData game) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteGame(GameData game) throws DataAccessException {
        this.games = null;
    }

    @Override
    public GameData createGame(GameData game) throws DataAccessException {
        return null;
    }
}
