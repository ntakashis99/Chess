package dataaccess;

import model.UserData;
import service.InvalidUserException;

import java.sql.SQLException;

public class SQLUserDAO implements UserDAO{

    public SQLUserDAO(){
        try {
            DatabaseManager.createTables();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserData getUser(UserData user) throws DataAccessException {
        if(user==null){
            throw new InvalidUserException("Error: unauthorized");
        }
        try (var conn = DatabaseManager.getConnection()){
            var statement = "SELECT username, password, email FROM UserData WHERE username=?";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(1,user.username());
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return new UserData(rs.getString(1),rs.getString(2),rs.getString(3));
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
    public void deleteAllUsers() throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            var statement = "TRUNCATE UserData";
            try(var ps = conn.prepareStatement(statement)){
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public UserData createUser(UserData user) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            var statement = "INSERT INTO UserData (username, password, email) VALUES (?,?,?)";
            try(var ps = conn.prepareStatement(statement)){
                ps.setString(1,user.username());
                ps.setString(2,user.password());
                ps.setString(3,user.email());
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return new UserData(rs.getString(1),rs.getString(2),rs.getString(3));
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
}
