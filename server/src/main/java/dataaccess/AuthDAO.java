package dataaccess;

import model.AuthData;
import model.UserData;

public interface AuthDAO {
    AuthData createAuth(AuthData data);
    AuthData getAuth(AuthData data);
    void deleteAuth(AuthData data);

}
