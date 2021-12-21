package org.comroid.contabo;

public final class ContaboConnection {
    private String bearerToken;

    public ContaboConnection(String accessToken) {
        this.bearerToken = "Bearer " + accessToken;
    }
}
