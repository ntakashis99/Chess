package dataaccess;

import model.AuthData;

public interface AuthDAO {
    AuthData createAuth(AuthData data) throws DataAccessException;
    AuthData getAuth(String data) throws DataAccessException;
    void deleteAuth(AuthData data) throws DataAccessException;
    void deleteAllAuth() throws DataAccessException;
}
