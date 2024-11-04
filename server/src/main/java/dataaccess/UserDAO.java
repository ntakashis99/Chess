package dataaccess;

import model.UserData;

public interface UserDAO {

    UserData getUser(UserData user) throws DataAccessException;
    void deleteUser(UserData user) throws DataAccessException;
    UserData createUser(UserData user) throws DataAccessException;

}
