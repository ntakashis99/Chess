package service.requestresult;

public record JoinGameRequest(String authorization, String playerColor, String gameID) {
}
