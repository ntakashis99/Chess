package dataaccess;

import model.AuthData;
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
                ps.setString(0,user.username());
                try(var rs = ps.executeQuery()){
                    if(rs.next()){
                        return new UserData(rs.getString(0),rs.getString(1),rs.getString(2));
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
    public void deleteUser() throws DataAccessException {

    }

    @Override
    public UserData createUser(UserData user) throws DataAccessException {
        return null;
    }
}
