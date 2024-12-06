package dataaccess;

import model.UserData;

public interface UserDAO {

    UserData getUser(UserData user) throws DataAccessException;

    void deleteAllUsers() throws DataAccessException;

    void createUser(UserData user) throws DataAccessException;

}
