package dataaccess;

import model.AuthData;
import service.InvalidUserException;

import java.sql.SQLException;

import static dataaccess.DatabaseManager.createTables;

public class SQLAuthDAO implements AuthDAO{

    public SQLAuthDAO(){
        try {
            DatabaseManager.createTables();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthData createAuth(AuthData data) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            var statement = "INSERT INTO AuthData (username,authToken) VALUES (?,?)";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(0,data.username());
                ps.setString(1,data.authToken());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return data;
    }

    @Override
    public AuthData getAuth(String data) throws DataAccessException {
        if(data==null){
            throw new InvalidUserException("Error: unauthorized");
        }
        try (var conn = DatabaseManager.getConnection()){
            var statement = "SELECT username, authToken FROM AuthData WHERE authToken=?";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(0,data);
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return new AuthData(rs.getString(0),rs.getString(1));
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
    public void deleteAuth(AuthData data) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            var statement = "DELETE FROM AuthData WHERE authToken=?";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(0,data.authToken());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
