package service;

import chess.ChessGame;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.GameData;
import service.requestresult.CreateGameRequest;
import service.requestresult.CreateGameResult;
import service.requestresult.JoinGameRequest;
import service.requestresult.ListGameResult;

import java.util.Objects;

public class GameService {

    private UserDAO userDao = null;
    private AuthDAO authDao = null;
    private GameDAO gameDao = null;

    public GameService(UserDAO userDao, AuthDAO authDao, GameDAO gameDao) {
        this.userDao = userDao;
        this.authDao = authDao;
        this.gameDao = gameDao;
    }

    public ListGameResult listGames(String auth) throws DataAccessException {
        AuthData verified = authDao.getAuth(auth);
        return new ListGameResult(gameDao.getGames());
    }

    public CreateGameResult createGame(CreateGameRequest request) throws DataAccessException{
        AuthData verified = authDao.getAuth(request.authorization());
        int num_games = gameDao.getNumGames();
        GameData data = new GameData(num_games+1,null,null,request.gameName(),new ChessGame());
        gameDao.createGame(data);
        return new CreateGameResult(data.gameID());
    }

    public CreateGameResult joinGame(JoinGameRequest request) throws Exception {
        AuthData verified = authDao.getAuth(request.authorization());
        GameData game = gameDao.getGame(request.gameID());
        //Start here by making checking if the color is right, if all good
        //set the new one as it. Then return the result.
        if(Objects.equals(request.playerColor(), "WHITE")){
            if(game.whiteUsername()!=null){
                throw new DataAccessException("Error: already taken");
            }
            GameData ourGame = new GameData(request.gameID(), verified.username(), game.blackUsername(),game.gameName(),game.game());
            gameDao.setGame(ourGame);
            return new CreateGameResult(ourGame.gameID());
        } else if (Objects.equals(request.playerColor(), "BLACK")) {
            if(game.blackUsername()!=null){
                throw new DataAccessException("Error: already taken");
            }
            GameData ourGame = new GameData(request.gameID(), game.whiteUsername(), verified.username(), game.gameName(),game.game());
            gameDao.setGame(ourGame);
            return new CreateGameResult(ourGame.gameID());
        }
        throw new Exception("Error: something went wrong");
    }



}
