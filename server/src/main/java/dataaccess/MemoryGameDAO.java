package dataaccess;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;

public class MemoryGameDAO implements GameDAO{
    private ArrayList<GameData> games = null;

    public MemoryGameDAO(){
        this.games = new ArrayList<>();
    }

    @Override
    public ArrayList<GameData> getGames() throws DataAccessException {
        return this.games;
    }

    @Override
    public void deleteAllGames() throws DataAccessException {
        this.games = null;
    }

    @Override
    public int createGame(String gameName) throws DataAccessException {
        games.add(new GameData(games.size() +1,null,null,gameName,new ChessGame()));
        return games.size();
    }

    public int getNumGames(){
        return games.size();
    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {
        for(GameData game:games){
            if(game.gameID()==gameID){
                return game;
            }
        }
        throw new DataAccessException("Error: bad request");
    }

    @Override
    public void setGame(GameData game) throws DataAccessException {
        for(int i=0; i<games.size(); i++){
            if(games.get(i).gameID()==game.gameID()){
                games.set(i,game);
                return;
            }
        }
        throw new DataAccessException("Error: bad request");
    }
}
