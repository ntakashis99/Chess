package dataaccess;

import model.UserData;

public interface UserDAO {

    UserData readUser(UserData username) throws DataAccessException;
    void deleteUser(UserData user) throws DataAccessException;

}
