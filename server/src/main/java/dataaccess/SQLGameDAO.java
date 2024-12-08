package dataaccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;
import service.InvalidUserException;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLGameDAO implements GameDAO {

    public SQLGameDAO(){
        try {
            DatabaseManager.createTables();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<GameData> getGames() throws DataAccessException {
        ArrayList<GameData> games = new ArrayList<>();
        int numGames = getNumGames();
        for(int i=1;i<=numGames;i++){
            games.add(getGame(i));
        }
        return games;
    }

    @Override
    public void deleteAllGames() throws DataAccessException {
        try(var conn = DatabaseManager.getConnection()){
            var statement = "TRUNCATE GameData";
            try(var ps = conn.prepareStatement(statement)){
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public int createGame(String gameName) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            //ASK HOW TO SET SOME VALUES BUT NOT ALL
            var statement = "INSERT INTO GameData (whiteUsername,blackUsername,gameName,game) VALUES (?,?,?,?)";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(1, null);
                ps.setString(2, null);
                ps.setString(3, gameName);
                ps.setString(4,new Gson().toJson(new ChessGame()));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return getNumGames();
    }

    @Override
    public int getNumGames() throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            var statement = "SELECT COUNT(*) FROM GameData";
            try(var ps = conn.prepareStatement(statement)){
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return 0;
    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            var statement = "SELECT gameId, whiteUsername, blackUsername, gameName, game FROM GameData WHERE gameID=?";
            try(var ps = conn.prepareStatement(statement)){
                ps.setInt(1,gameID);
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return new GameData(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        new Gson().fromJson(rs.getString(5), ChessGame.class));
                    }
                    else{
                        throw new InvalidUserException("Error: unauthorized");
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void setGame(GameData game) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            var statement = "UPDATE GameData SET whiteUsername = ?, blackUsername=?,gameName=?,game=? WHERE gameId = ?";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(1,game.whiteUsername());
                ps.setString(2,game.blackUsername());
                ps.setString(3,game.gameName());
                ps.setString(4,new Gson().toJson(game.game()));
                ps.setInt(5,game.gameID());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
