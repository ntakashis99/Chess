package dataaccess;

import model.AuthData;

public interface AuthDAO {
    AuthData createAuth(AuthData data);
    AuthData getAuth(AuthData data);
    void deleteAuth();
}
