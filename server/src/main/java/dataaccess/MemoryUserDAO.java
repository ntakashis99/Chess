package dataaccess;

import model.UserData;

import java.util.*;

public class MemoryUserDAO implements UserDAO{

    private ArrayList<UserData> users = null;

    public MemoryUserDAO(){
        this.users = new ArrayList<>();
    }

    @Override
    public UserData getUser(UserData user) throws DataAccessException {
        for(var u:users){
            if(Objects.equals(u.username(), user.username())){
                return u;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(UserData user) throws DataAccessException {
        this.users = null;
    }

    @Override
    public UserData createUser(UserData user) throws DataAccessException {
        users.add(new UserData(user.username(),user.password(),user.email()));
        return user;
    }
}
