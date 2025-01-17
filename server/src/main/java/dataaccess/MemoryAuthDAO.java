package dataaccess;

import model.AuthData;
import service.InvalidUserException;

import java.util.ArrayList;

public class MemoryAuthDAO implements AuthDAO{
    private ArrayList<AuthData> auths = null;

    public MemoryAuthDAO(){
        this.auths = new ArrayList<>();
    }

    @Override
    public AuthData createAuth(AuthData data) throws DataAccessException {
        auths.add(data);
        return data;
    }

    @Override
    public AuthData getAuth(String authToken) throws InvalidUserException {
        if(authToken==null){
            throw new InvalidUserException("Error: unauthorized");
        }
        for(AuthData auth: auths){
            if(auth.authToken().equals(authToken)){
                return auth;
            }
        }
        throw new InvalidUserException("Error: unauthorized");
    }

    @Override
    public void deleteAuth(AuthData data) throws InvalidUserException {
        for(var u:auths){
            if(u.authToken()==data.authToken()){
                auths.remove(u);
                return;
            }
            throw new InvalidUserException("Error: unauthorized");
        }
    }

    @Override
    public void deleteAllAuth() throws DataAccessException {

    }
}
