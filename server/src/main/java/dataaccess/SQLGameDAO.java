package dataaccess;

import com.google.gson.Gson;
import model.GameData;
import model.UserData;
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
        return null;
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
    public void createGame(GameData game) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            //ASK HOW TO SET SOME VALUES BUT NOT ALL
            var statement = "INSERT INTO GameData (whiteUsername,blackUsername,gameName,game) VALUES (?,?,?,?)";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(0,game.whiteUsername());
                ps.setString(1,game.blackUsername());
                ps.setString(2,game.gameName());
                ps.setString(3,new Gson().toJson(game.game()));
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return;
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
