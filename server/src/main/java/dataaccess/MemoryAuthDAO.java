package dataaccess;

import model.AuthData;

import java.util.ArrayList;

public class MemoryAuthDAO implements AuthDAO{
    private ArrayList<AuthData> auths = null;

    public MemoryAuthDAO(){
        this.auths = new ArrayList<>();
    }

    @Override
    public AuthData createAuth(AuthData data) {
        auths.add(data);
        return data;
    }

    @Override
    public AuthData getAuth(AuthData data) {
        for(AuthData auth: auths){
            if(auth.authToken() == data.authToken()){
                return auth;
            }
        }
        return null;
    }

    @Override
    public void deleteAuth() {
        this.auths = null;
    }
}
