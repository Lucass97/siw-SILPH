package it.uniroma3.siw.services.security;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}