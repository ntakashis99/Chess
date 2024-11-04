package dataaccess;

import model.UserData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryUserDAO implements UserDAO{

    private ArrayList<Map<String,String>> users = null;

    public MemoryUserDAO(){
        this.users = new ArrayList<>();
    }

    @Override
    public UserData getUser(UserData user) throws DataAccessException {
        for(var u:users){
            if(u.get("username") == user.username()){
                return new UserData(u.get("username"),u.get("password"),u.get("email"));
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
        Map<String,String> map = new HashMap<>();
        map.put("username",user.username());
        map.put("password",user.password());
        map.put("email",user.email());
        users.add(map);
        return user;
    }
}
