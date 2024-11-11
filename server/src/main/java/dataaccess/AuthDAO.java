package dataaccess;

import model.AuthData;

public interface AuthDAO {
    AuthData createAuth(AuthData data) throws DataAccessException;
    AuthData getAuth(AuthData data) throws DataAccessException;
    void deleteAuth(AuthData data) throws DataAccessException;
}
