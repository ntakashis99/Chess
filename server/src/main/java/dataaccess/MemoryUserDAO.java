package dataaccess;

import model.UserData;

public class MemoryUserDAO implements UserDAO{

    @Override
    public UserData readUser(UserData username) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteUser(UserData user) throws DataAccessException {

    }
}
