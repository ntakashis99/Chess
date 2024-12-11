package ui;

import chess.ChessGame;
import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.UserData;
import ui.requestresult.CreateGameRequest;
import ui.requestresult.CreateGameResult;
import ui.requestresult.JoinGameRequest;
import ui.requestresult.ListGameResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class ServerFacade {

    private final String serverUrl;
    private String authToken;

    public ServerFacade(String port){
        serverUrl = port;
        authToken = null;
    }

    public AuthData register(String username, String password, String email) throws ResponseException {
        String path = "/user";
        var auth =  this.makeRequest("POST", path, new UserData(username, password, email), AuthData.class,false);
        this.authToken = auth.authToken();
        return auth;
    }

    public AuthData login(String username, String password) throws ResponseException {
        String path = "/session";
        var auth =  this.makeRequest("POST", path, new UserData(username, password,null), AuthData.class,false);
        this.authToken = auth.authToken();
        return auth;
    }

    public void logout() throws ResponseException {
        String path = "/session";
        this.makeRequest("DELETE",path, null,null,true);
    }

    public int create(String gameName) throws ResponseException {
        String path = "/game";
        CreateGameResult result = this.makeRequest("POST",path,new CreateGameRequest(this.authToken,gameName),CreateGameResult.class,true);
        return result.gameID();
    }

    public ArrayList<GameData> list() throws ResponseException {
        String path = "/game";
        record ListGameResponse(ArrayList<GameData> games) {
        }
        return this.makeRequest("GET",path,null, ListGameResult.class,true).games();
    }

    public int join(String color, int gameID) throws ResponseException {
        String path = "/game";
        CreateGameResult result = this.makeRequest("PUT",path, new JoinGameRequest(this.authToken,color,gameID),CreateGameResult.class,true);
        return result.gameID();
    }

    public void clear() throws ResponseException {
        String path = "/db";
        this.makeRequest("DELETE",path,null,null,false);
    }


    private <T> T makeRequest(String method, String path, Object request, Class<T> responseClass, Boolean header) throws ResponseException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            //You need to write it to the headers
            if(header){
                writeHeader(this.authToken,http);
            }
            writeBody(request, http);
            http.connect();
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        } catch (ResponseException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    private void writeHeader(String authToken, HttpURLConnection http) throws IOException {
        http.addRequestProperty("Authorization", authToken);
        //http.addRequestProperty("authorization",authToken);
    }


    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            http.addRequestProperty("Content-Type", "application/json");
            //http.addRequestProperty("authorization",authToken);
            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, ResponseException {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            try (InputStream respErr = http.getErrorStream()) {
                if (respErr != null) {
                    throw ResponseException.fromJson(respErr);
                }
            }
            throw new ResponseException(status, "other failure: " + status);
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }


    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }

}
